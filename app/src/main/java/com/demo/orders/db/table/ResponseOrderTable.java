package com.demo.orders.db.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseOrderTable {

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
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("comments")
    @Expose
    private String comments;
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
    @SerializedName("total_products")
    @Expose
    private String totalProducts;

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

    public String getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(String totalProducts) {
        this.totalProducts = totalProducts;
    }

}
