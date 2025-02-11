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
public class TblProfile extends BaseModel {
    @Column
    @PrimaryKey
    int IdSalesMan;

    @Column
    String PrefixSalesMan;

    @Column
    String NameSalesMan;

    @Column
    String IdVehicles;

    public Integer getIdSalesMan() {
        return IdSalesMan;
    }

    public void setIdSalesMan(int IdSalesMan) {
        this.IdSalesMan = IdSalesMan;
    }

    public String getIdVehicles() { return IdVehicles; }

    public void setIdVehicles(String IdVehicles){
        this.IdVehicles = IdVehicles;
    }

    public String getPrefixSalesMan() { return PrefixSalesMan; }

    public void setPrefixSalesMan(String PrefixSalesMan){
        this.PrefixSalesMan = PrefixSalesMan;
    }

    public String getNameSalesMan() { return NameSalesMan; }

    public void setNameSalesMan(String NameSalesMan){
        this.NameSalesMan = NameSalesMan;
    }

}
