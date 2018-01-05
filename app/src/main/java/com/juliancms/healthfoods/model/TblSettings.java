package com.juliancms.healthfoods.model;

import com.juliancms.healthfoods.data.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by marines on 12/29/17.
 */
@Table(database = AppDatabase.class)
public class TblSettings extends BaseModel {
    @Column
    @PrimaryKey
    int IdSettings;

    @Column
    String LocationTblUM;

    @Column
    String LocationTblCustomers;

    @Column
    String LocationTblSalesDetail;

    @Column
    String LocationTblSalesHead;

    @Column
    String LocationTblProducts;

    public Integer getIdSettings() {
        return IdSettings;
    }

    public void setIdSettings(int IdSettings) {
        this.IdSettings = IdSettings;
    }

    public String getLocationTblUM() {
        return LocationTblUM;
    }

    public void setLocationTblUM(String LocationTblUM){
        this.LocationTblUM = LocationTblUM;
    }

    public String getLocationTblCustomers() {
        return LocationTblCustomers;
    }

    public void setLocationTblCustomers(String LocationTblCustomers){
        this.LocationTblCustomers = LocationTblCustomers;
    }

    public String getLocationTblSalesDetail() {
        return LocationTblSalesDetail;
    }

    public void setLocationTblSalesDetail(String LocationTblSalesDetail){
        this.LocationTblSalesDetail = LocationTblSalesDetail;
    }

    public String getLocationTblSalesHead() {
        return LocationTblSalesHead;
    }

    public void setLocationTblSalesHead(String LocationTblSalesHead){
        this.LocationTblSalesHead = LocationTblSalesHead;
    }

    public String getLocationTblProducts() {
        return LocationTblProducts;
    }

    public void setLocationTblProducts(String LocationTblProducts){
        this.LocationTblProducts = LocationTblProducts;
    }
}
