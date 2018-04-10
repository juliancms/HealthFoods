package com.juliancms.healthfoods.model;

import com.juliancms.healthfoods.data.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by marines on 12/29/17.
 */
@Table(database = AppDatabase.class)
public class TblSalesDetail extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long IdSalesDetail;

    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public TblProducts product;

    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public TblSalesHead saleHead;


    @Column
    int ItemQuantity;

    @Column
    String UnitPriceS;

    @Column
    Double VatS;

//    @Column
//    String DiscountS;
//
//    @Column
//    String VatP3;

    public long getIdSalesDetail() {
        return IdSalesDetail;
    }
    public void setIdSalesDetail(long IdSalesDetail) {
        this.IdSalesDetail = IdSalesDetail;
    }

    public TblProducts getProduct() { return product; }
    public Integer getItemQuantity() { return ItemQuantity; }
    public void setItemQuantity(Integer ItemQuantity){ this.ItemQuantity = ItemQuantity; }

    public String getUnitPriceS() { return UnitPriceS; }
    public void setUnitPriceS(String UnitPriceS){ this.UnitPriceS = UnitPriceS; }

    public Double getVatS() { return VatS; }
    public void setVatS(Double VatS){ this.VatS = VatS; }

    public Integer getProductQuantityOut() {
        Integer number = 1;
        String n;
        String Number = product.getSalesUMNoStockingUnits();
        if (Number != null && !Number.isEmpty() && Number != "0") {
            n = product.getSalesUMNoStockingUnits();
        } else {
            n = "0";
        }

        if(n == "0" || n.isEmpty() || n == null){
            number = ItemQuantity * 1;
        } else {
            number = ItemQuantity * Integer.parseInt(n);
        }
        if(number == 0) number = 1;
        return number;
    }

    public Double getPriceTotal() {
        Double total_double = Double.parseDouble(UnitPriceS);
        total_double = total_double * getProductQuantityOut();
        return total_double;
    }
}
