package com.juliancms.healthfoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.juliancms.healthfoods.utils.CustomCheckoutAdapter;
import com.juliancms.healthfoods.utils.ProductsAdded;
import com.travijuu.numberpicker.library.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    ArrayList<ProductsAdded> products=new ArrayList<>();
    NumberPicker quantity;
    TextView Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent i = getIntent();
        ArrayList<String> productID = i.getStringArrayListExtra("productID");
        ArrayList<String> productDescription = i.getStringArrayListExtra("productDescription");
        ArrayList<String> productPrice = i.getStringArrayListExtra("productPrice");
        ArrayList<String> productQuantity = i.getStringArrayListExtra("productQuantity");
        ArrayList<String> productTotal = i.getStringArrayListExtra("productTotal");
        String customer_name = i.getExtras().getString("customer_name");
        TextView Customer = (TextView) findViewById(R.id.customer);
        Customer.setText(customer_name);
        Date = (TextView) findViewById(R.id.Date);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String dateString = sdf.format(date);
        Date.setText("Date: " + dateString);
        ProductsAdded p=null;
        for(int x=0;x<=productDescription.size()-1;x+=1)
        {
            p=new ProductsAdded();
            p.setItemID(productID.get(x));
            p.setItemDescription(productDescription.get(x));
            p.setItemPrice(productPrice.get(x));
            p.setItemQuantity(Integer.parseInt(productQuantity.get(x)));
            String segments[] = productPrice.get(x).split("\\$");
            String number = segments[segments.length - 1];
            p.setItemTotal();
            products.add(p);
        }
        CustomCheckoutAdapter adapter = new CustomCheckoutAdapter(this, products, CheckoutActivity.this);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }
}
