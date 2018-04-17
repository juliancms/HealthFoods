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
public class TblSalesHead extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long IdSalesHead;

    @Column
    long id_Customer;

    @Column
    String IdCustomersKey;

    @Column
    int SalesInvoiceNumber;

    @Column
    String DateS;

    @Column
    String DateDue;

    @Column
    String IdVehiclesKey;

    @Column
    int IdSalesManKey;

    @Column
    String CustomerName;

    @Column
    String CN;

    @Column
    Integer NoDistrib;

    @Column
    String SalesInvoiceNumberP3;

    @Column
    int Status;

    @Column
    int Type;

    @Column
    String DateV;

    @Column(defaultValue = "30")
    int TermDays;

    @Column(defaultValue = "0")
    int PriceLevel;

    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public TblCustomers customer;

    @Column
    Double Total;

    public long getIdSalesHead() {
        return IdSalesHead;
    }
    public void setIdSalesHead(long IdSalesHead) {
        this.IdSalesHead = IdSalesHead;
    }

    public String getIdCustomersKey() { return IdCustomersKey; }
    public void setIdCustomersKey(String IdCustomersKey){
        this.IdCustomersKey = IdCustomersKey;
    }

    public String getDateDue() { return DateDue; }
    public void setDateDue(String DateDue){
        this.DateDue = DateDue;
    }

    public String getDateS() { return DateS; }
    public void setDateS(String DateS){
        this.DateS = DateS;
    }

    public String getDateV() { return DateV; }
    public void setDateV(String DateV){
        this.DateV = DateV;
    }

    public String getIdVehiclesKey() { return IdVehiclesKey; }
    public void setIdVehiclesKey(String IdVehiclesKey){
        this.IdVehiclesKey = IdVehiclesKey;
    }

    public Integer getIdSalesManKey() {
        return IdSalesManKey;
    }
    public void setIdSalesManKey(int IdSalesManKey) {
        this.IdSalesManKey = IdSalesManKey;
    }

    public Integer getNoDistrib() {
        return NoDistrib;
    }
    public void setNoDistrib(int NoDistrib) {
        this.NoDistrib = NoDistrib;
    }

    public String getCustomerName() { return CustomerName; }
    public void setCustomerName(String CustomerName){
        this.CustomerName = CustomerName;
    }

    public Integer getPriceLevel() {
        return PriceLevel;
    }
    public void setPriceLevel(int PriceLevel) {
        this.PriceLevel = PriceLevel;
    }

    public long getid_Customer() {
        return id_Customer;
    }
    public void setid_Customer(Long id_Customer) {
        this.id_Customer = id_Customer;
    }

    public Double getTotal() {
        return Total;
    }
    public void setTotal(double Total) {
        this.Total = Total;
    }



    public String getType() {
        String type_text = null;
        if(this.Type == 1){
            type_text = "CASH";
        } else if (this.Type == 2){
            type_text = "CREDIT";
        }
        return type_text;
    }
    public void setType(int Type) {
        this.Type = Type;
    }

}
