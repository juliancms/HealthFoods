package com.juliancms.healthfoods.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juliancms.healthfoods.R;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;

/**
 * Created by marines on 2/21/18.
 */

public class CustomCheckoutAdapter extends BaseAdapter {

    Context c;
    ArrayList<ProductsAdded> products;
    LayoutInflater inflater;
    Activity activity;

    public CustomCheckoutAdapter(Context c, ArrayList<ProductsAdded> products, Activity activity) {
        this.c = c;
        this.products = products;
        this.activity = activity;
    }

    static class ViewHolder {
        protected TextView product;
        protected NumberPicker quantity;
        protected TextView price;
        protected TextView total;
        protected Integer ref;
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
        View view = null;
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            view = inflater.inflate(R.layout.checkout_row,parent,false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.product = (TextView) view.findViewById(R.id.product);
            viewHolder.quantity = (NumberPicker) view.findViewById(R.id.quantity);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            viewHolder.total = (TextView) view.findViewById(R.id.total);
            viewHolder.price.setTag(products.get(position));
            viewHolder.quantity.setTag(products.get(position));
            viewHolder.total.setTag(products.get(position));
            final View finalview = view;
            viewHolder.quantity.setValueChangedListener(new ValueChangedListener() {
                @Override
                public void valueChanged(int value, ActionEnum action) {
                    ProductsAdded element = (ProductsAdded) viewHolder.quantity.getTag();
                    element.setItemQuantity(value);
//                    element.setItemPrice(products.get(position).getItemPrice());
                    element.setItemTotal();
                    viewHolder.total.setText(products.get(position).getItemTotal().toString());
                    setTotal(finalview);
//                    ProductsAdded _price = (ProductsAdded) viewHolder.price.getTag();
//                    _price.setItemPrice(products.get(position).getItemPriceNumbers());
//                    viewHolder.price.setText(products.get(position).getItemPriceNumbers().toString());
                }
            });
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            ((ViewHolder) view.getTag()).quantity.setTag(products.get(position));
            ((ViewHolder) view.getTag()).total.setTag(products.get(position));
//            ((ViewHolder) view.getTag()).price.setTag(products.get(position));
        }
        setTotal(view);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.quantity.setValue(products.get(position).getItemQuantity());
        holder.product.setText(products.get(position).getItemDescription());
        holder.price.setText(products.get(position).getItemPriceNumbers());
        holder.total.setText(products.get(position).getItemTotal().toString());
        return view;
//        // Lookup view for data population
//        TextView product = (TextView) convertView.findViewById(R.id.product);
//        final com.travijuu.numberpicker.library.NumberPicker quantity = (com.travijuu.numberpicker.library.NumberPicker) convertView.findViewById(R.id.quantity);
//        TextView price = (TextView) convertView.findViewById(R.id.price);
//        final TextView total = (TextView) convertView.findViewById(R.id.total);
//
//        quantity.setValueChangedListener(new ValueChangedListener() {
//            @Override
//            public void valueChanged(int value, ActionEnum action) {
//                Double total_double = Double.parseDouble(products.get(position).getItemPriceNumbers());
//                total_double = total_double * value;
//                total.setText(total_double.toString());
//                quantity.setValue(value);
////                ConstraintLayout vwParentRow = (ConstraintLayout)v.getParent();
////                mApp.getPlayer().str = (short) value;
////                setTotalPoints();
//            }
//        });
//
//        // Populate the data into the template view using the data object
//        product.setText(products.get(position).getItemDescription());
//        price.setText(products.get(position).getItemPriceNumbers());
//        total.setText(products.get(position).getItemPriceNumbers());
//
//        return convertView;
    }

    public String setTotal(View view){
        Double total = 0.0;
        for (int i = 0; i < getCount(); i++) {
            total = total + products.get(i).getItemTotal();
        }
        Log.e("test total", "setTotal: " +total);
        TextView total_total = (TextView) activity.findViewById(R.id.total_total);
        total_total.setText(total.toString());
//        total_total.setText(total.toString());
        return total.toString();
    }

}
