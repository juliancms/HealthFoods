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
//    @PrimaryKey(autoincrement = true)
//    public long id;

    @PrimaryKey
    String CustomerID;

    @Column
    String CustomerName;

    @Column
    String Prospect;

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
    String BilltoState;

    @Column
    String BilltoZip;

    @Column
    String BilltoCountry;

    @Column
    String BilltoSalesTaxID;

    @Column
    String ShiptoAddress1LineOne;

    @Column
    String ShiptoAddress1LineTwo;

    @Column
    String ShiptoCity1;

    @Column
    String ShiptoState1;

    @Column
    String ShiptoZipcode1;

    @Column
    String ShiptoCountry1;

    @Column
    String ShiptoSalesTaxID1;

    @Column
    String ShiptoAddress2LineOne;

    @Column
    String ShiptoAddress2LineTwo;

    @Column
    String CustomerType;

    @Column
    String Telephone1;

    @Column
    String Telephone2;

    @Column
    String FaxNumber;

    @Column
    String CustomerEmail;

    @Column
    String SalesRepresentativeID;

    @Column
    String AccountNumber;

    @Column
    String GLSalesAccount;

    @Column
    String OpenPurchaseOrderNumber;

    @Column
    String ShipVia;

    @Column
    String ResaleNumber;

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
    String DueDays;

    @Column
    String DiscountDays;

    @Column
    String DiscountPercent;

    @Column
    String CreditLimit;

    @Column
    String CreditStatus;

    @Column
    String ChargeFinanceCharges;

    @Column
    String DueMonthEndTerms;

    @Column
    String FormDeliveryMethod;

    @Column
    String IncludeSalesRep;

    @Column
    String CardholdersName;

    @Column
    String CreditCardAddressLine1;

    @Column
    String CreditCardAddressLine2;

    @Column
    String CreditCardCity;

    @Column
    String CreditCardState;

    @Column
    String CreditCardZipCode;

    @Column
    String CreditCardCountry;

    @Column
    String CreditCardNumber;

    @Column
    String CreditCardExpirationDate;

    @Column
    String UseReceiptSettings;

    @Column
    String CustomerPaymentMethod;

    @Column
    String CustomerCashAccount;

    @Column
    String SecondContact;

    @Column
    String DiscountDS;

    @Column
    String MailingList;

    @Column
    String MultipleSites;

    @Column
    String CustomerStatus;

    @Column
    String CustomerSinceDate;

    @Column
    String LastInvoiceDate;

    @Column
    String LastInvoiceAmount;

    @Column
    String LastPaymentDate;

    @Column
    String LastPaymentAmount;

    @Column
    String LastStatementDate;

    @Column
    String CurrentBalance;

    @Column
    String CustomerWebSite;

    @Column
    String IDReplacement;

