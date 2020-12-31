package com.demo.orders.model;

import java.io.Serializable;

public class PreviewDataSource implements Serializable {

    private String currentDate;
    /* Add to  Cart List*/
    private String productName;
    private String productPrice;
    private String productQty;
    private String ProductID;
    private String productTotal;
    private String productTax;
    private String productTaxValue;
    private String productUOM;
    private String GSTIN;


    public PreviewDataSource(String ProductID, String productName, String productPrice, String productQty, String productTotal,
                             String productTax, String productTaxValue, String productUOM, String currentDate, String GSTIN) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productTotal = productTotal;
        this.ProductID = ProductID;
        this.productTax = productTax;
        this.productTaxValue = productTaxValue;
        this.productUOM = productUOM;
        this.currentDate = currentDate;
        this.GSTIN = GSTIN;

    }


    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }


    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }


    public String getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(String productTotal) {
        this.productTotal = productTotal;
    }

    public String getProductTax() {
        return productTax;
    }

    public void setProductTax(String productTax) {
        this.productTax = productTax;
    }

    public String getProductTaxValue() {
        return productTaxValue;
    }

    public void setProductTaxValue(String productTaxValue) {
        this.productTaxValue = productTaxValue;
    }

    public String getproductUOM() {
        return productUOM;
    }

    public void setproductUOM(String productUOM) {
        productUOM = productUOM;
    }

}
