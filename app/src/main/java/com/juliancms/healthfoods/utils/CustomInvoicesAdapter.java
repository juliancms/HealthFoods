package com.juliancms.healthfoods.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juliancms.healthfoods.R;
import com.juliancms.healthfoods.model.TblSalesHead;

import java.util.ArrayList;

/**
 * Created by marines on 2/21/18.
 */

public class CustomInvoicesAdapter extends BaseAdapter {

    Context c;
    ArrayList<TblSalesHead> sales;
    LayoutInflater inflater;

    public CustomInvoicesAdapter(Context c, ArrayList<TblSalesHead> sales) {
        this.c = c;
        this.sales = sales;
    }

    @Override
    public int getCount() {
        return sales.size();
    }

    @Override
    public Object getItem(int position) {
        return sales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.item_invoices,parent,false);
        }

        // Lookup view for data population
        TextView tvID = (TextView) convertView.findViewById(R.id.saleID);
        TextView tvCustomer = (TextView) convertView.findViewById(R.id.saleCustomer);
        TextView tvType = (TextView) convertView.findViewById(R.id.saleType);
        TextView tvDate = (TextView) convertView.findViewById(R.id.saleDate);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.saleTotal);


        // Populate the data into the template view using the data object
        tvID.setText(String.valueOf(sales.get(position).getIdSalesHead()));
        tvCustomer.setText(String.valueOf(sales.get(position).getCustomerName()));
        tvDate.setText(sales.get(position).getDateS());
        tvType.setText(sales.get(position).getType());
        tvTotal.setText("$ " + String.valueOf(sales.get(position).getTotal()));

        return convertView;
    }
}

