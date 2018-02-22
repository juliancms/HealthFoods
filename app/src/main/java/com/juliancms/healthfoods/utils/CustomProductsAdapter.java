package com.juliancms.healthfoods.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juliancms.healthfoods.R;
import com.juliancms.healthfoods.model.TblProducts;

import java.util.ArrayList;

/**
 * Created by marines on 2/21/18.
 */

public class CustomProductsAdapter extends BaseAdapter {

    Context c;
    ArrayList<TblProducts> products;
    LayoutInflater inflater;

    public CustomProductsAdapter(Context c, ArrayList<TblProducts> products) {
        this.c = c;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
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
            convertView=inflater.inflate(R.layout.item_product,parent,false);
        }

        // Lookup view for data population
        TextView tvDescription = (TextView) convertView.findViewById(R.id.productADescription);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.productPrice);
        TextView tvID = (TextView) convertView.findViewById(R.id.productID);

        // Populate the data into the template view using the data object
        tvDescription.setText(products.get(position).getItemDescription());
        tvPrice.setText("Unit Price: $" + products.get(position).getSalesPrice1());
        tvID.setText(products.get(position).getItemID());

        return convertView;
    }
}

