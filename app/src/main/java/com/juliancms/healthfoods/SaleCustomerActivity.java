package com.juliancms.healthfoods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.juliancms.healthfoods.data.AppDatabase;
import com.juliancms.healthfoods.model.TblCustomers;
import com.juliancms.healthfoods.model.TblCustomers_Table;
import com.juliancms.healthfoods.model.TblProducts;
import com.juliancms.healthfoods.model.TblProducts_Table;
import com.juliancms.healthfoods.model.TblProfile;
import com.juliancms.healthfoods.model.TblSalesDetail;
import com.juliancms.healthfoods.model.TblSalesHead;
import com.juliancms.healthfoods.utils.CustomNewSaleAdapter;
import com.juliancms.healthfoods.utils.ProductsAdded;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaleCustomerActivity extends AppCompatActivity {
    ArrayList<ProductsAdded> products=new ArrayList<>();
    String customerID;
    String ItemPriceLevel;
    Double credit_note = 0.0;
    TblCustomers Customer = null;
    Integer sale_type;
    CustomNewSaleAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_customer);
        TblProducts Product = SQLite.select().
                from(TblProducts.class).querySingle();
        TblCustomers Customer_query = SQLite.select().
                from(TblCustomers.class).querySingle();
        TblProfile Profile = SQLite.select().
                from(TblProfile.class).querySingle();
        if(Product == null || Customer_query == null || Profile == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        }
        Intent i = getIntent();
        sale_type = i.getIntExtra("sale_type", 0);
        if (sale_type == 1){
            setTitle("New Cash Sales");
        } else if(sale_type == 2){
            setTitle("New Credit Sales");
        } else if(sale_type == 3) {
            setTitle("New Returns");
        }
        List<TblCustomers> TblCustomersList = SQLite.select().
                from(TblCustomers.class).queryList();
        List Customers = new ArrayList();
        final int size = TblCustomersList.size();
        for (TblCustomers customer: TblCustomersList) {
            Customers.add(customer.getCustomerName() + " (" + customer.getCustomerID() + ")");
        }
        ArrayAdapter<TblCustomers> adapter = new ArrayAdapter<TblCustomers>(this,
                android.R.layout.simple_dropdown_item_1line, Customers);
        final AutoCompleteTextView textViewC = (AutoCompleteTextView)
                findViewById(R.id.customer);
        textViewC.setThreshold(1);
        textViewC.setAdapter(adapter);
        textViewC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(textViewC.getWindowToken(), 0);
            }

        });
        EditText cn = (EditText) findViewById(R.id.credit_note);
        cn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText et_credit_note = (EditText) findViewById(R.id.credit_note);
                if(!et_credit_note.getText().toString().matches("")) {
                    credit_note = Double.parseDouble(et_credit_note.getText().toString());
                } else {
                    credit_note = 0.0;
                }
                adapter2 = new CustomNewSaleAdapter(SaleCustomerActivity.this, products, SaleCustomerActivity.this, credit_note);
                // Attach the adapter to a ListView
                ListView listView = (ListView) findViewById(R.id.lv);
                listView.setAdapter(adapter2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Check which request it is that we're responding to
        super.onActivityResult(requestCode, resultCode, intent);
        // check if the request code is same as what is passed  here it is 2
            if(intent == null){
                return;
            }
            if (requestCode == 2) {
            // Make sure the request was successful
            Bundle extras = intent.getExtras();
            products = (ArrayList<ProductsAdded>) extras.getSerializable("products");
            if(products.size() > 0){
                Button btn_save = (Button) findViewById(R.id.button2);
                btn_save.setEnabled(true);
            }
            adapter2 = new CustomNewSaleAdapter(this, products, SaleCustomerActivity.this, credit_note);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.lv);
            listView.setAdapter(adapter2);

        }
    }

    /** Called when the user taps the New Sale button */
    public void add_products(View view) {
        EditText customer_id = (EditText) findViewById(R.id.customer);
        if(customer_id.getText().toString().length() == 0){
            Toast.makeText(this, customer_id.getText().toString() + "You must select a valid customer before adding products.", Toast.LENGTH_SHORT).show();
            return;
        }
        String value = customer_id.getText().toString();

        Pattern p = Pattern.compile("[(]");
        Pattern p2 = Pattern.compile("[)]");
        Matcher m = p.matcher(value);
        Matcher m2 = p2.matcher(value);
        if (m.find() && m2.find()) {
            String segments[] = value.split("[(]");
            customerID = segments[segments.length - 1];
            customerID = customerID.substring(0, customerID.length() - 1);
            Customer = SQLite.select().
                    from(TblCustomers.class).
                    where(TblCustomers_Table.CustomerID.eq(customerID)).querySingle();
            if(Customer == null){
                Toast.makeText(this, "You must select a valid customer before adding products.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(Customer.getCustomerStatus() == "B"){
                Toast.makeText(this, "You can not add products to this customer because you have outstanding accounts payable.", Toast.LENGTH_SHORT).show();
                return;
            }
            ItemPriceLevel = Customer.getPricingLevel();
        }
        else {
            Toast.makeText(this, "You must select a valid customer before adding products.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sale_type == 2){
            TextView tvTermDays = (TextView) findViewById(R.id.termDays);
            tvTermDays.setText("Due Days: " + Customer.getDueDays());
        }
        Intent intent=new Intent(SaleCustomerActivity.this,AddProductsActivity.class);
        intent.putExtra("PricingLevel", ItemPriceLevel);
        intent.putExtra("products", products);
        startActivityForResult(intent, 2);// Activity is started with requestCode 2
    }

    /** Called when the user taps the Save button */
    public void save(View view) {

        TblSalesHead sale = new TblSalesHead();
        sale.setid_Customer(Customer.getCustomerID().toString());
        sale.setCustomerName(Customer.getCustomerName());
        sale.setTotal(CustomNewSaleAdapter.getTotal() + credit_note);
        sale.setCreditNote(credit_note);
        Integer days = Integer.parseInt(Customer.getDueDays());
        long date = System.currentTimeMillis();
        DateTime dt = new DateTime(date);
        DateTime dtdue = dt.plusDays(days);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        DateTime dtS = new DateTime();
        Date dateS = dtS.toDate();
        String dateDue = dtf.print(dtdue);
        sale.setDateS(dtf.print(dt));
        sale.setDateDue(dateDue);
        DateTimeFormatter dtf2 = DateTimeFormat.forPattern("HH:mm");
        String dateString2 = dtf2.print(dt);
        sale.setDateV(dateString2);
        sale.setType(sale_type);
        sale.customer = Customer;
        sale.setNoDistrib(products.size());
        List<TblProfile> TblProfileList = SQLite.select().
                from(TblProfile.class).queryList();
        for (TblProfile tblprofile: TblProfileList) {
            sale.setIdSalesManKey(tblprofile.getIdSalesMan());
            sale.setIdVehiclesKey(tblprofile.getIdVehicles());
            break;
        }
        if(sale.save()){
            if(products.size() < 1){
                Toast.makeText(this, "You must add products before saving.", Toast.LENGTH_SHORT).show();
                return;
            }
            List <TblSalesDetail> sales = new ArrayList();
            for (ProductsAdded product: products){
                TblSalesDetail sale_detail = new TblSalesDetail();
                sale_detail.saleHead = sale;
                TblProducts product_db = SQLite.select().
                        from(TblProducts.class).
                        where(TblProducts_Table.ItemID.eq(product.getItemID())).querySingle();
                sale_detail.product = product_db;
                sale_detail.setDateDetail(dtS.getMillis());
                sale_detail.setItemQuantity(Integer.valueOf(product.getItemQuantity()));
                sale_detail.setQuantityUM(product.getItemQuantityUM());
                sale_detail.setUnitPriceS(product.getItemPriceNumbers());
                sale_detail.setVatS(product.getItemVAT());
                sales.add(sale_detail);
            }
            FlowManager.getDatabase(AppDatabase.class).executeTransaction(
                    FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(TblSalesDetail.class)).addAll(sales).build());

            Intent intent = new Intent(this, InvoiceActivity.class);
            intent.putExtra("saleID", String.valueOf(sale.getIdSalesHead()));
            startActivity(intent);
            finish();
        }

    }
}
