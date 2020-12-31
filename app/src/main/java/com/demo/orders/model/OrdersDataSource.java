package com.demo.orders.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by admin on 26-Mar-18.
 */

public class OrdersDataSource implements Serializable {

    private String ordersID, date, customerID, customerName, customerEmail, customerGSTIN,
            customerAddress, ordersManID, ordersManName, ordersBillID, ordersTotalItems, ordersSubTotal,
            ordersTaxTotal, ordersNetAmount, taxSGST, taxCGST, taxIGST, paidAmount, paidMode,
            customerStateCode, ordersManStateCode, taxAmount, productCode, productName, productRate,
            productQTY, productUOM, taxPercentage, taxSGSTAmount, taxCGSTAmount, taxIGSTAmount, productTaxPrice, randomKey;
    private transient Bitmap customerImage;
    private byte[] customerBtyeImage;

    // Orders Items
    public OrdersDataSource(String ordersID, String date, String ordersBillID, String ordersSubTotal, String taxSGST, String taxCGST,
                            String taxIGST, String productCode, String productName, String productRate,
                            String productQTY, String productUOM, String productTaxPrice, String taxPercentage, String taxSGSTAmount,
                            String taxCGSTAmount, String taxIGSTAmount, String taxAmount, String netAmount) {
        this.ordersID = ordersID;
        this.date = date;
        this.ordersBillID = ordersBillID;
        this.ordersSubTotal = ordersSubTotal;
        this.taxSGST = taxSGST;
        this.taxCGST = taxCGST;
        this.taxIGST = taxIGST;
        this.productCode = productCode;
        this.productName = productName;
        this.productRate = productRate;
        this.productQTY = productQTY;
        this.productUOM = productUOM;
        this.productTaxPrice = productTaxPrice;
        this.taxPercentage = taxPercentage;
        this.taxSGSTAmount = taxSGSTAmount;
        this.taxCGSTAmount = taxCGSTAmount;
        this.taxIGSTAmount = taxIGSTAmount;
        this.taxAmount = taxAmount;
        this.ordersNetAmount = netAmount;
    }


    //Orders List
    public OrdersDataSource(String ordersID, String date, String customerID, String customerName, String customerEmail, String customerGSTIN,
                            String customerAddress, String ordersManID, String ordersManName, String ordersBillID, String ordersTotalItems,
                            String ordersSubTotal, String ordersTaxTotal, String ordersNetAmount, String taxSGST, String taxCGST, String taxIGST,
                            String paidAmount, String paidMode, String customerStateCode, String ordersManStateCode, String taxAmount,
                            Bitmap customerImage) {
        this.ordersID = ordersID;
        this.date = date;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerGSTIN = customerGSTIN;
        this.customerAddress = customerAddress;
        this.ordersManID = ordersManID;
        this.ordersManName = ordersManName;
        this.ordersBillID = ordersBillID;
        this.ordersTotalItems = ordersTotalItems;
        this.ordersSubTotal = ordersSubTotal;
        this.ordersTaxTotal = ordersTaxTotal;
        this.ordersNetAmount = ordersNetAmount;
        this.taxSGST = taxSGST;
        this.taxCGST = taxCGST;
        this.taxIGST = taxIGST;
        this.paidAmount = paidAmount;
        this.paidMode = paidMode;
        this.customerStateCode = customerStateCode;
        this.ordersManStateCode = ordersManStateCode;
        this.taxAmount = taxAmount;
        this.customerImage = customerImage;
    }

    //Store the  Orders Bitmap Image
    public OrdersDataSource(String ordersID, String date, String customerID, String customerName, String customerEmail,
                            String customerGSTIN, String customerAddress, String ordersManID, String ordersManName,
                            String ordersBillID, String ordersTotalItems, String customerStateCode,
                            String ordersManStateCode, Bitmap bitmapCustomerImage) {
        this.ordersID = ordersID;
        this.date = date;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerGSTIN = customerGSTIN;
        this.customerAddress = customerAddress;
        this.ordersManID = ordersManID;
        this.ordersManName = ordersManName;
        this.ordersBillID = ordersBillID;
        this.ordersTotalItems = ordersTotalItems;
        this.customerStateCode = customerStateCode;
        this.ordersManStateCode = ordersManStateCode;
        this.customerImage = bitmapCustomerImage;
    }

