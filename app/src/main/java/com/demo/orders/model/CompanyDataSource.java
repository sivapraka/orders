package com.demo.orders.model;


import android.graphics.Bitmap;

import java.io.Serializable;

public class CompanyDataSource implements Serializable {


    private String currentDate;
    private String companyID;
    private String companyName;
    private String companyEmail;
    private String companyGSTIN;
    private String companyImage;
    private byte[] companyImageByte;
    private String companyContactNo;
    private String companyBalance;
    private String companyAddress;
    private String companyStateCode;
    private String companyOrderBillNo;
    private String companySalesBillNo;
    private String companyStreet;
    private String companyCity;
    private String companyRegion;
    private String companyPostalCode;
    private transient Bitmap companyBitmapImage;


    public CompanyDataSource(String companyID, String companyName, String companyContactNo,
                             String currentDate, String companyEmail, String companyGSTIN,
                             String companyStreet, String companyCity, String companyRegion,
                             String companyPostalCode, String companyAddress, String companyStateCode,
                             String companyOrderBillNo, String companySalesBillNo) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyContactNo = companyContactNo;
        this.currentDate = currentDate;
        this.companyEmail = companyEmail;
        this.companyGSTIN = companyGSTIN;
        this.companyStreet = companyStreet;
        this.companyCity = companyCity;
        this.companyRegion = companyRegion;
        this.companyPostalCode = companyPostalCode;
        this.companyAddress = companyAddress;
        this.companyStateCode = companyStateCode;
        this.companyOrderBillNo = companyOrderBillNo;
        this.companySalesBillNo = companySalesBillNo;
    }

    //Company List Company Image (String)
    public CompanyDataSource(String companyID, String companyName, String companyContactNo,
                             String currentDate, String companyEmail, String companyGSTIN,
                             String companyStreet, String companyCity, String companyRegion,
                             String companyPostalCode, String companyAddress, String companyStateCode,
                             String companyOrderBillNo, String companySalesBillNo, String companyImage) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyContactNo = companyContactNo;
        this.currentDate = currentDate;
        this.companyEmail = companyEmail;
        this.companyGSTIN = companyGSTIN;
        this.companyStreet = companyStreet;
        this.companyCity = companyCity;
        this.companyRegion = companyRegion;
        this.companyPostalCode = companyPostalCode;
        this.companyAddress = companyAddress;
        this.companyStateCode = companyStateCode;
        this.companyOrderBillNo = companyOrderBillNo;
        this.companySalesBillNo = companySalesBillNo;
        this.companyImage = companyImage;
    }

    //Company List Company Image (Bitmap)
    public CompanyDataSource(String companyID, String companyName, String companyContactNo,
                             String currentDate, String companyEmail, String companyGSTIN,
                             String companyStreet, String companyCity, String companyRegion,
                             String companyPostalCode, String companyAddress, String companyStateCode,
                             String companyOrderBillNo, String companySalesBillNo, Bitmap companyImage) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyContactNo = companyContactNo;
        this.currentDate = currentDate;
        this.companyEmail = companyEmail;
        this.companyGSTIN = companyGSTIN;
        this.companyStreet = companyStreet;
        this.companyCity = companyCity;
        this.companyRegion = companyRegion;
        this.companyPostalCode = companyPostalCode;
        this.companyAddress = companyAddress;
        this.companyStateCode = companyStateCode;
        this.companyOrderBillNo = companyOrderBillNo;
        this.companySalesBillNo = companySalesBillNo;
        this.companyBitmapImage = companyImage;
    }


    public Bitmap getCompanyBitmapImage() {
        return companyBitmapImage;
    }

    public void setCompanyBitmapImage(Bitmap companyBitmapImage) {
        this.companyBitmapImage = companyBitmapImage;
    }

    public String getCompanyOrderBillNo() {
        return companyOrderBillNo;
    }

    public void setCompanyOrderBillNo(String companyOrderBillNo) {
        this.companyOrderBillNo = companyOrderBillNo;
    }

    public String getCompanySalesBillNo() {
        return companySalesBillNo;
    }

    public void setCompanySalesBillNo(String companySalesBillNo) {
        this.companySalesBillNo = companySalesBillNo;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyRegion() {
        return companyRegion;
    }

    public void setCompanyRegion(String companyRegion) {
        this.companyRegion = companyRegion;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyStateCode() {
        return companyStateCode;
    }

    public void setCompanyStateCode(String companyStateCode) {
        this.companyStateCode = companyStateCode;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyGSTIN() {
        return companyGSTIN;
    }

    public void setCompanyGSTIN(String companyGSTIN) {
        this.companyGSTIN = companyGSTIN;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public byte[] getCompanyImageByte() {
        return companyImageByte;
    }

    public void setCompanyImageByte(byte[] companyImageByte) {
        this.companyImageByte = companyImageByte;
    }

    public String getCompanyContactNo() {
        return companyContactNo;
    }

    public void setCompanyContactNo(String companyContactNo) {
        this.companyContactNo = companyContactNo;
    }

    public String getCompanyBalance() {
        return companyBalance;
    }

    public void setCompanyBalance(String companyBalance) {
        this.companyBalance = companyBalance;
    }


}
