package com.juliancms.healthfoods;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.juliancms.healthfoods.model.TblSalesHead;
import com.juliancms.healthfoods.utils.CustomInvoicesAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class InvoicesActivity extends AppCompatActivity {
    ArrayList<TblSalesHead> TblSalesList = (ArrayList<TblSalesHead>) SQLite.select().
            from(TblSalesHead.class).queryList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        CustomInvoicesAdapter adapter = new CustomInvoicesAdapter(this, TblSalesList);
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    /** Called when the user taps on an Item */
    public void openInvoice(View view) {
        Intent intent = new Intent(this, InvoiceActivity.class);
        ConstraintLayout vwParentRow = (ConstraintLayout)view.getParent();
        TextView childID = (TextView)vwParentRow.findViewById(R.id.saleID);
        intent.putExtra("saleID", childID.getText().toString());
        startActivity(intent);
    }
}
