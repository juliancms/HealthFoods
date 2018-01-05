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
public class TblCustomers extends BaseModel {
    @Column
    @PrimaryKey
    String IdCustomersKey;

    @Column
    String IdCustomers;

    @Column
    String NameCustomer;

    @Column(defaultValue = "\"FALSE\"")
    String Inactive;

    @Column
    String BilltoContactFirstName;

    @Column
    String BilltoContactLastName;

    @Column
    String BilltoAddressLineOne;

    @Column
    String BilltoAddressLineTwo;

    @Column
    String BilltoCity;

    @Column
    String BilltoCountry;

    @Column
    String AddressCustomer;

    @Column
    String CustomerType;

    @Column
    String TelephoneCustomer;

    @Column
    String Telephone2;

    @Column
    String FaxNumber;

    @Column
    String EmailCustomer;

    @Column
    String GLSalesAccount;

    @Column
    String PricingLevel;

    @Column
    String UseStandardTerms;

    @Column
    String CODTerms;

    @Column
    String PrepaidTerms;

    @Column
    String TermsType;

    @Column
    int DueDays;

    @Column
    String DiscountPercent;

    @Column
    String CreditLimit;

    @Column
    String CreditStatus;

    @Column
    String CustomerPaymentMethod;

    @Column
    String LastInvoiceDate;

    @Column
    String LastInvoiceAmount;

    @Column
    String LastPaymentDate;

    @Column
    String LastPaymentAmount;

    @Column
    String CurrentBalance;

    @Column
    String Note;

    @Column
    String DiscountCust0;

    @Column
    String DiscountCust;

    @Column
    String TermDays;

    @Column
    String CustomerStatus;

}
