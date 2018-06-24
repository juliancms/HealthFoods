package com.juliancms.healthfoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.juliancms.healthfoods.model.TblCustomers;
import com.juliancms.healthfoods.model.TblProducts;
import com.juliancms.healthfoods.model.TblProfile;
import com.juliancms.healthfoods.model.TblSalesHead;
import com.juliancms.healthfoods.utils.CustomInvoicesAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        TblProducts Product = SQLite.select().
                from(TblProducts.class).querySingle();
        TblCustomers Customer_query = SQLite.select().
                from(TblCustomers.class).querySingle();
        TblProfile Profile = SQLite.select().
                from(TblProfile.class).querySingle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(Product == null || Customer_query == null || Profile == null){
            TextView editText = (TextView) findViewById(R.id.welcome_title);
            TextView editText2 = (TextView) findViewById(R.id.welcome_text);
            editText.setVisibility(View.VISIBLE);
            editText2.setVisibility(View.VISIBLE);
        } else {
            NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView2.getHeaderView(0);
            TextView namesalesman = (TextView) headerView.findViewById(R.id.name_salesman);
            TextView vehicle = (TextView) headerView.findViewById(R.id.id_vehicle);
            namesalesman.setText(Profile.getNameSalesMan());
            vehicle.setText("ID Vehicle: " + Profile.getIdVehicles());
            ArrayList<TblSalesHead> TblSalesList = (ArrayList<TblSalesHead>) SQLite.select().
                    from(TblSalesHead.class).queryList();
            if(TblSalesList.size() > 0){
                CustomInvoicesAdapter adapter = new CustomInvoicesAdapter(this, TblSalesList);
                ListView listView = (ListView) findViewById(R.id.lv);
                listView.setAdapter(adapter);
            } else {
                TextView editText = (TextView) findViewById(R.id.welcome_title);
                TextView editText2 = (TextView) findViewById(R.id.welcome_text);
                editText2.setText("You have not created sales yet.");
                editText.setVisibility(View.VISIBLE);
                editText2.setVisibility(View.VISIBLE);
            }

        }
    }

    /** Called when the user taps on an Item */
    public void openInvoice(View view) {
        Intent intent = new Intent(this, InvoiceActivity.class);
        ConstraintLayout vwParentRow = (ConstraintLayout)view.getParent();
        TextView childID = (TextView)vwParentRow.findViewById(R.id.saleID);
        intent.putExtra("saleID", childID.getText().toString());
        startActivity(intent);
    }

    /** Called when the user taps the Settings button */
    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Profile button */
    public void profile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the New Sale button */
    public void saleCustomer(View view) {
        Intent intent = new Intent(this, SaleCustomerActivity.class);
        intent.putExtra("sale_type", 1);
        startActivity(intent);
    }

    /** Called when the user taps the New Sale button */
    public void saleCreditCustomer(View view) {
        Intent intent = new Intent(this, SaleCustomerActivity.class);
        intent.putExtra("sale_type", 2);
        startActivity(intent);
    }

    /** Called when the user taps the New Sale button */
    public void saleReturnCustomer(View view) {
        Intent intent = new Intent(this, SaleCustomerActivity.class);
        intent.putExtra("sale_type", 3);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.options, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_import) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_export) {
            Intent intent = new Intent(this, ExportActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_new_cash) {
            Intent intent = new Intent(this, SaleCustomerActivity.class);
            intent.putExtra("sale_type", 1);
            startActivity(intent);

        } else if (id == R.id.nav_new_credit) {
            Intent intent = new Intent(this, SaleCustomerActivity.class);
            intent.putExtra("sale_type", 2);
            startActivity(intent);

        } else if (id == R.id.nav_new_return) {
            Intent intent = new Intent(this, SaleCustomerActivity.class);
            intent.putExtra("sale_type", 3);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
