package com.demo.orders.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class CustomersDataSource implements Serializable {

    // Customer accounts
    String id, customer_code, ledger_balance, loyalty_points, lastpay_date,
            lastpay_amount, lastpay_mode, lastchq_num, lastchq_date, date, isOnline;
    // Customer
    String companyCode, plant_code, salesorg_code, salesarea_code, scheme_discount, price_code,
            material_code, loyalty, last_modified;
    //Customer report
    String billing;
    String payment;
    String order;
    String orderstatus;
    String paymentstatus;
    String billstatus;
    String invoicestatus;
    private String currentDate;
    private String customerID;
    private String customerName;
    private String customerImage;
    private byte[] customerImageByte;
    private String customerContactNo;
    private String customerBalance;
    private String customerAddress;
    private String customerEmail;
    private String customerGSTIN;
    private String customerStateCode;
    private String customerStreet;
    private String customerCity;
    private String customerRegion;
    private String customerPostalCode;
    private String leadStatus;
    private String latitude;
    private String longitude;
    private transient Bitmap customerBitmapImage;

    //Customer List Customer Image (String)
    public CustomersDataSource(String customerID, String customerName, String customerContactNo,
                               String currentDate, String customerEmail, String customerGSTIN,
                               String customerAddress, String customerStreet, String customerCity,
                               String customerRegion, String customerPostalCode, String customerStateCode,
                               String customerImage) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerContactNo = customerContactNo;
        this.currentDate = currentDate;
        this.customerEmail = customerEmail;
        this.customerGSTIN = customerGSTIN;
        this.customerAddress = customerAddress;
        this.customerStreet = customerStreet;
        this.customerCity = customerCity;
        this.customerRegion = customerRegion;
        this.customerPostalCode = customerPostalCode;
        this.customerStateCode = customerStateCode;
        this.customerImage = customerImage;
    }

    //Customer List Customer Image (Bitmap)
    public CustomersDataSource(String customerID, String customerName, String customerContactNo,
                               String currentDate, String customerEmail, String customerGSTIN,
                               String customerAddress, String customerStreet, String customerCity,
                               String customerRegion, String customerPostalCode, String customerStateCode,
                               Bitmap customerImage) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerContactNo = customerContactNo;
        this.currentDate = currentDate;
        this.customerEmail = customerEmail;
        this.customerGSTIN = customerGSTIN;
        this.customerAddress = customerAddress;
        this.customerStreet = customerStreet;
        this.customerCity = customerCity;
        this.customerRegion = customerRegion;
        this.customerPostalCode = customerPostalCode;
        this.customerStateCode = customerStateCode;
        this.customerBitmapImage = customerImage;
    }


    //Customer List Customer Image (Byte)
    public CustomersDataSource(String customerID, String customerName, String customerContactNo,
                               String currentDate, String customerEmail, String customerGSTIN,
                               String customerAddress, String customerStreet, String customerCity,
                               String customerRegion, String customerPostalCode, String customerStateCode,
                               byte[] customerImage, String leadStatus, String companyCode, String plantCode,
                               String salesorgCode, String salesareaCode, String schemeDiscount, String priceCode,
                               String materialCode, String loyalty, String lastModified) {
        this.customerID = customerID;
        this.customer_code = customerID;
        this.customerName = customerName;
        this.customerContactNo = customerContactNo;
        this.currentDate = currentDate;
        this.customerEmail = customerEmail;
        this.customerGSTIN = customerGSTIN;
        this.customerAddress = customerAddress;
        this.customerStreet = customerStreet;
        this.customerCity = customerCity;
        this.customerRegion = customerRegion;
        this.customerPostalCode = customerPostalCode;
        this.customerStateCode = customerStateCode;
        this.customerImageByte = customerImage;
        this.leadStatus = leadStatus;
        this.companyCode = companyCode;
        this.plant_code = plantCode;
        this.salesorg_code = salesorgCode;
        this.salesarea_code = salesareaCode;
        this.scheme_discount = schemeDiscount;
        this.price_code = priceCode;
        this.material_code = materialCode;
        this.loyalty = loyalty;
        this.last_modified = lastModified;
    }

    // Customer accounts
    public CustomersDataSource(String id, String customer_code, String ledger_balance, String loyalty_points,
                               String lastpay_date, String lastpay_amount, String lastpay_mode,
                               String lastchq_num, String lastchq_date, String date) {
        this.id = id;
        this.customer_code = customer_code;
        this.ledger_balance = ledger_balance;
        this.loyalty_points = loyalty_points;
        this.lastpay_date = lastpay_date;
        this.lastpay_amount = lastpay_amount;
        this.lastpay_mode = lastpay_mode;
        this.lastchq_num = lastchq_num;
        this.lastchq_date = lastchq_date;
        this.date = date;
    }

    // customer report
    public CustomersDataSource(String customerName, String customer_code,
                               String bill, String pay,
                               String order, String date, String orderstatus, String billstatus,
                               String paymentstatus) {

        this.customer_code = customer_code;
        this.customerName = customerName;
        this.billing = bill;
        this.payment = pay;
        this.order = order;
        this.date = date;
        this.billstatus = billstatus;
        this.paymentstatus = paymentstatus;
        this.orderstatus = orderstatus;
    }

    //Customer latlng
    public CustomersDataSource(String customerName, String customer_code,
                               String bill, String pay,
                               String order, String date, String orderstatus, String billstatus,
                               String paymentstatus, String latitude, String longitude) {

        this.customer_code = customer_code;
        this.customerName = customerName;
        this.billing = bill;
        this.payment = pay;
        this.order = order;
        this.date = date;
        this.billstatus = billstatus;
        this.paymentstatus = paymentstatus;
        this.orderstatus = orderstatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.invoicestatus = invoicestatus;
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


    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public String getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(String billstatus) {
        this.billstatus = billstatus;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPlant_code() {
        return plant_code;
    }

    public void setPlant_code(String plant_code) {
        this.plant_code = plant_code;
    }

    public String getSalesorg_code() {
        return salesorg_code;
    }

    public void setSalesorg_code(String salesorg_code) {
        this.salesorg_code = salesorg_code;
    }

    public String getSalesarea_code() {
        return salesarea_code;
    }

    public void setSalesarea_code(String salesarea_code) {
        this.salesarea_code = salesarea_code;
    }

    public String getScheme_discount() {
        return scheme_discount;
    }

    public void setScheme_discount(String scheme_discount) {
        this.scheme_discount = scheme_discount;
    }

    public String getPrice_code() {
        return price_code;
    }

    public void setPrice_code(String price_code) {
        this.price_code = price_code;
    }

    public String getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getLedger_balance() {
        return ledger_balance;
    }

    public void setLedger_balance(String ledger_balance) {
        this.ledger_balance = ledger_balance;
    }

    public String getLoyalty_points() {
        return loyalty_points;
    }

    public void setLoyalty_points(String loyalty_points) {
        this.loyalty_points = loyalty_points;
    }

    public String getLastpay_date() {
        return lastpay_date;
    }

    public void setLastpay_date(String lastpay_date) {
        this.lastpay_date = lastpay_date;
    }

    public String getLastpay_amount() {
        return lastpay_amount;
    }

    public void setLastpay_amount(String lastpay_amount) {
        this.lastpay_amount = lastpay_amount;
    }

    public String getLastpay_mode() {
        return lastpay_mode;
    }

    public void setLastpay_mode(String lastpay_mode) {
        this.lastpay_mode = lastpay_mode;
    }

    public String getLastchq_num() {
        return lastchq_num;
    }

    public void setLastchq_num(String lastchq_num) {
        this.lastchq_num = lastchq_num;
    }

    public String getLastchq_date() {
        return lastchq_date;
    }

    public void setLastchq_date(String lastchq_date) {
        this.lastchq_date = lastchq_date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Bitmap getCustomerBitmapImage() {
        return customerBitmapImage;
    }

    public void setCustomerBitmapImage(Bitmap customerBitmapImage) {
        this.customerBitmapImage = customerBitmapImage;
    }

    public String getCustomerStreet() {
        return customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerRegion() {
        return customerRegion;
    }

    public void setCustomerRegion(String customerRegion) {
        this.customerRegion = customerRegion;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerStateCode() {
        return customerStateCode;
    }

    public void setCustomerStateCode(String customerStateCode) {
        this.customerStateCode = customerStateCode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerGSTIN() {
        return customerGSTIN;
    }

    public void setCustomerGSTIN(String customerGSTIN) {
        this.customerGSTIN = customerGSTIN;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public byte[] getCustomerImageByte() {
        return customerImageByte;
    }

    public void setCustomerImageByte(byte[] customerImageByte) {
        this.customerImageByte = customerImageByte;
    }

    public String getCustomerContactNo() {
        return customerContactNo;
    }

    public void setCustomerContactNo(String customerContactNo) {
        this.customerContactNo = customerContactNo;
    }

    public String getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(String customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }
}