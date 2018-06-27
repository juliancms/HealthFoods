package com.juliancms.healthfoods.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.juliancms.healthfoods.R;
import com.juliancms.healthfoods.model.TblSalesDetail;
import com.juliancms.healthfoods.model.TblSalesHead;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by marines on 2/21/18.
 */

public class CustomInvoiceAdapter extends BaseAdapter {

    Context c;
    ArrayList<TblSalesDetail> products;
    TblSalesHead sale;
    LayoutInflater inflater;
    Activity activity;
    public static Double total;
    public static String subtotal_s;
    public static String totaltax_s;
    public static String total_s;
    DecimalFormat formatter = new DecimalFormat("#,###,###.00");

    public CustomInvoiceAdapter(Context c, ArrayList<TblSalesDetail> products, TblSalesHead sale, Activity activity) {
        this.c = c;
        this.products = products;
        this.sale = sale;
        this.activity = activity;
        this.total = null;
        this.subtotal_s = null;
        this.totaltax_s = null;
        this.total_s = null;
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
            view = inflater.inflate(R.layout.item_invoice,parent,false);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.product = (TextView) view.findViewById(R.id.product);
            viewHolder.quantity = (TextView) view.findViewById(R.id.quantity);
            viewHolder.um = (TextView) view.findViewById(R.id.um);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            viewHolder.total = (TextView) view.findViewById(R.id.total);
            final View finalView = view;
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
//            ((ViewHolder) view.getTag()).price.setTag(products.get(position));
        }
        Double subtotal = setSubTotal(view);
        Double totalTax = setTotalTax(view);
        Double total = subtotal + totalTax;
        total = round(total, 2);
        TextView total_total = (TextView) activity.findViewById(R.id.total_total);
        this.total_s = "TOTAL: $" + formatter.format(total);
        total_total.setText(total_s);
        this.total = total;
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.quantity.setText(products.get(position).getItemQuantity().toString());
        if(Integer.parseInt(products.get(position).product.getSalesUMNoStockingUnits()) > 0){
            holder.um.setText(products.get(position).product.getSalesUM().toString());
        } else {
            holder.um.setText("UNITS");
        }
        holder.product.setText(products.get(position).product.getItemDescription());
        holder.price.setText(products.get(position).getUnitPriceS().toString());
        holder.total.setText(formatter.format(products.get(position).getPriceTotal()));
        Button btn_print = (Button)activity.findViewById(R.id.btn_print);
        btn_print.setOnClickListener(mBtnPrintOnClickListener);
        return view;
    }

    public Double setSubTotal(View view){
        Double total = 0.0;
        for (int i = 0; i < getCount(); i++) {
            total = total + products.get(i).getPriceTotal();
        }
        total = round(total, 2);
        TextView total_total = (TextView) activity.findViewById(R.id.sub_total);
        this.subtotal_s = "SUBTOTAL: $" + formatter.format(total);
        total_total.setText(subtotal_s);
        return total;
    }
    public Double setTotalTax(View view){
        Double total = 0.0;
        for (int i = 0; i < getCount(); i++) {
            total = total + products.get(i).getVatS();
        }
        total = round(total, 2);
        TextView total_vat = (TextView) activity.findViewById(R.id.total_tax);
        this.totaltax_s = "TAX: $" + formatter.format(total);
        total_vat.setText(totaltax_s);
        return total;
    }

    public static Double getTotal(){
        return total;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    View.OnClickListener mBtnPrintOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(BluetoothPrintDriver.IsNoConnection()){
                return;
            }
            BluetoothPrintDriver.Begin();
            DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
            DateTime dateTime = DateTime.parse(sale.getDateS(), dtf);
            DateTimeFormatter dtf2 = DateTimeFormat.forPattern("dd/MMMM/yyyy");
            String textdate = dtf2.print(dateTime);
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.SetAlignMode((byte) 1);
            BluetoothPrintDriver.BT_Write("Caribbean Health Foods Ltd");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("Maracas Royal, St. Joseph");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("Tel: 6456231");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("VAT Reg: 229083");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.SetAlignMode((byte) 0);
            BluetoothPrintDriver.BT_Write("Invoice No: " + String.valueOf(sale.getIdSalesHead()));
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("Date: " + textdate);
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("Customer: " + sale.customer.getCustomerName());
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("Product Description");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("QTY  *  UNIT PRICE  =  ");
            BluetoothPrintDriver.LF();
            for (int i = 0; i < getCount(); i++) {
                BluetoothPrintDriver.BT_Write(products.get(i).product.getItemDescription());
                BluetoothPrintDriver.LF();
                BluetoothPrintDriver.BT_Write(products.get(i).getItemQuantity().toString() + "  *  $" + products.get(i).getUnitPriceS().toString() + "  =  $" + formatter.format(products.get(i).getPriceTotal()));
                BluetoothPrintDriver.LF();
                BluetoothPrintDriver.LF();
            }
            BluetoothPrintDriver.BT_Write("SUBTOTAL: " + subtotal_s);
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("TAX: " + totaltax_s);
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("TOTAL: " + total_s);
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("CUSTOMER SIGNATURE: __________________");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.BT_Write("Thank you 4 shopping with us!");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.LF();
        }
    };

}
