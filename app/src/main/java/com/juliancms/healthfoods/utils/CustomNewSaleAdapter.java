package com.juliancms.healthfoods.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.juliancms.healthfoods.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by marines on 2/21/18.
 */

public class CustomNewSaleAdapter extends BaseAdapter {

    Context c;
    ArrayList<ProductsAdded> products;
    LayoutInflater inflater;
    Activity activity;
    public static Double total;
    public static Double total_tax;

    public CustomNewSaleAdapter(Context c, ArrayList<ProductsAdded> products, Activity activity) {
        this.c = c;
        this.products = products;
        this.activity = activity;
        this.total = null;
        this.total_tax = null;
    }

    static class ViewHolder {
        protected TextView product;
        protected TextView quantity;
        protected TextView price;
        protected TextView total;
        protected Integer ref;
        protected TextView um;
        protected ImageView remove;
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
            viewHolder.quantity = (TextView) view.findViewById(R.id.quantity);
            viewHolder.um = (TextView) view.findViewById(R.id.um);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            viewHolder.total = (TextView) view.findViewById(R.id.total);
            viewHolder.remove = (ImageView) view.findViewById(R.id.remove_btn);
            viewHolder.remove.setTag(position);
            final View finalView = view;
            final View finalView1 = view;
            viewHolder.remove.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            products.remove(position);
                            notifyDataSetChanged();
                            Double subtotal = setSubTotal(finalView1);
                            Double totalTax = setTotalTax(finalView1);
                            Double total = subtotal + totalTax;
                            TextView total_total = (TextView) activity.findViewById(R.id.total_total);
                            total_total.setText("TOTAL: $" + total.toString());
                            total = total;
                            total_tax = totalTax;
                        }
            });

            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }
        Double subtotal = setSubTotal(view);
        Double totalTax = setTotalTax(view);
        Double total = subtotal + totalTax;
        TextView total_total = (TextView) activity.findViewById(R.id.total_total);
        total_total.setText("TOTAL: $" + total.toString());
        this.total = total;
        this.total_tax = totalTax;
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.quantity.setText(products.get(position).getItemQuantity().toString());
        holder.um.setText(products.get(position).getItemUM().toString());
        holder.product.setText(products.get(position).getItemDescription());
        holder.price.setText(products.get(position).getItemPriceNumbers().toString());
        holder.total.setText(products.get(position).getItemTotal().toString());
        return view;
    }

    public Double setSubTotal(View view){
        Double total = 0.0;
        for (int i = 0; i < getCount(); i++) {
            total = total + products.get(i).getItemTotal();
        }
        total = round(total, 2);
        TextView total_total = (TextView) activity.findViewById(R.id.sub_total);
        total_total.setText("SUBTOTAL: $" + total.toString());
        return total;
    }
    public Double setTotalTax(View view){
        Double total = 0.0;
        for (int i = 0; i < getCount(); i++) {
            total = total + products.get(i).getItemVAT();
        }
        total = round(total, 2);
        TextView total_vat = (TextView) activity.findViewById(R.id.total_tax);
        total_vat.setText("TAX: $" + total.toString());
        return total;
    }

    public static Double getTotal(){
        return total;
    }
    public static Double getTotalTax(){
        return total_tax;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
