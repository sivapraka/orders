package com.demo.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by admin on 23-Mar-18.
 */

public class InvoiceDataSource implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("erp_id")
    @Expose
    private String erpId;
    @SerializedName("tripID")
    @Expose
    private String tripID;
    @SerializedName("bill_no")
    @Expose
    private String billNo;
    @SerializedName("bill_date")
    @Expose
    private String billDate;
    @SerializedName("customer_code")
    @Expose
    private String customerCode;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("company_code")
    @Expose
    private String companyCode;
    @SerializedName("plant_code")
    @Expose
    private String plantCode;
    @SerializedName("salesorg_code")
    @Expose
    private String salesorgCode;
    @SerializedName("salesarea_code")
    @Expose
    private String salesareaCode;
    @SerializedName("supervisor_code")
    @Expose
    private String supervisorCode;
    @SerializedName("item_count")
    @Expose
    private String itemCount;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("sub_total")
    @Expose
    private String subTotal;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("grand_total")
    @Expose
    private String grandTotal;
    @SerializedName("round_up")
    @Expose
    private String roundUp;
    @SerializedName("net_amount")
    @Expose
    private String netAmount;
    @SerializedName("received")
    @Expose
    private String received;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("bill_status")
    @Expose
    private String billStatus;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("manual_billing")
    @Expose
    private String manualBilling;
    @SerializedName("randomkey")
    @Expose
    private String randomkey;
    @SerializedName("bill_key")
    @Expose
    private String billKey;
    @SerializedName("manual_bill_no")
    @Expose
    private String manualBillNo;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("entered_by")
    @Expose
    private String enteredBy;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;

    @SerializedName("bill_id")
    @Expose
    private String billId;
    @SerializedName("sgst_tax")
    @Expose
    private String sgstTax;
    @SerializedName("cgst_tax")
    @Expose
    private String cgstTax;
    @SerializedName("igst_tax")
    @Expose
    private String igstTax;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("isOnline")
    @Expose
    private String isOnline;

    /**
     * No args constructor for use in serialization
     */
    public InvoiceDataSource() {
    }

    /**
     * @param tripID
     * @param modifiedBy
     * @param received
     * @param billStatus
     * @param plantCode
     * @param id
     * @param salesorgCode
     * @param balance
     * @param subTotal
     * @param supervisorCode
     * @param billNo
     * @param customerCode
     * @param value
     * @param longitude
     * @param roundUp
     * @param manualBillNo
     * @param customerName
     * @param erpId
     * @param billKey
     * @param randomkey
     * @param billDate
     * @param grandTotal
     * @param companyCode
     * @param discount
     * @param createdOn
     * @param salesareaCode
     * @param modifiedOn
     * @param enteredBy
     * @param tax
     * @param manualBilling
     * @param latitude
     * @param itemCount
     * @param comments
     * @param netAmount
     */
    public InvoiceDataSource(String id, String erpId, String tripID, String billNo, String billDate,
                             String customerCode, String customerName, String companyCode, String plantCode,
                             String salesorgCode, String salesareaCode, String supervisorCode, String itemCount,
                             String value, String subTotal, String discount, String tax, String grandTotal,
                             String roundUp, String netAmount, String received, String balance,
                             String billStatus, String comments, String manualBilling, String randomkey,
                             String billKey, String manualBillNo, String latitude, String longitude,
                             String enteredBy, String createdOn, String modifiedBy, String modifiedOn,
                             String isOnline) {
        super();
        this.id = id;
        this.erpId = erpId;
        this.tripID = tripID;
        this.billNo = billNo;
        this.billDate = billDate;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.companyCode = companyCode;
        this.plantCode = plantCode;
        this.salesorgCode = salesorgCode;
        this.salesareaCode = salesareaCode;
        this.supervisorCode = supervisorCode;
        this.itemCount = itemCount;
        this.value = value;
        this.subTotal = subTotal;
        this.discount = discount;
        this.tax = tax;
        this.grandTotal = grandTotal;
        this.roundUp = roundUp;
        this.netAmount = netAmount;
        this.received = received;
        this.balance = balance;
        this.billStatus = billStatus;
        this.comments = comments;
        this.manualBilling = manualBilling;
        this.randomkey = randomkey;
        this.billKey = billKey;
        this.manualBillNo = manualBillNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.enteredBy = enteredBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
//            this.billId=billId;
//            this.sgstTax=sgstTax;
//            this.cgstTax=cgstTax;
//            this.igstTax=igstTax;
//            this.totalTax=totalTax;
        this.isOnline = isOnline;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getSgstTax() {
        return sgstTax;
    }

    public void setSgstTax(String sgstTax) {
        this.sgstTax = sgstTax;
    }

    public String getCgstTax() {
        return cgstTax;
    }

    public void setCgstTax(String cgstTax) {
        this.cgstTax = cgstTax;
    }

    public String getIgstTax() {
        return igstTax;
    }

    public void setIgstTax(String igstTax) {
        this.igstTax = igstTax;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getSalesorgCode() {
        return salesorgCode;
    }

    public void setSalesorgCode(String salesorgCode) {
        this.salesorgCode = salesorgCode;
    }

    public String getSalesareaCode() {
        return salesareaCode;
    }

    public void setSalesareaCode(String salesareaCode) {
        this.salesareaCode = salesareaCode;
    }

    public String getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getRoundUp() {
        return roundUp;
    }

    public void setRoundUp(String roundUp) {
        this.roundUp = roundUp;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getManualBilling() {
        return manualBilling;
    }

    public void setManualBilling(String manualBilling) {
        this.manualBilling = manualBilling;
    }

    public String getRandomkey() {
        return randomkey;
    }

    public void setRandomkey(String randomkey) {
        this.randomkey = randomkey;
    }

    public String getBillKey() {
        return billKey;
    }

    public void setBillKey(String billKey) {
        this.billKey = billKey;
    }

    public String getManualBillNo() {
        return manualBillNo;
    }

    public void setManualBillNo(String manualBillNo) {
        this.manualBillNo = manualBillNo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

}