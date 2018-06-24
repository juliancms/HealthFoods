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
    Long DateS;

    @Column
    int ItemQuantity;

    @Column
    int QuantityS;

    @Column
    String UnitPriceS;

    @Column
    Double VatS;

    @Column(defaultValue = "0")
    Integer DiscountS;

    @Column
    Double VatP3;

    @Column
    String SalesTypeAgencyID;

    public Long getDateS() { return DateS; }
    public void setDateS(Long DateS){
        this.DateS = DateS;
    }

    public long getIdSalesDetail() {
        return IdSalesDetail;
    }
    public void setIdSalesDetail(long IdSalesDetail) {
        this.IdSalesDetail = IdSalesDetail;
    }

    public TblProducts getProduct() { return product; }
    public Integer getItemQuantity() { return ItemQuantity; }
    public void setItemQuantity(Integer ItemQuantity){ this.ItemQuantity = ItemQuantity; }

    public Integer getQuantityS() { return QuantityS; }
    public void setQuantityS(Integer QuantityS){ this.QuantityS = QuantityS; }

    public String getUnitPriceS() { return UnitPriceS; }
    public void setUnitPriceS(String UnitPriceS){ this.UnitPriceS = UnitPriceS; }

    public Double getVatS() { return VatS; }
    public void setVatS(Double VatS){ this.VatS = VatS; }

    public Double getVatP3() { return VatP3; }
    public void setVatP3(Double VatP3){ this.VatP3 = VatP3; }

    public Double getPriceTotal() {
        Double total_double = Double.parseDouble(UnitPriceS);
        total_double = total_double * QuantityS;
        return total_double;
    }

    public String getSalesTypeAgencyID() { return SalesTypeAgencyID; }
    public void setSalesTypeAgencyID(String SalesTypeAgencyID){ this.SalesTypeAgencyID = SalesTypeAgencyID; }
}
