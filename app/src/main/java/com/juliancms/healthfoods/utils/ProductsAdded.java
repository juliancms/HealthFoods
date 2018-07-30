package com.juliancms.healthfoods.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.juliancms.healthfoods.model.TblProducts;
import com.juliancms.healthfoods.model.TblProducts_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by marines on 2/22/18.
 */

public class ProductsAdded implements Parcelable {
    String ItemID;
    String ItemDescription;
    String ItemPrice;
    String ItemUM;
    String ItemPriceLevel;
    Integer ItemTax;
    String ItemQuantity;
    Double ItemTotal;

    public ProductsAdded() {
    }

    public String getItemID() {
        return ItemID;
    }
    public void setItemID(String ItemID) {
        this.ItemID= ItemID;
    }

    public Integer  getItemTax() {
        return ItemTax;
    }
    public void setItemTax(int ItemTax) {
        this.ItemTax= ItemTax;
    }

    public String getItemDescription() {
        return ItemDescription;
    }
    public void setItemDescription(String ItemDescription) {
        this.ItemDescription= ItemDescription;
    }

    public String getItemPrice() {
        return ItemPrice;
    }
    public String getItemPriceNumbers() {
        String segments[] = ItemPrice.split("\\$");
        String number = segments[segments.length - 1];
        return number;
    }
    public void setItemPrice(String ItemPrice) {
        this.ItemPrice= ItemPrice;
    }

    public void setItemQuantity(String ItemQuantity) {
        this.ItemQuantity = ItemQuantity;
    }

    public String getItemQuantity() {
        return ItemQuantity;
    }

    public Integer getItemQuantityUM() {
        Integer number = 0;
        Integer n;
        TblProducts Number = SQLite.select(TblProducts_Table.SalesUMNoStockingUnits).
                from(TblProducts.class).
                where(TblProducts_Table.SalesUM.eq(ItemUM)).querySingle();
        if (Number != null) {
            n = Integer.valueOf(Number.getSalesUMNoStockingUnits());
        } else {
            n = 1;
        }
        return n;
    }


    public String getItemUM() {
        return ItemUM;
    }
    public void setItemUM(String ItemUM) {
        this.ItemUM= ItemUM;
    }

    public String getItemPriceLevel() {
        return ItemPriceLevel;
    }
    public void setItemPriceLevel(String ItemPriceLevel) {
        this.ItemPriceLevel= ItemPriceLevel;
    }

    public Double getItemTotal() {
        return ItemTotal;
    }
    public void setItemTotal() {
        Double total_double = Double.parseDouble(getItemPriceNumbers());
        int total_quantity = Integer.parseInt(ItemQuantity) * getItemQuantityUM();
        total_double = total_double * total_quantity;
        this.ItemTotal = total_double;
    }

    public Double getItemVAT() {
        Double VAT = 0.0;
        if (this.ItemTax == 1){
            VAT = getItemTotal() * (12.5/100);
        }
        return VAT;
    }

    protected ProductsAdded(Parcel in) {
        ItemID = in.readString();
        ItemDescription = in.readString();
        ItemPrice = in.readString();
        ItemUM = in.readString();
        ItemPriceLevel = in.readString();
        ItemQuantity = in.readString();
        ItemTax = in.readByte() == 0x00 ? null : in.readInt();
        ItemTotal = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ItemID);
        dest.writeString(ItemDescription);
        dest.writeString(ItemPrice);
        dest.writeString(ItemUM);
        dest.writeString(ItemPriceLevel);
        dest.writeString(ItemQuantity);
        if (ItemTax == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(ItemTax);
        }
        if (ItemTotal == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(ItemTotal);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProductsAdded> CREATOR = new Parcelable.Creator<ProductsAdded>() {
        @Override
        public ProductsAdded createFromParcel(Parcel in) {
            return new ProductsAdded(in);
        }

        @Override
        public ProductsAdded[] newArray(int size) {
            return new ProductsAdded[size];
        }
    };
}