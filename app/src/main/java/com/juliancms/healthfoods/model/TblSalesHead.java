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
public class TblSalesHead extends BaseModel {
    @Column
    @PrimaryKey
    int IdSalesHead;

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
    String NoDistrib;

    @Column
    String SalesInvoiceNumberP3;

    @Column
    int Status;

    @Column
    String DateV;

    @Column
    String DiscountCust;

    @Column(defaultValue = "30")
    int TermDays;

    @Column(defaultValue = "1")
    int PriceLevel;
}
