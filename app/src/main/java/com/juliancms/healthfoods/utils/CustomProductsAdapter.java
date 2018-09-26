package com.juliancms.healthfoods.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.juliancms.healthfoods.R;
import com.juliancms.healthfoods.model.TblProducts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marines on 2/21/18.
 */

public class CustomProductsAdapter extends BaseAdapter {

    Context c;
    ArrayList<TblProducts> products;
    LayoutInflater inflater;
    Activity activity;
    String PricingLevel;
    private int lastFocussedPosition = -1;
    private Handler handler = new Handler();

    public CustomProductsAdapter(Context c, ArrayList<TblProducts> products, Activity activity, String pricingLevel) {
        this.c = c;
        this.products = products;
        this.activity = activity;
        this.PricingLevel = pricingLevel;
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
        TextView tvTax = (TextView) convertView.findViewById(R.id.productTax);
        Spinner tvUM = (Spinner) convertView.findViewById(R.id.productUM);
        final EditText Quantity = (EditText) convertView.findViewById(R.id.productQuantity);
        List<String> tvUMList = new ArrayList<String>();
        if(Integer.parseInt(products.get(position).getSalesUMNoStockingUnits()) > 0){
            tvUMList.add(products.get(position).getSalesUM());
        }
        tvUMList.add("UNITS");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, tvUMList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tvUM.setAdapter(dataAdapter);
        Quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (lastFocussedPosition == -1 || lastFocussedPosition == position) {
                                lastFocussedPosition = position;
                                Quantity.requestFocus();
                            }
                        }
                    }, 200);

                } else {
                    lastFocussedPosition = -1;
                }
            }
        });


        // Populate the data into the template view using the data object
        tvDescription.setText(products.get(position).getItemDescription());
        Integer level = 0;
        if(PricingLevel == null || PricingLevel.isEmpty()){
            level = 0;
        } else {
            level = Integer.parseInt(PricingLevel);
        }
        switch (level) {
            case 0: tvPrice.setText("$" + products.get(position).getSalesPrice1());
                break;
            case 1:  tvPrice.setText("$" + products.get(position).getSalesPrice2());
                break;
            case 2:  tvPrice.setText("$" + products.get(position).getSalesPrice3());
                break;
            case 3:  tvPrice.setText("$" + products.get(position).getSalesPrice4());
                break;
            case 4:  tvPrice.setText("$" + products.get(position).getSalesPrice5());
                break;
            case 5:  tvPrice.setText("$" + products.get(position).getSalesPrice6());
                break;
            case 6:  tvPrice.setText("$" + products.get(position).getSalesPrice7());
                break;
            case 7:  tvPrice.setText("$" + products.get(position).getSalesPrice8());
                break;
            case 8:  tvPrice.setText("$" + products.get(position).getSalesPrice9());
                break;
            case 9: tvPrice.setText("$" + products.get(position).getSalesPrice10());
                break;
            default: tvPrice.setText("$" + products.get(position).getSalesPrice1());
                break;
        }
        tvID.setText(products.get(position).getItemID().toString());
        tvTax.setText(products.get(position).getItemTaxType());
        return convertView;
    }
}

