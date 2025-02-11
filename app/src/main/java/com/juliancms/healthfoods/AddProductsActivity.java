package com.juliancms.healthfoods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.juliancms.healthfoods.model.TblProducts;
import com.juliancms.healthfoods.model.TblProducts_Table;
import com.juliancms.healthfoods.utils.CustomProductsAdapter;
import com.juliancms.healthfoods.utils.ProductsAdded;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class AddProductsActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<ProductsAdded> products=new ArrayList<>();
    SearchView sv;
    ArrayList<TblProducts> TblProductsList = (ArrayList<TblProducts>) SQLite.select().
            from(TblProducts.class).queryList();
    String PricingLevel;
    String credit_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Intent i = getIntent();
        PricingLevel = i.getExtras().getString("PricingLevel");
        credit_note = i.getExtras().getString("credit_note");
        Bundle extras = i.getExtras();
        products = (ArrayList<ProductsAdded>) extras.getSerializable("products");
        String q_products = products.size() + " PRODUCTS ADDED";
        TextView products_added = (TextView) findViewById(R.id.products_added);
        products_added.setText(q_products);
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
        LinearLayout vwParentRow = (LinearLayout) v.getParent();
        TextView childDescription = (TextView)vwParentRow.findViewById(R.id.productADescription);
        TextView childPrice = (TextView)vwParentRow.findViewById(R.id.productPrice);
        TextView childID = (TextView)vwParentRow.findViewById(R.id.productID);
        TextView childTax = (TextView)vwParentRow.findViewById(R.id.productTax);
        Spinner childUM = (Spinner)vwParentRow.findViewById(R.id.productUM);
        EditText childQuantity = (EditText)vwParentRow.findViewById(R.id.productQuantity);
        ProductsAdded p=null;
        p=new ProductsAdded();
        p.setItemID(childID.getText().toString());
        p.setItemTax(Integer.parseInt(childTax.getText().toString()));
        p.setItemDescription(childDescription.getText().toString());
        p.setItemPrice(childPrice.getText().toString());
        p.setItemQuantity(childQuantity.getText().toString());
        p.setItemUM(childUM.getSelectedItem().toString());
        p.setItemTotal();
        p.setItemPriceLevel(PricingLevel);
        products.add(p);

        String q_products = products.size() + " PRODUCTS ADDED";
        TextView products_added = (TextView) findViewById(R.id.products_added);
        products_added.setText(q_products);
        childQuantity.setText("");
        hideKeyboard(this);
    }

    public void onClickBack(View v)
    {
        Intent intent=new Intent();
        intent.putExtra("products", products);
        setResult(2, intent);
        finish();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}