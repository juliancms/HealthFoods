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
import com.juliancms.healthfoods.model.TblProfile;
import com.juliancms.healthfoods.model.TblSalesDetail;
import com.juliancms.healthfoods.model.TblSalesHead;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
    public static Double credit_note;
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
        this.credit_note = 0.0;
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
        credit_note = sale.getCreditNote();
        Double total = (subtotal + totalTax) - credit_note;
        total = round(total, 2);
        TextView total_total = (TextView) activity.findViewById(R.id.total_total);
        TextView tv_credit_note = (TextView) activity.findViewById(R.id.credit_note);
        if(sale.getTypeInt() == 3 && sale.getStatus() == 0){
            this.total_s = "TOTAL: ($" + formatter.format(total) + ")";
        } else {
            this.total_s = "TOTAL: $" + formatter.format(total);
        }
        total_total.setText(total_s);
        tv_credit_note.setText("CREDIT NOTE: $" + formatter.format(credit_note));
        this.total = total;
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.quantity.setText(products.get(position).getItemQuantity().toString());
        if(Integer.parseInt(products.get(position).product.getSalesUMNoStockingUnits()) > 0 && products.get(position).getQuantityUM() > 1){
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
            String prefixsale = null;
            String namesale = null;
            String getVat = null;
            String getUM = null;
            List<TblProfile> TblProfileList = SQLite.select().
                    from(TblProfile.class).queryList();
            for (TblProfile tblprofile: TblProfileList) {
                prefixsale = tblprofile.getPrefixSalesMan().toString();
                namesale = tblprofile.getNameSalesMan().toString();
            }
            if (sale.getTypeInt() == 3){
                prefixsale = "CR" + prefixsale;
            }
            BluetoothPrintDriver.Begin();
            DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
            DateTime dateTime = DateTime.parse(sale.getDateS(), dtf);
            DateTimeFormatter dtf2 = DateTimeFormat.forPattern("dd/MMMM/yyyy");
            String textdate = dtf2.print(dateTime);
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.SetAlignMode((byte) 1);
            BluetoothPrintDriver.BT_Write("Caribbean Health Foods Ltd");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Maracas Royal, St. Joseph");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Tel: 6456231");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("VAT Reg: 229083");
            if(sale.getStatus() == 1){
                BluetoothPrintDriver.BT_Write("\r");
                BluetoothPrintDriver.BT_Write("VOIDED");
            }
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
            BluetoothPrintDriver.SetAlignMode((byte) 0);
            BluetoothPrintDriver.BT_Write("Sale Type: " + sale.getType());
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Invoice No: " + prefixsale + String.valueOf(sale.getIdSalesHead()));
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Date: " + textdate);
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Customer: " + sale.customer.getCustomerName());
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
            BluetoothPrintDriver.BT_Write("Product Description");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("QTY (UM)  *  UNIT PRICE  =  ");
            BluetoothPrintDriver.BT_Write("\r");
            for (int i = 0; i < getCount(); i++) {
                if(products.get(i).getVatS() > 0){
                    getVat = " (V)";
                } else {
                    getVat = "";
                }
                if(Integer.parseInt(products.get(i).product.getSalesUMNoStockingUnits()) > 0 && products.get(i).getQuantityUM() > 1){
                    getUM = " (" + products.get(i).product.getSalesUM().toString() + ")";
                } else {
                    getUM = " (UNITS)";
                }
                BluetoothPrintDriver.BT_Write(products.get(i).product.getItemDescription()+ getVat);
                BluetoothPrintDriver.BT_Write("\r");
                BluetoothPrintDriver.BT_Write(products.get(i).getItemQuantity().toString() + getUM + "  *  $" + products.get(i).getUnitPriceS().toString() + "  =  $" + formatter.format(products.get(i).getPriceTotal()));
                BluetoothPrintDriver.BT_Write("\r");
                BluetoothPrintDriver.LF();
                BluetoothPrintDriver.CR();
            }
            BluetoothPrintDriver.SetAlignMode((byte) 2);
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write(subtotal_s);
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write(totaltax_s);
            BluetoothPrintDriver.BT_Write("\r");
            if(sale.getCreditNote() > 0.0 && sale.getStatus() == 0){
                BluetoothPrintDriver.BT_Write("CREDIT NOTE: $" + formatter.format(credit_note));
                BluetoothPrintDriver.BT_Write("\r");
            }
            BluetoothPrintDriver.BT_Write(total_s);
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
            BluetoothPrintDriver.BT_Write("CUSTOMER SIGNATURE: ___________________________");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Vendor Name:" + namesale);
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.BT_Write("Thank you 4 shopping with us!");
            BluetoothPrintDriver.BT_Write("\r");
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
            BluetoothPrintDriver.LF();
            BluetoothPrintDriver.CR();
        }
    };

}
