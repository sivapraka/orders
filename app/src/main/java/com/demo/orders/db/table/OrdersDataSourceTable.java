package com.demo.orders.db.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrdersDataSourceTable implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tripID")
    @Expose
    private String tripID;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("order_no")
    @Expose
    private String orderNo;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
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
    @SerializedName("material_code")
    @Expose
    private String materialCode;
    @SerializedName("material_name")
    @Expose
    private String materialName;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("sub_total")
    @Expose
    private String subTotal;
    @SerializedName("tax_percent")
    @Expose
    private String taxPercent;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("sgst")
    @Expose
    private String sgst;
    @SerializedName("cgst")
    @Expose
    private String cgst;
    @SerializedName("igst")
    @Expose
    private String igst;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("status")
    @Expose
    private String status;
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
    @SerializedName("order_datetime")
    @Expose
    private String orderDatetime;
    @SerializedName("order_cancel_datetime")
    @Expose
    private String orderCancelDatetime;
    @SerializedName("order_confirm_datetime")
    @Expose
    private String orderConfirmDatetime;
    @SerializedName("cancel_usertype")
    @Expose
    private String cancelUsertype;
    @SerializedName("loyalty_type")
    @Expose
    private String loyaltyType;
    @SerializedName("scheme_discount")
    @Expose
    private String schemeDiscount;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("billItemTotal")
    @Expose
    private String billItemTotal;
    @SerializedName("customer_image")
    @Expose
    private String customerImage;
    @SerializedName("salesManName")
    @Expose
    private String salesManName;

    @SerializedName("unit_price")
    @Expose
    private String unitPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(String taxPercent) {
        this.taxPercent = taxPercent;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getIgst() {
        return igst;
    }

    public void setIgst(String igst) {
        this.igst = igst;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(String orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public String getOrderCancelDatetime() {
        return orderCancelDatetime;
    }

    public void setOrderCancelDatetime(String orderCancelDatetime) {
        this.orderCancelDatetime = orderCancelDatetime;
    }

    public String getOrderConfirmDatetime() {
        return orderConfirmDatetime;
    }

    public void setOrderConfirmDatetime(String orderConfirmDatetime) {
        this.orderConfirmDatetime = orderConfirmDatetime;
    }

    public String getCancelUsertype() {
        return cancelUsertype;
    }

    public void setCancelUsertype(String cancelUsertype) {
        this.cancelUsertype = cancelUsertype;
    }

    public String getLoyaltyType() {
        return loyaltyType;
    }

    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    public String getSchemeDiscount() {
        return schemeDiscount;
    }

    public void setSchemeDiscount(String schemeDiscount) {
        this.schemeDiscount = schemeDiscount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBillItemTotal() {
        return billItemTotal;
    }

    public void setBillItemTotal(String billItemTotal) {
        this.billItemTotal = billItemTotal;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public String getSalesManName() {
        return salesManName;
    }

    public void setSalesManName(String salesManName) {
        this.salesManName = salesManName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
