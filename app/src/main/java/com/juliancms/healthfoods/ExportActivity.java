package com.juliancms.healthfoods;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
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

import java.io.File;
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
//            String filename = from + "/sales"+dateTime1.getDayOfMonth()+dateTime1.getMonthOfYear()+dateTime1.getYear()+"-"+dateTime2.getDayOfMonth()+dateTime2.getMonthOfYear()+dateTime2.getYear()+".csv";
                        String filename = from + "/TblDeviceSalesExport.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(filename));
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
                sale.add(sales.saleHead.getType());
                sale.add(Profile.getPrefixSalesMan());
                sale.add(String.valueOf(sales.saleHead.getIdSalesHead()));
                sale.add(sales.saleHead.customer.getCustomerID());
                sale.add(sales.saleHead.customer.getCustomerName());
                sale.add(sales.saleHead.getDateS().toString());
                sale.add(sales.saleHead.getDateV().toString());
                sale.add(sales.saleHead.getDateDue());
                sale.add(Profile.getIdVehicles());
                sale.add(Profile.getNameSalesMan());
                sale.add(sales.product.getItemID());
                sale.add(sales.product.getItemDescription());
                sale.add(String.valueOf(sales.getItemQuantity()));
                String SalesUM;
                if(sales.product.getSalesUMNoStockingUnits() != null){
                    SalesUM = sales.product.getSalesUMNoStockingUnits();
                } else {
                    SalesUM = "0";
                }
                if(Integer.parseInt(SalesUM) > 0 && sales.getQuantityUM() > 1){
                    sale.add(sales.product.getSalesUM().toString());
                } else {
                    sale.add("UNITS");
                }
                sale.add(String.valueOf(sales.getQuantityUM()));
                sale.add(sales.getUnitPriceS());
                Double VatPorc = 0.0;
                if(sales.product.getItemTaxType().equals("0")){
                    VatPorc = 0.125;
                }
                sale.add(String.valueOf(VatPorc));
                Double VatAmount = 0.0;
                Integer total_quantity = sales.getItemQuantity() * sales.getQuantityUM();
                Double UnitPrice = Double.parseDouble(sales.getUnitPriceS());
                BigDecimal AmountBD = BigDecimal.valueOf(total_quantity).multiply(BigDecimal.valueOf(UnitPrice));
                Double Amount = 0.0;
                VatAmount = total_quantity * UnitPrice * VatPorc;
                sale.add(String.valueOf(VatAmount));
                Double Total = 0.0;
                Total = total_quantity * UnitPrice + VatAmount;
                sale.add(String.valueOf(Total));
                String[] record = new String[sale.size()];
                record = sale.toArray(record);
                writer.writeNext(record);
            }
            //close the writer
            writer.close();
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Sales " + Profile.getNameSalesMan());
            Uri fileURI = FileProvider.getUriForFile(ExportActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    new File(filename));
            sendIntent.putExtra(Intent.EXTRA_STREAM, fileURI);
            sendIntent.setType("text/html");
            startActivity(sendIntent);
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
