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

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    String ItemID;

    @Column
    String ItemDescription;

    @Column
    String ItemClass;

    @Column
    String Inactive;

    @Column
    String SubjectToCommission;

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

    @Column
    Integer ItemQuantity;

    public Long getId() { return id; }
    public String getItemID() { return ItemID; }
    public String getItemDescription() { return ItemDescription; }
    public String getItemClass() { return ItemClass; }
    public String getInactive() { return Inactive; }
    public String getSubjectToCommission() { return SubjectToCommission; }
    public String getDescriptionForSales() { return DescriptionForSales; }
    public String getDescriptionForPurchases() { return DescriptionForPurchases; }
    public String getSalesPrice1() { return SalesPrice1; }
    public String getSalesPrice1Calculation() { return SalesPrice1Calculation; }
    public String getSalesPrice1Rounding() { return SalesPrice1Rounding; }
    public String getSalesPrice1RoundingNumber() { return SalesPrice1RoundingNumber; }
    public String getSalesPrice2() { return SalesPrice2; }
    public String getSalesPrice2Calculation() { return SalesPrice2Calculation; }
    public String getSalesPrice2Rounding() { return SalesPrice2Rounding; }
    public String getSalesPrice2RoundingNumber() { return SalesPrice2RoundingNumber; }
    public String getSalesPrice3() { return SalesPrice3; }
    public String getSalesPrice3Calculation() { return SalesPrice3Calculation; }
    public String getSalesPrice3Rounding() { return SalesPrice3Rounding; }
    public String getSalesPrice3RoundingNumber() { return SalesPrice3RoundingNumber; }
    public String getSalesPrice4() { return SalesPrice4; }
    public String getSalesPrice4Calculation() { return SalesPrice4Calculation; }
    public String getSalesPrice4Rounding() { return SalesPrice4Rounding; }
    public String getSalesPrice4RoundingNumber() { return SalesPrice4RoundingNumber; }
    public String getSalesPrice5() { return SalesPrice5; }
    public String getSalesPrice5Calculation() { return SalesPrice5Calculation; }
    public String getSalesPrice5Rounding() { return SalesPrice5Rounding; }
    public String getSalesPrice5RoundingNumber() { return SalesPrice5RoundingNumber; }
    public String getSalesPrice6() { return SalesPrice6; }
    public String getSalesPrice6Calculation() { return SalesPrice6Calculation; }
    public String getSalesPrice6Rounding() { return SalesPrice6Rounding; }
    public String getSalesPrice6RoundingNumber() { return SalesPrice6RoundingNumber; }
    public String getSalesPrice7() { return SalesPrice7; }
    public String getSalesPrice7Calculation() { return SalesPrice7Calculation; }
    public String getSalesPrice7Rounding() { return SalesPrice7Rounding; }
    public String getSalesPrice7RoundingNumber() { return SalesPrice7RoundingNumber; }
    public String getSalesPrice8() { return SalesPrice8; }
    public String getSalesPrice8Calculation() { return SalesPrice8Calculation; }
    public String getSalesPrice8Rounding() { return SalesPrice8Rounding; }
    public String getSalesPrice8RoundingNumber() { return SalesPrice8RoundingNumber; }
    public String getSalesPrice9() { return SalesPrice9; }
    public String getSalesPrice9Calculation() { return SalesPrice9Calculation; }
    public String getSalesPrice9Rounding() { return SalesPrice9Rounding; }
    public String getSalesPrice9RoundingNumber() { return SalesPrice9RoundingNumber; }
    public String getSalesPrice10() { return SalesPrice10; }
    public String getSalesPrice10Calculation() { return SalesPrice10Calculation; }
    public String getSalesPrice10Rounding() { return SalesPrice10Rounding; }
    public String getSalesPrice10RoundingNumber() { return SalesPrice10RoundingNumber; }
    public String getQtyDiscountId() { return QtyDiscountId; }
    public String getItemTaxType() {
        Integer n = Integer.parseInt(ItemTaxType);
        return n.toString();
    }
    public String getLastUnitCost() { return LastUnitCost; }
    public String getCostingMethod() { return CostingMethod; }
    public String getGLSalesAccount() { return GLSalesAccount; }
    public String getGlInventoryAccount() { return GlInventoryAccount; }
    public String getGLCOGSSalaryAcct() { return GLCOGSSalaryAcct; }
    public String getUPCSKU() { return UPCSKU; }
    public String getItemType() { return ItemType; }
    public String getLocation() { return Location; }
    public String getStockingUM() { return StockingUM; }
    public String getUseMultiPacks() { return UseMultiPacks; }
    public String getPurchasingUM() { return PurchasingUM; }
    public String getPurchasingUMDescription() { return PurchasingUMDescription; }
    public String getPurchUMNoStockingUnits() { return PurchUMNoStockingUnits; }
    public String getPurchasingWeight() { return PurchasingWeight; }
    public String getPurchasingUPCSCC() { return PurchasingUPCSCC; }
    public String getUsePurchasingUMDefault() { return UsePurchasingUMDefault; }
    public String getSalesUM() { return SalesUM; }
    public String getSalesUMDescription() { return SalesUMDescription; }
    public String getSalesUMNoStockingUnits() { return SalesUMNoStockingUnits; }
    public String getSalesWeight() { return SalesWeight; }
    public String getSalesUPCSCC() { return SalesUPCSCC; }
    public String getUseSalesUMDefault() { return UseSalesUMDefault; }
    public String getWeight() { return Weight; }
    public String getMinimumStock() { return MinimumStock; }
    public String getReorderQuantity() { return ReorderQuantity; }
    public String getVendorID() { return VendorID; }
    public String getBuyerID() { return BuyerID; }
    public String getAlternateVendor() { return AlternateVendor; }
    public String getSubstitution() { return Substitution; }
    public String getSpecialNote() { return SpecialNote; }
    public String getEmpty1() { return Empty1; }
    public String getEmpty2() { return Empty2; }
    public String getMasterStockItemID() { return MasterStockItemID; }
    public String getQuantitySalesOrders() { return QuantitySalesOrders; }
    public String getQuantityPurchaseOrders() { return QuantityPurchaseOrders; }
    public String getQuantityHand() { return QuantityHand; }
    public String getIsTaxable() { return IsTaxable; }
    public String getWarrantyPeriod() { return WarrantyPeriod; }
    public Integer getItemQuantity() { return ItemQuantity; }
    public void setId(Long id){ this.id = id; }
    public void setItemID(String ItemID){ this.ItemID = ItemID; }
    public void setItemDescription(String ItemDescription){ this.ItemDescription = ItemDescription; }
    public void setItemClass(String ItemClass){ this.ItemClass = ItemClass; }
    public void setInactive(String Inactive){ this.Inactive = Inactive; }
    public void setSubjectToCommission(String SubjectToCommission){ this.SubjectToCommission = SubjectToCommission; }
    public void setDescriptionForSales(String DescriptionForSales){ this.DescriptionForSales = DescriptionForSales; }
    public void setDescriptionForPurchases(String DescriptionForPurchases){ this.DescriptionForPurchases = DescriptionForPurchases; }
    public void setSalesPrice1(String SalesPrice1){ this.SalesPrice1 = SalesPrice1; }
    public void setSalesPrice1Calculation(String SalesPrice1Calculation){ this.SalesPrice1Calculation = SalesPrice1Calculation; }
    public void setSalesPrice1Rounding(String SalesPrice1Rounding){ this.SalesPrice1Rounding = SalesPrice1Rounding; }
    public void setSalesPrice1RoundingNumber(String SalesPrice1RoundingNumber){ this.SalesPrice1RoundingNumber = SalesPrice1RoundingNumber; }
    public void setSalesPrice2(String SalesPrice2){ this.SalesPrice2 = SalesPrice2; }
    public void setSalesPrice2Calculation(String SalesPrice2Calculation){ this.SalesPrice2Calculation = SalesPrice2Calculation; }
    public void setSalesPrice2Rounding(String SalesPrice2Rounding){ this.SalesPrice2Rounding = SalesPrice2Rounding; }
    public void setSalesPrice2RoundingNumber(String SalesPrice2RoundingNumber){ this.SalesPrice2RoundingNumber = SalesPrice2RoundingNumber; }
    public void setSalesPrice3(String SalesPrice3){ this.SalesPrice3 = SalesPrice3; }
    public void setSalesPrice3Calculation(String SalesPrice3Calculation){ this.SalesPrice3Calculation = SalesPrice3Calculation; }
    public void setSalesPrice3Rounding(String SalesPrice3Rounding){ this.SalesPrice3Rounding = SalesPrice3Rounding; }
    public void setSalesPrice3RoundingNumber(String SalesPrice3RoundingNumber){ this.SalesPrice3RoundingNumber = SalesPrice3RoundingNumber; }
    public void setSalesPrice4(String SalesPrice4){ this.SalesPrice4 = SalesPrice4; }
    public void setSalesPrice4Calculation(String SalesPrice4Calculation){ this.SalesPrice4Calculation = SalesPrice4Calculation; }
    public void setSalesPrice4Rounding(String SalesPrice4Rounding){ this.SalesPrice4Rounding = SalesPrice4Rounding; }
    public void setSalesPrice4RoundingNumber(String SalesPrice4RoundingNumber){ this.SalesPrice4RoundingNumber = SalesPrice4RoundingNumber; }
    public void setSalesPrice5(String SalesPrice5){ this.SalesPrice5 = SalesPrice5; }
    public void setSalesPrice5Calculation(String SalesPrice5Calculation){ this.SalesPrice5Calculation = SalesPrice5Calculation; }
    public void setSalesPrice5Rounding(String SalesPrice5Rounding){ this.SalesPrice5Rounding = SalesPrice5Rounding; }
    public void setSalesPrice5RoundingNumber(String SalesPrice5RoundingNumber){ this.SalesPrice5RoundingNumber = SalesPrice5RoundingNumber; }
    public void setSalesPrice6(String SalesPrice6){ this.SalesPrice6 = SalesPrice6; }
    public void setSalesPrice6Calculation(String SalesPrice6Calculation){ this.SalesPrice6Calculation = SalesPrice6Calculation; }
    public void setSalesPrice6Rounding(String SalesPrice6Rounding){ this.SalesPrice6Rounding = SalesPrice6Rounding; }
    public void setSalesPrice6RoundingNumber(String SalesPrice6RoundingNumber){ this.SalesPrice6RoundingNumber = SalesPrice6RoundingNumber; }
    public void setSalesPrice7(String SalesPrice7){ this.SalesPrice7 = SalesPrice7; }
    public void setSalesPrice7Calculation(String SalesPrice7Calculation){ this.SalesPrice7Calculation = SalesPrice7Calculation; }
    public void setSalesPrice7Rounding(String SalesPrice7Rounding){ this.SalesPrice7Rounding = SalesPrice7Rounding; }
    public void setSalesPrice7RoundingNumber(String SalesPrice7RoundingNumber){ this.SalesPrice7RoundingNumber = SalesPrice7RoundingNumber; }
    public void setSalesPrice8(String SalesPrice8){ this.SalesPrice8 = SalesPrice8; }
    public void setSalesPrice8Calculation(String SalesPrice8Calculation){ this.SalesPrice8Calculation = SalesPrice8Calculation; }
    public void setSalesPrice8Rounding(String SalesPrice8Rounding){ this.SalesPrice8Rounding = SalesPrice8Rounding; }
    public void setSalesPrice8RoundingNumber(String SalesPrice8RoundingNumber){ this.SalesPrice8RoundingNumber = SalesPrice8RoundingNumber; }
    public void setSalesPrice9(String SalesPrice9){ this.SalesPrice9 = SalesPrice9; }
    public void setSalesPrice9Calculation(String SalesPrice9Calculation){ this.SalesPrice9Calculation = SalesPrice9Calculation; }
    public void setSalesPrice9Rounding(String SalesPrice9Rounding){ this.SalesPrice9Rounding = SalesPrice9Rounding; }
    public void setSalesPrice9RoundingNumber(String SalesPrice9RoundingNumber){ this.SalesPrice9RoundingNumber = SalesPrice9RoundingNumber; }
    public void setSalesPrice10(String SalesPrice10){ this.SalesPrice10 = SalesPrice10; }
    public void setSalesPrice10Calculation(String SalesPrice10Calculation){ this.SalesPrice10Calculation = SalesPrice10Calculation; }
    public void setSalesPrice10Rounding(String SalesPrice10Rounding){ this.SalesPrice10Rounding = SalesPrice10Rounding; }
    public void setSalesPrice10RoundingNumber(String SalesPrice10RoundingNumber){ this.SalesPrice10RoundingNumber = SalesPrice10RoundingNumber; }
    public void setQtyDiscountId(String QtyDiscountId){ this.QtyDiscountId = QtyDiscountId; }
    public void setItemTaxType(String ItemTaxType){ this.ItemTaxType = ItemTaxType; }
    public void setLastUnitCost(String LastUnitCost){ this.LastUnitCost = LastUnitCost; }
    public void setCostingMethod(String CostingMethod){ this.CostingMethod = CostingMethod; }
    public void setGLSalesAccount(String GLSalesAccount){ this.GLSalesAccount = GLSalesAccount; }
    public void setGlInventoryAccount(String GlInventoryAccount){ this.GlInventoryAccount = GlInventoryAccount; }
    public void setGLCOGSSalaryAcct(String GLCOGSSalaryAcct){ this.GLCOGSSalaryAcct = GLCOGSSalaryAcct; }
    public void setUPCSKU(String UPCSKU){ this.UPCSKU = UPCSKU; }
    public void setItemType(String ItemType){ this.ItemType = ItemType; }
    public void setLocation(String Location){ this.Location = Location; }
    public void setStockingUM(String StockingUM){ this.StockingUM = StockingUM; }
    public void setUseMultiPacks(String UseMultiPacks){ this.UseMultiPacks = UseMultiPacks; }
    public void setPurchasingUM(String PurchasingUM){ this.PurchasingUM = PurchasingUM; }
    public void setPurchasingUMDescription(String PurchasingUMDescription){ this.PurchasingUMDescription = PurchasingUMDescription; }
    public void setPurchUMNoStockingUnits(String PurchUMNoStockingUnits){ this.PurchUMNoStockingUnits = PurchUMNoStockingUnits; }
    public void setPurchasingWeight(String PurchasingWeight){ this.PurchasingWeight = PurchasingWeight; }
    public void setPurchasingUPCSCC(String PurchasingUPCSCC){ this.PurchasingUPCSCC = PurchasingUPCSCC; }
    public void setUsePurchasingUMDefault(String UsePurchasingUMDefault){ this.UsePurchasingUMDefault = UsePurchasingUMDefault; }
    public void setSalesUM(String SalesUM){ this.SalesUM = SalesUM; }
    public void setSalesUMDescription(String SalesUMDescription){ this.SalesUMDescription = SalesUMDescription; }
    public void setSalesUMNoStockingUnits(String SalesUMNoStockingUnits){ this.SalesUMNoStockingUnits = SalesUMNoStockingUnits; }
    public void setSalesWeight(String SalesWeight){ this.SalesWeight = SalesWeight; }
    public void setSalesUPCSCC(String SalesUPCSCC){ this.SalesUPCSCC = SalesUPCSCC; }
    public void setUseSalesUMDefault(String UseSalesUMDefault){ this.UseSalesUMDefault = UseSalesUMDefault; }
    public void setWeight(String Weight){ this.Weight = Weight; }
    public void setMinimumStock(String MinimumStock){ this.MinimumStock = MinimumStock; }
    public void setReorderQuantity(String ReorderQuantity){ this.ReorderQuantity = ReorderQuantity; }
    public void setVendorID(String VendorID){ this.VendorID = VendorID; }
    public void setBuyerID(String BuyerID){ this.BuyerID = BuyerID; }
    public void setAlternateVendor(String AlternateVendor){ this.AlternateVendor = AlternateVendor; }
    public void setSubstitution(String Substitution){ this.Substitution = Substitution; }
    public void setSpecialNote(String SpecialNote){ this.SpecialNote = SpecialNote; }
    public void setEmpty1(String Empty1){ this.Empty1 = Empty1; }
    public void setEmpty2(String Empty2){ this.Empty2 = Empty2; }
    public void setMasterStockItemID(String MasterStockItemID){ this.MasterStockItemID = MasterStockItemID; }
    public void setQuantitySalesOrders(String QuantitySalesOrders){ this.QuantitySalesOrders = QuantitySalesOrders; }
    public void setQuantityPurchaseOrders(String QuantityPurchaseOrders){ this.QuantityPurchaseOrders = QuantityPurchaseOrders; }
    public void setQuantityHand(String QuantityHand){ this.QuantityHand = QuantityHand; }
    public void setIsTaxable(String IsTaxable){ this.IsTaxable = IsTaxable; }
    public void setWarrantyPeriod(String WarrantyPeriod){ this.WarrantyPeriod = WarrantyPeriod; }
    public void setItemQuantity(Integer ItemQuantity){ this.ItemQuantity = ItemQuantity; }
    public void setField(Integer i, String field){
        switch (i){
            case 0: this.ItemID = field; break;
            case 1: this.ItemDescription = field; break;
            case 2: this.ItemClass = field; break;
            case 3: this.Inactive = field; break;
            case 4: this.SubjectToCommission = field; break;
            case 5: this.DescriptionForSales = field; break;
            case 6: this.DescriptionForPurchases = field; break;
            case 7: this.SalesPrice1 = field; break;
            case 8: this.SalesPrice1Calculation = field; break;
            case 9: this.SalesPrice1Rounding = field; break;
            case 10: this.SalesPrice1RoundingNumber = field; break;
            case 11: this.SalesPrice2 = field; break;
            case 12: this.SalesPrice2Calculation = field; break;
            case 13: this.SalesPrice2Rounding = field; break;
            case 14: this.SalesPrice2RoundingNumber = field; break;
            case 15: this.SalesPrice3 = field; break;
            case 16: this.SalesPrice3Calculation = field; break;
            case 17: this.SalesPrice3Rounding = field; break;
            case 18: this.SalesPrice3RoundingNumber = field; break;
            case 19: this.SalesPrice4 = field; break;
            case 20: this.SalesPrice4Calculation = field; break;
            case 21: this.SalesPrice4Rounding = field; break;
            case 22: this.SalesPrice4RoundingNumber = field; break;
            case 23: this.SalesPrice5 = field; break;
            case 24: this.SalesPrice5Calculation = field; break;
            case 25: this.SalesPrice5Rounding = field; break;
            case 26: this.SalesPrice5RoundingNumber = field; break;
            case 27: this.SalesPrice6 = field; break;
            case 28: this.SalesPrice6Calculation = field; break;
            case 29: this.SalesPrice6Rounding = field; break;
            case 30: this.SalesPrice6RoundingNumber = field; break;
            case 31: this.SalesPrice7 = field; break;
            case 32: this.SalesPrice7Calculation = field; break;
            case 33: this.SalesPrice7Rounding = field; break;
            case 34: this.SalesPrice7RoundingNumber = field; break;
            case 35: this.SalesPrice8 = field; break;
            case 36: this.SalesPrice8Calculation = field; break;
            case 37: this.SalesPrice8Rounding = field; break;
            case 38: this.SalesPrice8RoundingNumber = field; break;
            case 39: this.SalesPrice9 = field; break;
            case 40: this.SalesPrice9Calculation = field; break;
            case 41: this.SalesPrice9Rounding = field; break;
            case 42: this.SalesPrice9RoundingNumber = field; break;
            case 43: this.SalesPrice10 = field; break;
            case 44: this.SalesPrice10Calculation = field; break;
            case 45: this.SalesPrice10Rounding = field; break;
            case 46: this.SalesPrice10RoundingNumber = field; break;
            case 47: this.QtyDiscountId = field; break;
            case 48: this.ItemTaxType = field; break;
            case 49: this.LastUnitCost = field; break;
            case 50: this.CostingMethod = field; break;
            case 51: this.GLSalesAccount = field; break;
            case 52: this.GlInventoryAccount = field; break;
            case 53: this.GLCOGSSalaryAcct = field; break;
            case 54: this.UPCSKU = field; break;
            case 55: this.ItemType = field; break;
            case 56: this.Location = field; break;
            case 57: this.StockingUM = field; break;
            case 58: this.UseMultiPacks = field; break;
            case 59: this.PurchasingUM = field; break;
            case 60: this.PurchasingUMDescription = field; break;
            case 61: this.PurchUMNoStockingUnits = field; break;
            case 62: this.PurchasingWeight = field; break;
            case 63: this.PurchasingUPCSCC = field; break;
            case 64: this.UsePurchasingUMDefault = field; break;
            case 65: this.SalesUM = field; break;
            case 66: this.SalesUMDescription = field; break;
            case 67: this.SalesUMNoStockingUnits = field; break;
            case 68: this.SalesWeight = field; break;
            case 69: this.SalesUPCSCC = field; break;
            case 70: this.UseSalesUMDefault = field; break;
            case 71: this.Weight = field; break;
            case 72: this.MinimumStock = field; break;
            case 73: this.ReorderQuantity = field; break;
            case 74: this.VendorID = field; break;
            case 75: this.BuyerID = field; break;
            case 76: this.AlternateVendor = field; break;
            case 77: this.Substitution = field; break;
            case 78: this.SpecialNote = field; break;
            case 79: this.Empty1 = field; break;
            case 80: this.Empty2 = field; break;
            case 81: this.MasterStockItemID = field; break;
            case 82: this.QuantitySalesOrders = field; break;
            case 83: this.QuantityPurchaseOrders = field; break;
            case 84: this.QuantityHand = field; break;
            case 85: this.IsTaxable = field; break;
            case 86: this.WarrantyPeriod = field; break;
        }
    }
}