//    public Long getid() { return id; }
    public String getCustomerID() { return CustomerID; }
    public String getCustomerName() { return CustomerName; }
    public String getProspect() { return Prospect; }
    public String getInactive() { return Inactive; }
    public String getBilltoContactFirstName() { return BilltoContactFirstName; }
    public String getBilltoContactLastName() { return BilltoContactLastName; }
    public String getBilltoAddressLineOne() { return BilltoAddressLineOne; }
    public String getBilltoAddressLineTwo() { return BilltoAddressLineTwo; }
    public String getBilltoCity() { return BilltoCity; }
    public String getBilltoState() { return BilltoState; }
    public String getBilltoZip() { return BilltoZip; }
    public String getBilltoCountry() { return BilltoCountry; }
    public String getBilltoSalesTaxID() { return BilltoSalesTaxID; }
    public String getShiptoAddress1LineOne() { return ShiptoAddress1LineOne; }
    public String getShiptoAddress1LineTwo() { return ShiptoAddress1LineTwo; }
    public String getShiptoCity1() { return ShiptoCity1; }
    public String getShiptoState1() { return ShiptoState1; }
    public String getShiptoZipcode1() { return ShiptoZipcode1; }
    public String getShiptoCountry1() { return ShiptoCountry1; }
    public String getShiptoSalesTaxID1() { return ShiptoSalesTaxID1; }
    public String getShiptoAddress2LineOne() { return ShiptoAddress2LineOne; }
    public String getShiptoAddress2LineTwo() { return ShiptoAddress2LineTwo; }
    public String getCustomerType() { return CustomerType; }
    public String getTelephone1() { return Telephone1; }
    public String getTelephone2() { return Telephone2; }
    public String getFaxNumber() { return FaxNumber; }
    public String getCustomerEmail() { return CustomerEmail; }
    public String getSalesRepresentativeID() { return SalesRepresentativeID; }
    public String getAccountNumber() { return AccountNumber; }
    public String getGLSalesAccount() { return GLSalesAccount; }
    public String getOpenPurchaseOrderNumber() { return OpenPurchaseOrderNumber; }
    public String getShipVia() { return ShipVia; }
    public String getResaleNumber() { return ResaleNumber; }
    public String getPricingLevel() { return PricingLevel; }
    public String getUseStandardTerms() { return UseStandardTerms; }
    public String getCODTerms() { return CODTerms; }
    public String getPrepaidTerms() { return PrepaidTerms; }
    public String getTermsType() { return TermsType; }
    public String getDueDays() { return DueDays; }
    public String getDiscountDays() { return DiscountDays; }
    public String getDiscountPercent() { return DiscountPercent; }
    public String getCreditLimit() { return CreditLimit; }
    public String getCreditStatus() { return CreditStatus; }
    public String getChargeFinanceCharges() { return ChargeFinanceCharges; }
    public String getDueMonthEndTerms() { return DueMonthEndTerms; }
    public String getFormDeliveryMethod() { return FormDeliveryMethod; }
    public String getIncludeSalesRep() { return IncludeSalesRep; }
    public String getCardholdersName() { return CardholdersName; }
    public String getCreditCardAddressLine1() { return CreditCardAddressLine1; }
    public String getCreditCardAddressLine2() { return CreditCardAddressLine2; }
    public String getCreditCardCity() { return CreditCardCity; }
    public String getCreditCardState() { return CreditCardState; }
    public String getCreditCardZipCode() { return CreditCardZipCode; }
    public String getCreditCardCountry() { return CreditCardCountry; }
    public String getCreditCardNumber() { return CreditCardNumber; }
    public String getCreditCardExpirationDate() { return CreditCardExpirationDate; }
    public String getUseReceiptSettings() { return UseReceiptSettings; }
    public String getCustomerPaymentMethod() { return CustomerPaymentMethod; }
    public String getCustomerCashAccount() { return CustomerCashAccount; }
    public String getSecondContact() { return SecondContact; }
    public String getDiscountDS() { return DiscountDS; }
    public String getMailingList() { return MailingList; }
    public String getMultipleSites() { return MultipleSites; }
    public String getCustomerStatus() { return CustomerStatus; }
    public String getCustomerSinceDate() { return CustomerSinceDate; }
    public String getLastInvoiceDate() { return LastInvoiceDate; }
    public String getLastInvoiceAmount() { return LastInvoiceAmount; }
    public String getLastPaymentDate() { return LastPaymentDate; }
    public String getLastPaymentAmount() { return LastPaymentAmount; }
    public String getLastStatementDate() { return LastStatementDate; }
    public String getCurrentBalance() { return CurrentBalance; }
    public String getCustomerWebSite() { return CustomerWebSite; }
    public String getIDReplacement() { return IDReplacement; }
