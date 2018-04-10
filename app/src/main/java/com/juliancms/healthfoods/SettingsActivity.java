package com.juliancms.healthfoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.filebrowser.Constants;
import com.aditya.filebrowser.FileChooser;
import com.juliancms.healthfoods.data.AppDatabase;
import com.juliancms.healthfoods.model.TblCustomers;
import com.juliancms.healthfoods.model.TblProducts;
import com.juliancms.healthfoods.model.TblProfile;
import com.juliancms.healthfoods.model.TblSalesDetail;
import com.juliancms.healthfoods.model.TblSalesHead;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    Button EditTblProducts;
    TextView _tvEditTblProducts;
    Button EditTblCustomers;
    TextView _tvEditTblCustomers;
    Button EditTblInvoices;
    TextView _tvEditTblInvoices;
    private static final int PICK_FILE_REQUEST = 1;
    private static final int REQUEST_DIRECTORY = 0;
    private static final String TAG = "DirChooserSample";
    int buttontype = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        List<TblProducts> TblProductList = SQLite.select().
                from(TblProducts.class).queryList();
        List<TblCustomers> TblCustomersList = SQLite.select().
                from(TblCustomers.class).queryList();
        List<TblSalesHead> TblInvoicesList = SQLite.select().
                from(TblSalesHead.class).queryList();
        EditTblProducts = (Button)findViewById(R.id.edit_tblproducts);
        _tvEditTblProducts = (TextView) findViewById(R.id.file_tblproducts);
        _tvEditTblProducts.setText("Total products added: " + TblProductList.size());
        EditTblCustomers = (Button)findViewById(R.id.edit_tblcustomers);
        _tvEditTblCustomers = (TextView) findViewById(R.id.file_tblcustomers);
        _tvEditTblCustomers.setText("Total customers added: " + TblCustomersList.size());
        EditTblInvoices = (Button)findViewById(R.id.edit_tblinvoices);
        _tvEditTblInvoices = (TextView) findViewById(R.id.file_tblinvoices);
        _tvEditTblInvoices.setText("Total invoices created: " + TblInvoicesList.size());
        EditTblProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttontype = 1;
                Intent i2 = new Intent(getApplicationContext(), FileChooser.class);
                i2.putExtra(Constants.SELECTION_MODE,Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal());
//                i2.putExtra(Constants.ALLOWED_FILE_EXTENSIONS, "csv");
                startActivityForResult(i2,PICK_FILE_REQUEST);
            }
        });
        EditTblCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttontype = 2;
                Intent i2 = new Intent(getApplicationContext(), FileChooser.class);
                i2.putExtra(Constants.SELECTION_MODE,Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal());
//                i2.putExtra(Constants.ALLOWED_FILE_EXTENSIONS, "csv");
                startActivityForResult(i2,PICK_FILE_REQUEST);
            }
        });
        EditTblInvoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttontype = 3;
                Intent i2 = new Intent(getApplicationContext(), FileChooser.class);
                i2.putExtra(Constants.SELECTION_MODE,Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal());
//                i2.putExtra(Constants.ALLOWED_FILE_EXTENSIONS, "csv");
                startActivityForResult(i2,PICK_FILE_REQUEST);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        if (requestCode == PICK_FILE_REQUEST && data!=null) {
            if (resultCode == RESULT_OK) {
                proImportCSV(new File(data.getData().getPath()));
            }
        }
    }

    private void handleDirectoryChoice(File from) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(from));
            //Create record
            TblProfile Profile = SQLite.select().
                    from(TblProfile.class).querySingle();
            List<TblSalesDetail> SalesList = SQLite.select().
                    from(TblSalesDetail.class).queryList();
            List<String> sale = new ArrayList<String>();
            for (TblSalesDetail sales: SalesList) {
                sale.add(sales.saleHead.customer.getCustomerID());
                sale.add(Profile.getPrefixSalesMan() + sales.saleHead.getIdSalesHead());
                sale.add("");
                sale.add("FALSE");
                sale.add(sales.saleHead.getDateS());
                sale.add(sales.saleHead.customer.getCustomerName());
                sale.add(sales.saleHead.getDateDue());
            }
            String [] record = "4,David,Miller,Australia,30".split(",");
            //Write the record to file


            //close the writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error exporting the file", Toast.LENGTH_SHORT).show();
        }
    }

    private void proImportCSV(File from){
        try{
            String [] nextLine;
            if(buttontype == 1){
                CSVReader reader = new CSVReader(new FileReader(from));
                reader.readNext();
                List<TblProducts> TblProductsList = SQLite.select().
                        from(TblProducts.class).queryList();
                for (TblProducts productdel: TblProductsList) {
                    productdel.delete();
                }
                List <TblProducts> products = new ArrayList();
                while ((nextLine = reader.readNext()) != null) {
                    TblProducts product = new TblProducts();
                    for(int i=0;i<=86;i+=1)
                    {
                        product.setField(i, nextLine[i]);
                    }
                    products.add(product);
                }
                FlowManager.getDatabase(AppDatabase.class).executeTransaction(
                        FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(TblProducts.class)).addAll(products).build());
                TblProductsList = SQLite.select().
                        from(TblProducts.class).queryList();
                _tvEditTblProducts.setText("Total products added: " + TblProductsList.size());
                Toast.makeText(this, "" + TblProductsList.size() + " products were imported successfully", Toast.LENGTH_SHORT).show();
            } else if (buttontype == 2){
                CSVReader reader = new CSVReader(new FileReader(from));
                reader.readNext();
                List<TblCustomers> TblCustomersList = SQLite.select().
                        from(TblCustomers.class).queryList();
                for (TblCustomers customerdel: TblCustomersList) {
                    customerdel.delete();
                }
                List <TblCustomers> customers = new ArrayList();
                while ((nextLine = reader.readNext()) != null) {
                    TblCustomers customer = new TblCustomers();
                    for(int i=0;i<=72;i+=1)
                    {
                        customer.setField(i, nextLine[i]);
                    }
                    customers.add(customer);
                }
                FlowManager.getDatabase(AppDatabase.class).executeTransaction(
                        FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(TblCustomers.class)).addAll(customers).build());
                TblCustomersList = SQLite.select().
                        from(TblCustomers.class).queryList();
                _tvEditTblCustomers.setText("Total customers added: " + TblCustomersList.size());
                Toast.makeText(this, "" + TblCustomersList.size() + " customers were imported successfully", Toast.LENGTH_SHORT).show();
            } else if (buttontype == 3){
                handleDirectoryChoice(from);
            }

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }
    }
//    /** Called when the user taps the Export Invoices button */
//    public void exportInvoices(View view) {
//        try {
//            String csv = "data.csv";
//            CSVWriter writer = new CSVWriter(new FileWriter(csv));
//            //Create record
//            String [] record = "4,David,Miller,Australia,30".split(",");
//            //Write the record to file
//            writer.writeNext(record);
//
//            //close the writer
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error exporting the file", Toast.LENGTH_SHORT).show();
//        }
//    }
}
