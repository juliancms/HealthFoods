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
public class TblProducts extends BaseModel {
    @Column
    @PrimaryKey
    String ItemID;

    @Column
    String ItemDescription;

    @Column
    String ItemClass;

    @Column
    String Inactive;

    @Column
    String SubjectToComission;

    @Column
    String DescriptionForSales;

    @Column
    String DescriptionForPurchases;

    @Column
    String SalesPrice1;

    @Column
    String SalesPrice1Calculation;

    @Column
    String SalesPrice1Rounding;

    @Column
    String SalesPrice1RoundingNumber;

    @Column
    String SalesPrice2;

    @Column
    String SalesPrice2Calculation;

    @Column
    String SalesPrice2Rounding;

    @Column
    String SalesPrice2RoundingNumber;

    @Column
    String SalesPrice3;

    @Column
    String SalesPrice3Calculation;

    @Column
    String SalesPrice3Rounding;

    @Column
    String SalesPrice3RoundingNumber;

    @Column
    String SalesPrice4;

    @Column
    String SalesPrice4Calculation;

    @Column
    String SalesPrice4Rounding;

    @Column
    String SalesPrice4RoundingNumber;

    @Column
    String SalesPrice5;

    @Column
    String SalesPrice5Calculation;

    @Column
    String SalesPrice5Rounding;

    @Column
    String SalesPrice5RoundingNumber;

    @Column
    String SalesPrice6;

    @Column
    String SalesPrice6Calculation;

    @Column
    String SalesPrice6Rounding;

    @Column
    String SalesPrice6RoundingNumber;

    @Column
    String SalesPrice7;

    @Column
    String SalesPrice7Calculation;

    @Column
    String SalesPrice7Rounding;

    @Column
    String SalesPrice7RoundingNumber;

    @Column
    String SalesPrice8;

    @Column
    String SalesPrice8Calculation;

    @Column
    String SalesPrice8Rounding;

    @Column
    String SalesPrice8RoundingNumber;

    @Column
    String SalesPrice9;

    @Column
    String SalesPrice9Calculation;

    @Column
    String SalesPrice9Rounding;

    @Column
    String SalesPrice9RoundingNumber;

    @Column
    String SalesPrice10;

    @Column
    String SalesPrice10Calculation;

    @Column
    String SalesPrice10Rounding;

    @Column
    String SalesPrice10RoundingNumber;

    @Column
    String QtyDiscountId;

    @Column
    String ItemTaxType;

    @Column
    String LastUnitCost;

    @Column
    String CostingMethod;

    @Column
    String GLSalesAccount;

    @Column
    String GlInventoryAccount;

    @Column
    String GLCOGSSalaryAcct;

    @Column
    String UPCSKU;

    @Column
    String ItemType;

    @Column
    String Location;

    @Column
    String StockingUM;

    @Column
    String UseMultiPacks;

    @Column
    String PurchasingUM;

    @Column
    String PurchasingUMDescription;

    @Column
    String PurchUMNoStockingUnits;

    @Column
    String PurchasingWeight;

    @Column
    String PurchasingUPCSCC;

    @Column
    String UsePurchasingUMDefault;

    @Column
    String SalesUM;

    @Column
    String SalesUMDescription;

    @Column
    String SalesUMNoStockingUnits;

    @Column
    String SalesWeight;

    @Column
    String SalesUPCSCC;

    @Column
    String UseSalesUMDefault;

    @Column
    String Weight;

    @Column
    String MinimumStock;

    @Column
    String ReorderQuantity;

    @Column
    String VendorID;

    @Column
    String BuyerID;

    @Column
    String AlternateVendor;

    @Column
    String Substitution;

    @Column
    String SpecialNote;

    @Column
    String Empty1;

    @Column
    String Empty2;

    @Column
    String MasterStockItemID;

    @Column
    String QuantitySalesOrders;

    @Column
    String QuantityPurchaseOrders;

    @Column
    String QuantityHand;

    @Column
    String IsTaxable;

    @Column
    String WarrantyPeriod;
}
