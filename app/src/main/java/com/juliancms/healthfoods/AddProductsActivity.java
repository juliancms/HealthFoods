package com.juliancms.healthfoods;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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
    TblProducts p = new TblProducts();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        sv= (SearchView) findViewById(R.id.sv);
        populateProductsList();
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

//        final int size = TblProductsList.size();
//        for (TblProducts product: TblProductsList) {
//            Products.add(product.getItemDescription());
//        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                Products );
//        lv.setAdapter(arrayAdapter);
    }
    private void populateProductsList() {
        // Construct the data source
        ArrayList<TblProducts> arrayOfProducts = TblProductsList;
        // Create the adapter to convert the array to views
        CustomProductsAdapter adapter = new CustomProductsAdapter(this, arrayOfProducts);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }
    private void getProducts(String searchTerm)
    {
        ArrayList<TblProducts> ProductsArray = (ArrayList<TblProducts>) SQLite.select().
                from(TblProducts.class).
                where(TblProducts_Table.ItemDescription.like("%" + searchTerm + "%")).queryList();

        CustomProductsAdapter adapter = new CustomProductsAdapter(this, ProductsArray);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);

    }

    public void onClickProduct(View v)
    {
        ConstraintLayout vwParentRow = (ConstraintLayout)v.getParent();
        TextView childDescription = (TextView)vwParentRow.getChildAt(0);
        TextView childPrice = (TextView)vwParentRow.getChildAt(1);
        TextView childID = (TextView)vwParentRow.getChildAt(3);

        p.setItemID(childID.getText().toString());
        p.setItemDescription(childDescription.getText().toString());
        p.setSalesPrice1(childPrice.getText().toString());
        ProductsAdded.add(p);
        Button button_products = (Button) findViewById(R.id.button_products);
        button_products.setText(ProductsAdded.size() + " PRODUCTS ADDED");
//        Integer index = (Integer) v.getTag();
//        items.remove(index.intValue());
    }

}