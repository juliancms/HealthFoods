package com.juliancms.healthfoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.juliancms.healthfoods.model.TblSalesDetail;
import com.juliancms.healthfoods.model.TblSalesDetail_Table;
import com.juliancms.healthfoods.model.TblSalesHead;
import com.juliancms.healthfoods.model.TblSalesHead_Table;
import com.juliancms.healthfoods.utils.CustomInvoiceAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Intent i = getIntent();
        String saleID = i.getStringExtra("saleID");
        TblSalesHead sale = SQLite.select().
                from(TblSalesHead.class).
                where(TblSalesHead_Table.IdSalesHead.eq(Long.valueOf(saleID))).querySingle();
        ArrayList<TblSalesDetail> products = (ArrayList<TblSalesDetail>)  SQLite.select().
                from(TblSalesDetail.class).
                where(TblSalesDetail_Table.saleHead_IdSalesHead.eq(Long.valueOf(saleID))).
                and(TblSalesDetail_Table.SalesTypeAgencyID.isNull()).queryList();
        CustomInvoiceAdapter adapter = new CustomInvoiceAdapter(this, products, InvoiceActivity.this);
        TextView tvType = (TextView) findViewById(R.id.saleType);
        TextView tvSaleID = (TextView) findViewById(R.id.saleID);
        TextView tvDate = (TextView) findViewById(R.id.Date);
        TextView tvCustomerName = (TextView) findViewById(R.id.customerName);
        tvType.setText(sale.getType());
        if(sale.getType() == "CREDIT"){
            tvType.setText(sale.getType() + " DUE DAYS: " + sale.customer.getDueDays());
        }
        tvSaleID.setText("ID: " + String.valueOf(sale.getIdSalesHead()));
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        DateTime dateTime = DateTime.parse(sale.getDateS(), dtf);
        DateTimeFormatter dtf2 = DateTimeFormat.forPattern("dd/MMMM/yyyy");
        String textdate = dtf2.print(dateTime);
        tvDate.setText("DATE: " + textdate);
        tvCustomerName.setText(sale.customer.getCustomerName());
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }
}
