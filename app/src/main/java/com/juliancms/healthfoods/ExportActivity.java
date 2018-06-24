package com.juliancms.healthfoods;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.juliancms.healthfoods.model.TblProfile;
import com.juliancms.healthfoods.model.TblSalesDetail;
import com.juliancms.healthfoods.model.TblSalesDetail_Table;
import com.opencsv.CSVWriter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lib.folderpicker.FolderPicker;

import static com.juliancms.healthfoods.SettingsActivity.FOLDER_PICKER_CODE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ExportActivity extends AppCompatActivity {
    static Button startDate;
    static Button endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        startDate = (Button) findViewById(R.id.startDate);
        endDate = (Button) findViewById(R.id.endDate);
    }
    public void showStartDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(1);
        newFragment.show(this.getFragmentManager(), "date picker");
    }
    public void showEndDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(2);
        newFragment.show(this.getFragmentManager(), "date picker");
    }

    @SuppressLint("ValidFragment")
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        Integer date_type;

        @SuppressLint("ValidFragment")
        public DatePickerFragment(int i) {
            this.date_type = i;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month + 1;
            String monthS = String.valueOf(month);
            String dayS = String.valueOf(day);
            if(month < 10){
                monthS = "0" + month;
            }
            if(day < 10){
                dayS = "0" + day;
            }
            // Do something with the date chosen by the user
            if(date_type == 1){
                startDate.setText(dayS + "/" + monthS + "/" + year);
            } else if(date_type == 2) {
                endDate.setText(dayS + "/" + monthS + "/" + year);
            }

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        if (requestCode == FOLDER_PICKER_CODE && resultCode == RESULT_OK) {
            String folderLocation = data.getExtras().getString("data");
            handleDirectoryChoice(folderLocation);
        }
    }

    private void handleDirectoryChoice(String from) {
        try {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
            String d1 = startDate.getText().toString() + " 00:00:01";
            String d2 = endDate.getText().toString() + " 23:59:59";
            DateTime dateTime1 = DateTime.parse(d1, dtf);
            DateTime dateTime2 = DateTime.parse(d2, dtf);
            CSVWriter writer = new CSVWriter(new FileWriter(from + "/export.csv"));
            //Create record
            TblProfile Profile = SQLite.select().
                    from(TblProfile.class).querySingle();
            List<TblSalesDetail> SalesList = SQLite.select().
                    from(TblSalesDetail.class).
                    where(TblSalesDetail_Table.DateS.greaterThanOrEq(dateTime1.getMillis())).
                    and(TblSalesDetail_Table.DateS.lessThanOrEq(dateTime2.getMillis())).
                    queryList();
            for (TblSalesDetail sales: SalesList) {
                List<String> sale = new ArrayList<String>();
                sale.add(sales.saleHead.customer.getCustomerID());
                sale.add(Profile.getPrefixSalesMan() + sales.saleHead.getIdSalesHead());
                sale.add("");
                sale.add("FALSE");
                sale.add(sales.saleHead.getDateS().toString());
                sale.add(sales.saleHead.customer.getCustomerName());
                sale.add(sales.saleHead.getDateDue());
                Double DiscountAmount = round(sales.getQuantityS() * Double.parseDouble(sales.getUnitPriceS()) * sales.getVatS(), 2);
                sale.add(DiscountAmount.toString());
                sale.add(sales.saleHead.customer.getDueDays() + " days");//Por confirmar Displayed Terms
                sale.add(Profile.getPrefixSalesMan());
                sale.add("10301-HO");
                sale.add("VAT");
                sale.add("");
                sale.add(sales.saleHead.getNoDistrib().toString());
                sale.add(sales.getQuantityS().toString());
                if(sales.product.getItemID().equals("11111")){
                    sale.add("");
                } else {
                    sale.add(sales.product.getItemID());
                }
                sale.add(sales.product.getItemDescription());
                sale.add(sales.product.getGLSalesAccount());
                Double VatPorc = 0.0;
                if(sales.product.getItemTaxType().equals("0")){
                    VatPorc = 0.125;
                }
                Double UnitPrice = Double.parseDouble(sales.getUnitPriceS()) / (1 + VatPorc);
                UnitPrice = round(UnitPrice, 2);
                sale.add(UnitPrice.toString());
                sale.add(sales.product.getItemTaxType());
                Double Amount = 0.0;
                if(sales.product.getItemTaxType().equals("0")){
                    Amount = sales.getVatP3();
                    if(Amount == null || Amount == 0.0){
                        Amount = 0.0;
                    } else {
                        Amount = round(Amount, 2);
                    }
                } else {
                    BigDecimal AmountBD = BigDecimal.valueOf(sales.getQuantityS()).multiply(BigDecimal.valueOf(UnitPrice));
                    Amount = round(AmountBD.doubleValue(), 2);

                }
                if(sales.saleHead.getTypeInt() < 3){
                    sale.add("-" + Amount.toString());
                } else {
                    sale.add(Amount.toString());
                }
                Log.e("NO LO PEUDO CREER", "handleDirectoryChoice: " + sales.getDateS());
                sale.add(sales.product.getGlInventoryAccount());
                sale.add(sales.product.getGLCOGSSalaryAcct());
                sale.add("1");
                sale.add(sales.getSalesTypeAgencyID());
                sale.add("");
                sale.add("");
                String[] record = new String[sale.size()];
                record = sale.toArray(record);
                writer.writeNext(record);
            }
            //close the writer
            writer.close();
            Toast.makeText(this, "The invoices has been successfully exported", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error exporting the file", Toast.LENGTH_SHORT).show();
        }
    }

    /** Called when the user taps the Export Invoices button */
    public void exportInvoices(View view) {
        if(startDate.getText().toString().isEmpty() || endDate.getText().toString().isEmpty()){
            Toast.makeText(this, "You must set both dates before exporting.", Toast.LENGTH_LONG).show();
            return;
        }
        if(CheckDates(startDate.getText().toString(), endDate.getText().toString()) == TRUE){
            Toast.makeText(this, "End Date must be greater or equal to Start Date", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, FolderPicker.class);
        startActivityForResult(intent, FOLDER_PICKER_CODE);
    }

    public static boolean CheckDates(String d1, String d2)    {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime date1 = dtf.parseDateTime(d1);
        DateTime date2 = dtf.parseDateTime(d2);
        if(date1.isAfter(date2)) {
            return TRUE;
        }
        return FALSE;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
