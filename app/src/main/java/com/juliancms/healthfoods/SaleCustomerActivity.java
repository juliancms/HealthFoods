package com.juliancms.healthfoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.juliancms.healthfoods.model.TblCustomers;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleCustomerActivity extends AppCompatActivity {
    TextView _tvEditDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_customer);

        _tvEditDate = (TextView) findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
        _tvEditDate.setText( sdf.format( new Date() ));

        List<TblCustomers> TblCustomersList = SQLite.select().
                from(TblCustomers.class).queryList();
        List Customers = new ArrayList();
        final int size = TblCustomersList.size();
        for (TblCustomers customer: TblCustomersList) {
            Customers.add(customer.getCustomerName());
        }
        ArrayAdapter<TblCustomers> adapter = new ArrayAdapter<TblCustomers>(this,
                android.R.layout.simple_dropdown_item_1line, Customers);
        AutoCompleteTextView textViewC = (AutoCompleteTextView)
                findViewById(R.id.customer);
        textViewC.setThreshold(1);
        textViewC.setAdapter(adapter);
    }

    /** Called when the user taps the New Sale button */
    public void add_products(View view) {
        Intent intent = new Intent(this, AddProductsActivity.class);
        startActivity(intent);
    }
}
