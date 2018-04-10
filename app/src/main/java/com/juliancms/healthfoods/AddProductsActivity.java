package com.juliancms.healthfoods;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.juliancms.healthfoods.model.TblProducts;
import com.juliancms.healthfoods.model.TblProducts_Table;
import com.juliancms.healthfoods.utils.CustomProductsAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class AddProductsActivity extends AppCompatActivity {
    ListView lv;
    SearchView sv;
    ArrayList<TblProducts> TblProductsList = (ArrayList<TblProducts>) SQLite.select().
            from(TblProducts.class).queryList();
    ArrayList<TblProducts> ProductsAdded = new ArrayList<>();
    ArrayList<String> productDescription=new ArrayList<String>();
    ArrayList<String> productPrice=new ArrayList<String>();
    ArrayList<String> productID=new ArrayList<String>();
    ArrayList<String> productQuantity=new ArrayList<String>();
    ArrayList<String> productTotal=new ArrayList<String>();
    String PricingLevel;

    public static final Integer REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent i = getIntent();
        PricingLevel = i.getExtras().getString("PricingLevel");
        sv= (SearchView) findViewById(R.id.sv);
        populateProductsList(TblProductsList, PricingLevel);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getProducts(newText);
                return false;
            }
        });
    }
    private void populateProductsList(ArrayList products, String PricingLevel) {
        // Create the adapter to convert the array to views
        CustomProductsAdapter adapter = new CustomProductsAdapter(this, products, AddProductsActivity.this, PricingLevel);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }
    private void getProducts(String searchTerm)
    {
        ArrayList<TblProducts> ProductsArray = (ArrayList<TblProducts>) SQLite.select().
                from(TblProducts.class).
                where(TblProducts_Table.ItemDescription.like("%" + searchTerm + "%")).queryList();

        CustomProductsAdapter adapter = new CustomProductsAdapter(this, ProductsArray, AddProductsActivity.this, PricingLevel);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);

    }

    public void onClickProduct(View v)
    {
        ConstraintLayout vwParentRow = (ConstraintLayout)v.getParent();
        TextView childDescription = (TextView)vwParentRow.findViewById(R.id.productADescription);
        TextView childPrice = (TextView)vwParentRow.findViewById(R.id.productPrice);
        TextView childID = (TextView)vwParentRow.findViewById(R.id.productID);
        TextView childTax = (TextView)vwParentRow.findViewById(R.id.productTax);
        Spinner childUM = (Spinner)vwParentRow.findViewById(R.id.productUM);
        EditText childQuantity = (EditText)vwParentRow.findViewById(R.id.productQuantity);
        Intent intent=new Intent();
        intent.putExtra("productID", childID.getText().toString());
        intent.putExtra("productTax", childTax.getText().toString());
        intent.putExtra("productDescription", childDescription.getText().toString());
        intent.putExtra("productPrice", childPrice.getText().toString());
        intent.putExtra("productUM", childUM.getSelectedItem().toString());
        intent.putExtra("productQuantity", childQuantity.getText().toString());
        setResult(2,intent);
        finish();
    }

}