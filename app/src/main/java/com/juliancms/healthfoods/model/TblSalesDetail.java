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
public class TblSalesDetail extends BaseModel {
    @Column
    @PrimaryKey
    int IdSalesDetail;

    @Column
    int IdSalesHead;

    @Column
    String CodeProducts;

    @Column
    int QuantityS;

    @Column
    String UnitPriceS;

    @Column
    String VatS;

    @Column
    String DiscountS;

    @Column
    String VatP3;
}