    //Store the  Orders Byte[] Image
    public OrdersDataSource(String ordersID, String date, String customerID, String customerName, String customerEmail,
                            String customerGSTIN, String customerAddress, String ordersManID, String ordersManName,
                            String ordersBillID, String ordersTotalItems, String customerStateCode,
                            String ordersManStateCode, byte[] bitmapCustomerImage, String randomKey) {
        this.ordersID = ordersID;
        this.date = date;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerGSTIN = customerGSTIN;
        this.customerAddress = customerAddress;
        this.ordersManID = ordersManID;
        this.ordersManName = ordersManName;
        this.ordersBillID = ordersBillID;
        this.ordersTotalItems = ordersTotalItems;
        this.customerStateCode = customerStateCode;
        this.ordersManStateCode = ordersManStateCode;
        this.customerBtyeImage = bitmapCustomerImage;
        this.randomKey = randomKey;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public byte[] getCustomerBtyeImage() {
        return customerBtyeImage;
    }

    public void setCustomerBtyeImage(byte[] customerBtyeImage) {
        this.customerBtyeImage = customerBtyeImage;
    }

    public Bitmap getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(Bitmap customerImage) {
        this.customerImage = customerImage;
    }

    public String getTaxCGSTAmount() {
        return taxCGSTAmount;
    }

    public void setTaxCGSTAmount(String taxCGSTAmount) {
        this.taxCGSTAmount = taxCGSTAmount;
    }

    public String getProductTaxPrice() {
        return productTaxPrice;
    }

    public void setProductTaxPrice(String productTaxPrice) {
        this.productTaxPrice = productTaxPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRate() {
        return productRate;
    }

    public void setProductRate(String productRate) {
        this.productRate = productRate;
    }

    public String getProductQTY() {
        return productQTY;
    }

    public void setProductQTY(String productQTY) {
        this.productQTY = productQTY;
    }

    public String getProductUOM() {
        return productUOM;
    }

    public void setProductUOM(String productUOM) {
        this.productUOM = productUOM;
    }

    public String getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        taxPercentage = taxPercentage;
    }

    public String getTaxSGSTAmount() {
        return taxSGSTAmount;
    }

    public void setTaxSGSTAmount(String taxSGSTAmount) {
        taxSGSTAmount = taxSGSTAmount;
    }


    public String getTaxIGSTAmount() {
        return taxIGSTAmount;
    }

    public void setTaxIGSTAmount(String taxIGSTAmount) {
        this.taxIGSTAmount = taxIGSTAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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

    public String getOrdersID() {
        return ordersID;
    }

    public void setOrdersID(String ordersID) {
        this.ordersID = ordersID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getOrdersManID() {
        return ordersManID;
    }

    public void setOrdersManID(String ordersManID) {
        this.ordersManID = ordersManID;
    }

    public String getOrdersManName() {
        return ordersManName;
    }

    public void setOrdersManName(String ordersManName) {
        this.ordersManName = ordersManName;
    }

    public String getOrdersBillID() {
        return ordersBillID;
    }

    public void setOrdersBillID(String ordersBillID) {
        this.ordersBillID = ordersBillID;
    }

    public String getOrdersTotalItems() {
        return ordersTotalItems;
    }

    public void setOrdersTotalItems(String ordersTotalItems) {
        this.ordersTotalItems = ordersTotalItems;
    }

    public String getOrdersSubTotal() {
        return ordersSubTotal;
    }

    public void setOrdersSubTotal(String ordersSubTotal) {
        this.ordersSubTotal = ordersSubTotal;
    }

    public String getOrdersTaxTotal() {
        return ordersTaxTotal;
    }

    public void setOrdersTaxTotal(String ordersTaxTotal) {
        this.ordersTaxTotal = ordersTaxTotal;
    }

    public String getOrdersNetAmount() {
        return ordersNetAmount;
    }

    public void setOrdersNetAmount(String ordersNetAmount) {
        this.ordersNetAmount = ordersNetAmount;
    }

    public String getTaxSGST() {
        return taxSGST;
    }

    public void setTaxSGST(String taxSGST) {
        this.taxSGST = taxSGST;
    }

    public String getTaxCGST() {
        return taxCGST;
    }

    public void setTaxCGST(String taxCGST) {
        this.taxCGST = taxCGST;
    }

    public String getTaxIGST() {
        return taxIGST;
    }

    public void setTaxIGST(String taxIGST) {
        this.taxIGST = taxIGST;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidMode() {
        return paidMode;
    }

    public void setPaidMode(String paidMode) {
        this.paidMode = paidMode;
    }

    public String getCustomerStateCode() {
        return customerStateCode;
    }

    public void setCustomerStateCode(String customerStateCode) {
        this.customerStateCode = customerStateCode;
    }

    public String getOrdersManStateCode() {
        return ordersManStateCode;
    }

    public void setOrdersManStateCode(String ordersManStateCode) {
        this.ordersManStateCode = ordersManStateCode;
    }
}