//    public void setid(Long id){ this.id = id; }
    public void setCustomerID(String CustomerID){ this.CustomerID = CustomerID; }
    public void setCustomerName(String CustomerName){ this.CustomerName = CustomerName; }
    public void setProspect(String Prospect){ this.Prospect = Prospect; }
    public void setInactive(String Inactive){ this.Inactive = Inactive; }
    public void setBilltoContactFirstName(String BilltoContactFirstName){ this.BilltoContactFirstName = BilltoContactFirstName; }
    public void setBilltoContactLastName(String BilltoContactLastName){ this.BilltoContactLastName = BilltoContactLastName; }
    public void setBilltoAddressLineOne(String BilltoAddressLineOne){ this.BilltoAddressLineOne = BilltoAddressLineOne; }
    public void setBilltoAddressLineTwo(String BilltoAddressLineTwo){ this.BilltoAddressLineTwo = BilltoAddressLineTwo; }
    public void setBilltoCity(String BilltoCity){ this.BilltoCity = BilltoCity; }
    public void setBilltoState(String BilltoState){ this.BilltoState = BilltoState; }
    public void setBilltoZip(String BilltoZip){ this.BilltoZip = BilltoZip; }
    public void setBilltoCountry(String BilltoCountry){ this.BilltoCountry = BilltoCountry; }
    public void setBilltoSalesTaxID(String BilltoSalesTaxID){ this.BilltoSalesTaxID = BilltoSalesTaxID; }
    public void setShiptoAddress1LineOne(String ShiptoAddress1LineOne){ this.ShiptoAddress1LineOne = ShiptoAddress1LineOne; }
    public void setShiptoAddress1LineTwo(String ShiptoAddress1LineTwo){ this.ShiptoAddress1LineTwo = ShiptoAddress1LineTwo; }
    public void setShiptoCity1(String ShiptoCity1){ this.ShiptoCity1 = ShiptoCity1; }
    public void setShiptoState1(String ShiptoState1){ this.ShiptoState1 = ShiptoState1; }
    public void setShiptoZipcode1(String ShiptoZipcode1){ this.ShiptoZipcode1 = ShiptoZipcode1; }
    public void setShiptoCountry1(String ShiptoCountry1){ this.ShiptoCountry1 = ShiptoCountry1; }
    public void setShiptoSalesTaxID1(String ShiptoSalesTaxID1){ this.ShiptoSalesTaxID1 = ShiptoSalesTaxID1; }
    public void setShiptoAddress2LineOne(String ShiptoAddress2LineOne){ this.ShiptoAddress2LineOne = ShiptoAddress2LineOne; }
    public void setShiptoAddress2LineTwo(String ShiptoAddress2LineTwo){ this.ShiptoAddress2LineTwo = ShiptoAddress2LineTwo; }
    public void setCustomerType(String CustomerType){ this.CustomerType = CustomerType; }
    public void setTelephone1(String Telephone1){ this.Telephone1 = Telephone1; }
    public void setTelephone2(String Telephone2){ this.Telephone2 = Telephone2; }
    public void setFaxNumber(String FaxNumber){ this.FaxNumber = FaxNumber; }
    public void setCustomerEmail(String CustomerEmail){ this.CustomerEmail = CustomerEmail; }
    public void setSalesRepresentativeID(String SalesRepresentativeID){ this.SalesRepresentativeID = SalesRepresentativeID; }
    public void setAccountNumber(String AccountNumber){ this.AccountNumber = AccountNumber; }
    public void setGLSalesAccount(String GLSalesAccount){ this.GLSalesAccount = GLSalesAccount; }
    public void setOpenPurchaseOrderNumber(String OpenPurchaseOrderNumber){ this.OpenPurchaseOrderNumber = OpenPurchaseOrderNumber; }
    public void setShipVia(String ShipVia){ this.ShipVia = ShipVia; }
    public void setResaleNumber(String ResaleNumber){ this.ResaleNumber = ResaleNumber; }
    public void setPricingLevel(String PricingLevel){ this.PricingLevel = PricingLevel; }
    public void setUseStandardTerms(String UseStandardTerms){ this.UseStandardTerms = UseStandardTerms; }
    public void setCODTerms(String CODTerms){ this.CODTerms = CODTerms; }
    public void setPrepaidTerms(String PrepaidTerms){ this.PrepaidTerms = PrepaidTerms; }
    public void setTermsType(String TermsType){ this.TermsType = TermsType; }
    public void setDueDays(String DueDays){ this.DueDays = DueDays; }
    public void setDiscountDays(String DiscountDays){ this.DiscountDays = DiscountDays; }
    public void setDiscountPercent(String DiscountPercent){ this.DiscountPercent = DiscountPercent; }
    public void setCreditLimit(String CreditLimit){ this.CreditLimit = CreditLimit; }
    public void setCreditStatus(String CreditStatus){ this.CreditStatus = CreditStatus; }
    public void setChargeFinanceCharges(String ChargeFinanceCharges){ this.ChargeFinanceCharges = ChargeFinanceCharges; }
    public void setDueMonthEndTerms(String DueMonthEndTerms){ this.DueMonthEndTerms = DueMonthEndTerms; }
    public void setFormDeliveryMethod(String FormDeliveryMethod){ this.FormDeliveryMethod = FormDeliveryMethod; }
    public void setIncludeSalesRep(String IncludeSalesRep){ this.IncludeSalesRep = IncludeSalesRep; }
    public void setCardholdersName(String CardholdersName){ this.CardholdersName = CardholdersName; }
    public void setCreditCardAddressLine1(String CreditCardAddressLine1){ this.CreditCardAddressLine1 = CreditCardAddressLine1; }
    public void setCreditCardAddressLine2(String CreditCardAddressLine2){ this.CreditCardAddressLine2 = CreditCardAddressLine2; }
    public void setCreditCardCity(String CreditCardCity){ this.CreditCardCity = CreditCardCity; }
    public void setCreditCardState(String CreditCardState){ this.CreditCardState = CreditCardState; }
    public void setCreditCardZipCode(String CreditCardZipCode){ this.CreditCardZipCode = CreditCardZipCode; }
    public void setCreditCardCountry(String CreditCardCountry){ this.CreditCardCountry = CreditCardCountry; }
    public void setCreditCardNumber(String CreditCardNumber){ this.CreditCardNumber = CreditCardNumber; }
    public void setCreditCardExpirationDate(String CreditCardExpirationDate){ this.CreditCardExpirationDate = CreditCardExpirationDate; }
    public void setUseReceiptSettings(String UseReceiptSettings){ this.UseReceiptSettings = UseReceiptSettings; }
    public void setCustomerPaymentMethod(String CustomerPaymentMethod){ this.CustomerPaymentMethod = CustomerPaymentMethod; }
    public void setCustomerCashAccount(String CustomerCashAccount){ this.CustomerCashAccount = CustomerCashAccount; }
    public void setSecondContact(String SecondContact){ this.SecondContact = SecondContact; }
    public void setDiscountDS(String DiscountDS){ this.DiscountDS = DiscountDS; }
    public void setMailingList(String MailingList){ this.MailingList = MailingList; }
    public void setMultipleSites(String MultipleSites){ this.MultipleSites = MultipleSites; }
    public void setCustomerStatus(String CustomerStatus){ this.CustomerStatus = CustomerStatus; }
    public void setCustomerSinceDate(String CustomerSinceDate){ this.CustomerSinceDate = CustomerSinceDate; }
    public void setLastInvoiceDate(String LastInvoiceDate){ this.LastInvoiceDate = LastInvoiceDate; }
    public void setLastInvoiceAmount(String LastInvoiceAmount){ this.LastInvoiceAmount = LastInvoiceAmount; }
    public void setLastPaymentDate(String LastPaymentDate){ this.LastPaymentDate = LastPaymentDate; }
    public void setLastPaymentAmount(String LastPaymentAmount){ this.LastPaymentAmount = LastPaymentAmount; }
    public void setLastStatementDate(String LastStatementDate){ this.LastStatementDate = LastStatementDate; }
    public void setCurrentBalance(String CurrentBalance){ this.CurrentBalance = CurrentBalance; }
    public void setCustomerWebSite(String CustomerWebSite){ this.CustomerWebSite = CustomerWebSite; }
    public void setIDReplacement(String IDReplacement){ this.IDReplacement = IDReplacement; }
    public void setField(Integer i, String field){
        switch (i){
            case 0: this.CustomerID = field; break;
            case 1: this.CustomerName = field; break;
            case 2: this.Prospect = field; break;
            case 3: this.Inactive = field; break;
            case 4: this.BilltoContactFirstName = field; break;
            case 5: this.BilltoContactLastName = field; break;
            case 6: this.BilltoAddressLineOne = field; break;
            case 7: this.BilltoAddressLineTwo = field; break;
            case 8: this.BilltoCity = field; break;
            case 9: this.BilltoState = field; break;
            case 10: this.BilltoZip = field; break;
            case 11: this.BilltoCountry = field; break;
            case 12: this.BilltoSalesTaxID = field; break;
            case 13: this.ShiptoAddress1LineOne = field; break;
            case 14: this.ShiptoAddress1LineTwo = field; break;
            case 15: this.ShiptoCity1 = field; break;
            case 16: this.ShiptoState1 = field; break;
            case 17: this.ShiptoZipcode1 = field; break;
            case 18: this.ShiptoCountry1 = field; break;
            case 19: this.ShiptoSalesTaxID1 = field; break;
            case 20: this.ShiptoAddress2LineOne = field; break;
            case 21: this.ShiptoAddress2LineTwo = field; break;
            case 22: this.CustomerType = field; break;
            case 23: this.Telephone1 = field; break;
            case 24: this.Telephone2 = field; break;
            case 25: this.FaxNumber = field; break;
            case 26: this.CustomerEmail = field; break;
            case 27: this.SalesRepresentativeID = field; break;
            case 28: this.AccountNumber = field; break;
            case 29: this.GLSalesAccount = field; break;
            case 30: this.OpenPurchaseOrderNumber = field; break;
            case 31: this.ShipVia = field; break;
            case 32: this.ResaleNumber = field; break;
            case 33: this.PricingLevel = field; break;
            case 34: this.UseStandardTerms = field; break;
            case 35: this.CODTerms = field; break;
            case 36: this.PrepaidTerms = field; break;
            case 37: this.TermsType = field; break;
            case 38: this.DueDays = field; break;
            case 39: this.DiscountDays = field; break;
            case 40: this.DiscountPercent = field; break;
            case 41: this.CreditLimit = field; break;
            case 42: this.CreditStatus = field; break;
            case 43: this.ChargeFinanceCharges = field; break;
            case 44: this.DueMonthEndTerms = field; break;
            case 45: this.FormDeliveryMethod = field; break;
            case 46: this.IncludeSalesRep = field; break;
            case 47: this.CardholdersName = field; break;
            case 48: this.CreditCardAddressLine1 = field; break;
            case 49: this.CreditCardAddressLine2 = field; break;
            case 50: this.CreditCardCity = field; break;
            case 51: this.CreditCardState = field; break;
            case 52: this.CreditCardZipCode = field; break;
            case 53: this.CreditCardCountry = field; break;
            case 54: this.CreditCardNumber = field; break;
            case 55: this.CreditCardExpirationDate = field; break;
            case 56: this.UseReceiptSettings = field; break;
            case 57: this.CustomerPaymentMethod = field; break;
            case 58: this.CustomerCashAccount = field; break;
            case 59: this.SecondContact = field; break;
            case 60: this.DiscountDS = field; break;
            case 61: this.MailingList = field; break;
            case 62: this.MultipleSites = field; break;
            case 63: this.CustomerStatus = field; break;
            case 64: this.CustomerSinceDate = field; break;
            case 65: this.LastInvoiceDate = field; break;
            case 66: this.LastInvoiceAmount = field; break;
            case 67: this.LastPaymentDate = field; break;
            case 68: this.LastPaymentAmount = field; break;
            case 69: this.LastStatementDate = field; break;
            case 70: this.CurrentBalance = field; break;
            case 71: this.CustomerWebSite = field; break;
            case 72: this.IDReplacement = field; break;
        }
    }
}
