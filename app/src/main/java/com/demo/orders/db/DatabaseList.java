package com.demo.orders.db;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;

import com.demo.orders.helper.CommonClass;
import com.demo.orders.model.CompanyDataSource;
import com.demo.orders.model.CustomersDataSource;
import com.demo.orders.model.InvoiceDataSource;
import com.demo.orders.model.MaterialMasterDataSource;
import com.demo.orders.model.MenuDataSource;
import com.demo.orders.model.OrdersDataSource;
import com.demo.orders.model.SelectionDataSource;
import com.demo.orders.model.UserDataSource;
import com.demo.orders.util.BitmapUtility;
import com.demo.orders.util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 15-Mar-18.
 */

public class DatabaseList {

    DBHelper dbHelper;
    BitmapUtility bitmapUtility;
    CommonClass commonClass;
    private Context context;
    private String TAG = "DataBase List";


    public DatabaseList(Context ctxt) {
        this.context = ctxt;
        dbHelper = new DBHelper(ctxt);
        bitmapUtility = new BitmapUtility(ctxt);
        //  commonClass = new CommonClass(ctxt);
    }

    /* Customer List DropDown*/
    public List<SelectionDataSource> getCustomerList() {

        List<SelectionDataSource> allItems = new ArrayList<SelectionDataSource>();
        Cursor customer = dbHelper.getTable(DBHelper.TABLE_CUSTOMER);
        if (customer.getCount() > 0) {
            customer.moveToFirst();
            do {
                int id = customer.getInt(customer.getColumnIndex(DBHelper.COLUMN_ID));
                String userID = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_ID));
                String userName = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME));
                allItems.add(new SelectionDataSource(id, userID, userName));
            }
            while (customer.moveToNext());
        }
        customer.close();

        return allItems;
    }

    //Get Products List
    public List<ProductsDataSource> getProductDB() {
        List<ProductsDataSource> allItems = new ArrayList<ProductsDataSource>();
        // Get the products order by Product Name
        Cursor products = dbHelper.getTable(DBHelper.TABLE_PRODUCT, DBHelper.COLUMN_NAME);
        if (products.getCount() > 0) {
            products.moveToFirst();
            do {
                String productsID = products.getString(products.getColumnIndex(DBHelper.COLUMN_PROD_ID));
                String productsName = products.getString(products.getColumnIndex(DBHelper.COLUMN_NAME));
                String productsPrice = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_PRICE));
                String productsTaxPrice = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_TAX_PRICE));
                String productsTaxValue = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_TAX_VALUE));
                String productsUOM = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_UOM));
                String date = products.getString(products.getColumnIndex(DBHelper.COLUMN_DATE));
                String prodQty = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_QTY));
                String productsTaxPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_TAX_PERCENTAGE));
                String productSelectedMode = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_SELECTED_MODE));
                String productGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_SELECTED_GST));
                String productImage = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_IMAGE));
                //Product Price and Product Total is Same
                allItems.add(new ProductsDataSource(productsID, productsName, productsPrice, prodQty, productsPrice,
                        productsTaxPrice, productsTaxValue, productsUOM, date, productsTaxPercent, productSelectedMode,
                        productGST, productImage));
            }
            while (products.moveToNext());
        }
        products.close();
        return allItems;
    }

    //Get Products List
    public List<ProductsDataSource> getProductDetails(String values) {
        List<ProductsDataSource> allItems = new ArrayList<ProductsDataSource>();
        // Get the products order by Product Name
        Cursor products = dbHelper.getTableList(DBHelper.TABLE_PRODUCT_DETAILS, DBHelper.COLUMN_BILL_NO, values);
        if (products.getCount() > 0) {
            products.moveToFirst();
            do {
                String billNo = products.getString(products.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                String date = products.getString(products.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                String productsID = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                String productsName = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                String productsType = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_TYPE));
                String prodQty = products.getString(products.getColumnIndex(DBHelper.COLUMN_QUANTITY));
                String productsPrice = products.getString(products.getColumnIndex(DBHelper.COLUMN_UNIT_PRICE));
                String value = products.getString(products.getColumnIndex(DBHelper.COLUMN_VALUE));
                String discount_amount = products.getString(products.getColumnIndex(DBHelper.COLUMN_DISCOUNT_AMOUNT));
                String totalDiscount = products.getString(products.getColumnIndex(DBHelper.COLUMN_TOTAL_DISCOUNT));
                String subTotal = products.getString(products.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                String tax_percent = products.getString(products.getColumnIndex(DBHelper.COLUMN_TAX_PERCENT));
                String totalTax = products.getString(products.getColumnIndex(DBHelper.COLUMN_TOTAL_TAX));
                String totalPrice = products.getString(products.getColumnIndex(DBHelper.COLUMN_TATAL_PRICE));
                String mainDiscountAmount = products.getString(products.getColumnIndex(DBHelper.COLUMN_MAIN_DISCOUNT_AMOUNT));
                String main_total_discount = products.getString(products.getColumnIndex(DBHelper.COLUMN_MAIN_TOTAL_DISCOUNT));
                String main_sub_total = products.getString(products.getColumnIndex("main_sub_total"));
                String main_total_tax = products.getString(products.getColumnIndex("main_total_tax"));
                String main_total_price = products.getString(products.getColumnIndex("main_total_price"));
                String enteredBy = products.getString(products.getColumnIndex(DBHelper.COLUMN_ENTERED_BY));
                String createdOn = products.getString(products.getColumnIndex(DBHelper.COLUMN_CREATED_ON));
                String productCGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_CGST));
                String productSGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_SGST));
                String productIGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_IGST));
                String productCGSTPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_CGST_PERCENT));
                String productSGSTPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_SGST_PERCENT));
                String productIGSTPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_IGST_PERCENT));
                String productType = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_TYPE));

                String roundOffTotalTax = String.valueOf(Math.round(Float.parseFloat(totalTax)));
                String netTotal;
                String taxQuantity = totalTax;
                Log.e(TAG, "getProductDetails: " + taxQuantity);
                if (!totalTax.equals("0")) {
                    netTotal = String.valueOf(Double.parseDouble(value) + Double.parseDouble(totalTax));
                    netTotal = String.valueOf(Math.round(Float.parseFloat(netTotal)));
                    taxQuantity = String.valueOf(Double.parseDouble(totalTax) / Double.parseDouble(prodQty));
                } else {
                    netTotal = value;
                }
                String amoutTotal = value;
                //Product Price and Product Total is Same
                allItems.add(new ProductsDataSource(productsID, productsName, productsPrice, prodQty, totalPrice, tax_percent, taxQuantity,
                        "", date, tax_percent,
                        productSGSTPercent, productSGST, productCGSTPercent, productCGST, productIGSTPercent, productIGST,
                        "", "", "", netTotal, amoutTotal, taxQuantity, value,
                        totalPrice, "",
                        "", totalDiscount, "", "", "", "", "",
                        "", "", "", "", "", "", "",
                        "", "", "", "", "", "", productType));
            }
            while (products.moveToNext());
        }
        products.close();
        return allItems;
    }

    //Get Products List
    public List<ProductsDataSource> getProductGroupDetails(String values) {
        List<ProductsDataSource> allItems = new ArrayList<ProductsDataSource>();
        // Get the products order by Product Name
        Cursor products = dbHelper.getTableListGroup(DBHelper.TABLE_PRODUCT_DETAILS, DBHelper.COLUMN_BILL_NO, values);
        if (products.getCount() > 0) {
            products.moveToFirst();
            do {
                String billNo = products.getString(products.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                String date = products.getString(products.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                String productsID = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                String productsName = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                String productsType = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_TYPE));
                String prodQty = products.getString(products.getColumnIndex(DBHelper.COLUMN_QUANTITY));
                String productsPrice = products.getString(products.getColumnIndex(DBHelper.COLUMN_UNIT_PRICE));
                String value = products.getString(products.getColumnIndex(DBHelper.COLUMN_VALUE));
                String discount_amount = products.getString(products.getColumnIndex(DBHelper.COLUMN_DISCOUNT_AMOUNT));
                String totalDiscount = products.getString(products.getColumnIndex(DBHelper.COLUMN_TOTAL_DISCOUNT));
                String subTotal = products.getString(products.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                String tax_percent = products.getString(products.getColumnIndex(DBHelper.COLUMN_TAX_PERCENT));
                String totalTax = products.getString(products.getColumnIndex(DBHelper.COLUMN_TOTAL_TAX));
                String totalPrice = products.getString(products.getColumnIndex(DBHelper.COLUMN_TATAL_PRICE));
                String mainDiscountAmount = products.getString(products.getColumnIndex(DBHelper.COLUMN_MAIN_DISCOUNT_AMOUNT));
                String main_total_discount = products.getString(products.getColumnIndex(DBHelper.COLUMN_MAIN_TOTAL_DISCOUNT));
                String main_sub_total = products.getString(products.getColumnIndex("main_sub_total"));
                String main_total_tax = products.getString(products.getColumnIndex("main_total_tax"));
                String main_total_price = products.getString(products.getColumnIndex("main_total_price"));
                String enteredBy = products.getString(products.getColumnIndex(DBHelper.COLUMN_ENTERED_BY));
                String createdOn = products.getString(products.getColumnIndex(DBHelper.COLUMN_CREATED_ON));
                String productCGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_CGST));
                String productSGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_SGST));
                String productIGST = products.getString(products.getColumnIndex(DBHelper.COLUMN_IGST));
                String productCGSTPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_CGST_PERCENT));
                String productSGSTPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_SGST_PERCENT));
                String productIGSTPercent = products.getString(products.getColumnIndex(DBHelper.COLUMN_IGST_PERCENT));
                String productType = products.getString(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_TYPE));

                String roundOffTotalTax = String.valueOf(Math.round(Float.parseFloat(totalTax)));
                String netTotal;
                String taxQuantity = totalTax;
                Log.e(TAG, "getProductDetails: " + taxQuantity);
                if (!totalTax.equals("0")) {
                    netTotal = String.valueOf(Double.parseDouble(value) + Double.parseDouble(totalTax));
                    netTotal = String.valueOf(Math.round(Float.parseFloat(netTotal)));
                    taxQuantity = String.valueOf(Double.parseDouble(totalTax) / Double.parseDouble(prodQty));
                } else {
                    netTotal = value;
                }
                String amoutTotal = value;
                //Product Price and Product Total is Same
                allItems.add(new ProductsDataSource(productsID, productsName, productsPrice, prodQty, totalPrice, tax_percent, taxQuantity,
                        "", date, tax_percent,
                        productSGSTPercent, productSGST, productCGSTPercent, productCGST, productIGSTPercent, productIGST,
                        "", "", "", netTotal, amoutTotal, taxQuantity, value,
                        totalPrice, "",
                        "", totalDiscount, "", "", "", "", "",
                        "", "", "", "", "", "", "",
                        "", "", "", "", "", "", productType));
            }
            while (products.moveToNext());
        }
        products.close();
        return allItems;
    }

    //GET Sale List From Database
    public List<SalesDataSource> getSalesDB() {
        List<SalesDataSource> allItems = new ArrayList<SalesDataSource>();
        Cursor sales = dbHelper.getTableList(DBHelper.TABLE_SALES);
        if (sales.getCount() > 0) {
            sales.moveToFirst();
            do {
                String salesID = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_SALES_ID));
                String date = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_DATE));
                String customerID = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_CUSTOMER_ID));
                String customerName = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME));
                String salesManID = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_USER_ID));
                String salesManName = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_USER_NAME));
                String salesBillID = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_BILL_ID));
                String salesTotalItems = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_BILL_TOTAL_ITEMS));
                String salesSubTotal = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL));
                String salesTaxTotal = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_TOTAL));
                String salesNetAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_NET_AMOUNT));
                String taxSGST = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_SGST));
                String taxCGST = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_CGST));
                String taxIGST = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_IGST));
                String paidAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_PAID_AMOUNT));
                String paidMode = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_PAID_MODE));
                String customerStateCode = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_CUSTOMER_STATE_CODE));
                String salesManStateCode = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_SALES_MAN_STATE_CODE));
                String taxAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_AMOUNT));
                Log.e(TAG, "SALES LIST: " + date);
                List<CustomersDataSource> customerDB = getCustomerListDB(DBHelper.COLUMN_SALES_AREA_CODE, Constants.salesAreaCode);
                if (customerDB.size() > 0) {
                    for (CustomersDataSource customersDataSource : customerDB) {
                        if (customerID.equals(customersDataSource.getCustomerID())) {
                            String customerEmail = customersDataSource.getCustomerEmail();
                            String customerGSTIN = customersDataSource.getCustomerGSTIN();
                            String customerAddress = customersDataSource.getCustomerAddress();
                            byte[] customerImage = null;
                            customerImage = customersDataSource.getCustomerImageByte();
                            allItems.add(new SalesDataSource(salesID, date, customerID, customerName, customerEmail, customerGSTIN,
                                    customerAddress, salesManID, salesManName, salesBillID, salesTotalItems, salesSubTotal, salesTaxTotal,
                                    salesNetAmount, taxSGST, taxCGST, taxIGST, paidAmount, paidMode, customerStateCode, salesManStateCode,
                                    taxAmount, customerImage));
                        }
                    }
                }
            }
            while (sales.moveToNext());
        }
        sales.close();
        return allItems;
    }


    //GET Sale List From Database
    public List<PrinterDataSource> getBluetoothDevices() {
        List<PrinterDataSource> allItems = new ArrayList<>();
        Cursor sales = dbHelper.getTableList(DBHelper.TABLE_APP_DETAILS, DBHelper.COLUMN_NAME, "active");
        if (sales.getCount() > 0) {
            sales.moveToFirst();
            do {
                String status = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_NAME));
                String name = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_ADDRESS));
                //   String customerID = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_CUSTOMER_ID));
                allItems.add(new PrinterDataSource(name, status));
            }
            while (sales.moveToNext());
        }
        sales.close();
        return allItems;
    }


    /* Show the Customer Details*/
    public List<ProductsDataSource> getProductsList(String condition, String value, String value1,
                                                    String value2, String discount, String customerID
    ) {
        List<ProductsDataSource> allItems = new ArrayList<>();
        //get the table Name order by customerName
        Cursor prod = dbHelper.product(condition, value, value1, value2, discount, customerID);
        try {
            if (prod != null && prod.getCount() > 0) {
                int i = 0;
                prod.moveToFirst();
                do {
                    String companyCode = String.valueOf(prod.getInt(prod.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL)));
                    String plantCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                    String salesorgCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                    String salesareaCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                    String supCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SUPERVISOR_CODE));
                    String supName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SUPERVISOR_NAME));
                    String vehicle = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VEHICLE));
                    String priceList = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRICE_LIST));
                    String materialCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                    String materialName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                    String materialCategory = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_CATEGORY));
                    String amount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AMOUNT));
                    String priceTax = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRICE_TAX));
                    String netAmount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_PRICE));
                    String productUOM = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_UOM));
                    String taxDetails = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_TAX_DETAILS));
                    String validFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_FROM));
                    String validTo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_TO));
                    String availableQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AVAILABLE_QUANTITY));
                    String Qty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_QUANTITY));
                    String discountFromQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM_QUANTITY));
                    String discountToQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TO_QUANTITY));
                    String discnt = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
                    String discountType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TYPE));
                    String discountWot = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_WOT));
                    String discountFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM));
                    String discountTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TILL));
                    String dayCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DAY_COUNT));
                    String replaceQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_REPLACE_QUANTITY));
                    String soldQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SOLD_QUANTITY));
                    String scheme = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME));
                    String schemeCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
                    String schemeName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
                    String schemeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_QUANTITY));
                    String schemeFreeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FREE_QUANTITY));
                    String schemeFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
                    String schemeTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
                    String schemeType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
                    String schemeMaterial1 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL1));
                    String schemeMaterial2 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL2));
                    String loyalty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_LOYALTY));
                    String productImage = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRODUCT_IMAGE));
                    String productTaxValue1 = String.valueOf(Double.parseDouble(amount) * Double.parseDouble(priceTax));
//                    String productTaxValue = productSubtract(netAmount, amount);
                    String productTaxValue = String.valueOf(getDivision(Double.parseDouble(productTaxValue1), 100.0f));
                    String cgstSgstAmount = String.valueOf(getDivision(Double.parseDouble(productTaxValue), 2.0d));
                    String cgstSgstTax = String.valueOf(getDivision(Double.parseDouble(priceTax), 2.0d));

                    DateFormat dateFormat = new SimpleDateFormat(Constants.inputFormat3);
                    Date date = new Date();
                    //  Log.e(TAG, "getDateTime: " + dateFormat.format(date));
                    String currentDate = dateFormat.format(date);
                    allItems.add(new ProductsDataSource(companyCode, plantCode, salesorgCode, salesareaCode,
                            supCode, supName, vehicle, priceList, materialCode, materialName, materialCategory,
                            amount, priceTax, netAmount, productUOM, taxDetails, validFrom, validTo, availableQty,
                            Qty, discountFromQty, discountToQty, discnt, discountType, discountWot, discountFrom,
                            discountTill, dayCount, replaceQty, soldQty, scheme, schemeCode, schemeName, schemeQty,
                            schemeFreeQty, schemeFrom, schemeTill, schemeType, schemeMaterial1, schemeMaterial2,
                            productImage, false, "1", netAmount, currentDate, loyalty, productTaxValue,
                            cgstSgstTax, cgstSgstAmount, cgstSgstTax, cgstSgstAmount,
                            priceTax, productTaxValue, i, "main"));
                    i++;
                }
                while (prod.moveToNext());
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        assert prod != null;
        prod.close();
        Log.e(TAG, "getProductsList: allItems : " + allItems.size());
        return allItems;
    }


    /* Show the Customer Details*/
    public List<ProductsDataSource> getProductsMaterialSchemeCodeList(String currentdate,
                                                                      String pcode, String customerID, String salesOrgCode,
                                                                      String schemeDiscount, String loyalty, String mastermaterialCode, String priceCode) {
        List<ProductsDataSource> allItems = new ArrayList<>();
        //get the table Name order by customerName
        Cursor prod = dbHelper.productmaterialSchemeCode(currentdate, pcode, customerID, salesOrgCode, schemeDiscount, mastermaterialCode, priceCode);
        try {
            if (prod != null && prod.getCount() > 0) {
                int i = 0;
                prod.moveToFirst();
                do {
                    String companyCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                    String plantCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                    String salesorgCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                    String materialCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                    String materialName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                    String materialCategory = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_CATEGORY));
                    String netWeight = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_WEIGHT));
                    String productUOM = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_UOM));
                    String taxDetails = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_TAX_DETAILS));
                    String productImage = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRODUCT_IMAGE));

                    String discountFromQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM_QUANTITY));
                    String discountToQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TO_QUANTITY));
                    String discnt = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
                    String discountType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TYPE));
                    String discountWot = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_WOT));
                    String discountFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM));
                    String discountTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TILL));

                    Cursor sche = dbHelper.scheme(schemeDiscount, materialCode, currentdate);

                    String scheme = "", schemeCode = "", schemeName = "", schemeQty = "", schemeFreeQty = "",
                            schemeFrom = "", schemeTill = "", schemeType = "", schemeMaterial1 = "", schemeMaterial2 = "";
                    if (sche.getCount() > 0) {
                        sche.moveToFirst();
                        do {
                            scheme = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME));
                            schemeCode = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
                            schemeName = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
                            schemeQty = sche.getString(sche.getColumnIndex("quantity"));
                            schemeFreeQty = sche.getString(sche.getColumnIndex("free_quantity"));
                            schemeFrom = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
                            schemeTill = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
                            schemeType = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
                            schemeMaterial1 = sche.getString(sche.getColumnIndex("material_code1"));
                            schemeMaterial2 = sche.getString(sche.getColumnIndex("material_code2"));
                        } while (sche.moveToNext());
                    } else {
                        scheme = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME));
                        schemeCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
                        schemeName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
                        schemeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_QUANTITY));
                        schemeFreeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FREE_QUANTITY));
                        schemeFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
                        schemeTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
                        schemeType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
                        schemeMaterial1 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL1));
                        schemeMaterial2 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL2));
                    }

                    String productTaxValue1 = String.valueOf(Double.parseDouble(netWeight) * Double.parseDouble(taxDetails));
                    String productTaxValue = String.valueOf(getDivision(Double.parseDouble(productTaxValue1), 100.0f));
                    String productTotal = String.valueOf(productAdd(netWeight, productTaxValue));
                    String cgstSgstAmount = String.valueOf(getDivision(Double.parseDouble(productTaxValue), 2.0d));
                    String cgstSgstTax = String.valueOf(getDivision(Double.parseDouble(taxDetails), 2.0d));

                    String amount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AMOUNT));
                    String priceTax = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRICE_TAX));
                    String netAmount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_PRICE));
                    String validFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_FROM));
                    String validTo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_TO));
                    String availableQty = "";
                    String Qty = "";
                    String salesareaCode = "";
                    String supCode = "";
                    String supName = "";
                    String vehicle = "";
                    String priceList = "";
                    String dayCount = "";
                    String replaceQty = "";
                    String soldQty = "";

                    DateFormat dateFormat = new SimpleDateFormat(Constants.inputFormat3);
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);

                    allItems.add(new ProductsDataSource(companyCode, plantCode, salesorgCode, salesareaCode,
                            supCode, supName, vehicle, priceList, materialCode, materialName, materialCategory,
                            amount, priceTax, netAmount, productUOM, taxDetails, validFrom, validTo, availableQty,
                            Qty, discountFromQty, discountToQty, discnt, discountType, discountWot, discountFrom,
                            discountTill, dayCount, replaceQty, soldQty, scheme, schemeCode, schemeName, schemeQty,
                            schemeFreeQty, schemeFrom, schemeTill, schemeType, schemeMaterial1, schemeMaterial2,
                            productImage, false, "1", netAmount, currentDate, loyalty, productTaxValue,
                            cgstSgstTax, cgstSgstAmount, cgstSgstTax, cgstSgstAmount,
                            priceTax, productTaxValue, i));
                    i++;
                }
                while (prod.moveToNext());
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        assert prod != null;
        prod.close();
        Log.e(TAG, "getProductsList: allItems : " + allItems.size());
        return allItems;
    }

    public double getDivision(double amount, double value1) {
        return amount / value1;
    }

    //Subtract the product
    public String productSubtract(String input1, String input2) {
        return String.valueOf(Double.parseDouble(input1) - Double.parseDouble(input2));
    }

    //Subtract the product
    public String productAdd(String input1, String input2) {
        return String.valueOf(Double.parseDouble(input1) + Double.parseDouble(input2));
    }

    /* Show the Product List*/
    public List<ProductsDataSource> getOrderProductsList(String currenDate, String customerID, String price_code, String salesorg_code
            , String cmScheme_discount, String loyalty) {
        List<ProductsDataSource> allItems = new ArrayList<>();
        //get the table Name order by customerName

        Cursor prod = dbHelper.product(currenDate, price_code, salesorg_code, cmScheme_discount);
        try {
            if (prod != null && prod.getCount() > 0) {
                int i = 0;
                prod.moveToFirst();
                do {
                    String companyCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                    String plantCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                    String salesorgCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                    String salesareaCode = "";
                    String supCode = "";
                    String supName = "";
                    String vehicle = "";
                    String priceList = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRICE_LIST));
                    String materialCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                    String materialName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                    String materialCategory = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_MATERIAL_CATEGORY));
                    String amount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AMOUNT));
                    String priceTax = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRICE_TAX));
                    String netAmount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_PRICE));
                    String productUOM = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_UOM));
                    String taxDetails = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_TAX_DETAILS));
                    String validFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_FROM));
                    String validTo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_TO));
                    String availableQty = "";
                    String Qty = "";
                    String discountFromQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM_QUANTITY));
                    String discountToQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TO_QUANTITY));
                    String discnt = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
                    String discountType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TYPE));
                    String discountWot = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_WOT));
                    String discountFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM));
                    String discountTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TILL));
                    String dayCount = "";
                    String replaceQty = "";
                    String soldQty = "";

                    //  Cursor sche = dbHelper.scheme(customerID, materialCode, value1);
                    Cursor sche = dbHelper.scheme(cmScheme_discount, materialCode, currenDate);
                    String scheme = "", schemeCode = "", schemeName = "", schemeQty = "", schemeFreeQty = "",
                            schemeFrom = "", schemeTill = "", schemeType = "", schemeMaterial1 = "", schemeMaterial2 = "";
                    if (sche.getCount() > 0) {
                        sche.moveToFirst();
                        do {
                            scheme = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME));
                            schemeCode = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
                            schemeName = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
                            schemeQty = sche.getString(sche.getColumnIndex("quantity"));
                            schemeFreeQty = sche.getString(sche.getColumnIndex("free_quantity"));
                            schemeFrom = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
                            schemeTill = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
                            schemeType = sche.getString(sche.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
                            schemeMaterial1 = sche.getString(sche.getColumnIndex("material_code1"));
                            schemeMaterial2 = sche.getString(sche.getColumnIndex("material_code2"));
                        } while (sche.moveToNext());
                    } else {
                        scheme = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME));
                        schemeCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
                        schemeName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
                        schemeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_QUANTITY));
                        schemeFreeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FREE_QUANTITY));
                        schemeFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
                        schemeTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
                        schemeType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
                        schemeMaterial1 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL1));
                        schemeMaterial2 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL2));
                    }
                    String productTaxValue1 = String.valueOf(Double.parseDouble(amount) * Double.parseDouble(priceTax));
                    String productTaxValue = String.valueOf(getDivision(Double.parseDouble(productTaxValue1), 100.0f));
                    String cgstSgstAmount = String.valueOf(getDivision(Double.parseDouble(productTaxValue), 2.0d));
                    String cgstSgstTax = String.valueOf(getDivision(Double.parseDouble(priceTax), 2.0d));
                    String productImage = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PRODUCT_IMAGE));
                    DateFormat dateFormat = new SimpleDateFormat(Constants.inputFormat3);
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);
                    allItems.add(new ProductsDataSource(companyCode, plantCode, salesorgCode, salesareaCode,
                            supCode, supName, vehicle, priceList, materialCode, materialName, materialCategory,
                            amount, priceTax, netAmount, productUOM, taxDetails, validFrom, validTo, availableQty,
                            Qty, discountFromQty, discountToQty, discnt, discountType, discountWot, discountFrom,
                            discountTill, dayCount, replaceQty, soldQty, scheme, schemeCode, schemeName, schemeQty,
                            schemeFreeQty, schemeFrom, schemeTill, schemeType, schemeMaterial1, schemeMaterial2,
                            productImage, false, "1", netAmount, currentDate, loyalty, productTaxValue,
                            cgstSgstTax, cgstSgstAmount, cgstSgstTax, cgstSgstAmount,
                            priceTax, productTaxValue, i));
                    i++;
                }
                while (prod.moveToNext());
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        assert prod != null;
        prod.close();
        return allItems;
    }

    /* Show the Customer Details*/
    public List<ProductsDataSource> getFutureRequire(String value) {
        List<ProductsDataSource> allItems = new ArrayList<ProductsDataSource>();
        //get the Table Name order by CustomerName
        Cursor customer = dbHelper.getTableList(DBHelper.TABLE_FUTURE_REQUIRED, DBHelper.COLUMN_RANDOM_KEY, value);

        if (customer.getCount() > 0) {
            customer.moveToFirst();
            do {

                String materialCode = String.valueOf(customer.getInt(customer.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE)));
                String materialName = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                String quantity = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_QUANTITY));

                allItems.add(new ProductsDataSource(materialCode, materialName, quantity));

            }
            while (customer.moveToNext());
        }
        customer.close();

        return allItems;
    }

    /* Show the Customer account Details*/
    public List<CustomersDataSource> getCustomerAccountDB(String value) {
        List<CustomersDataSource> allItems = new ArrayList<CustomersDataSource>();
        //get the Table Name order by CustomerName
        Cursor customer = dbHelper.getTableList(DBHelper.TABLE_CUSTOMER_ACCOUNTS, DBHelper.COLUMN_CUSTOMER_CODE_SMALL, value);

        if (customer.getCount() > 0) {
            customer.moveToFirst();
            do {

                String id = String.valueOf(customer.getInt(customer.getColumnIndex(DBHelper.COLUMN_ID)));
                String customerCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                String ledgerBalance = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LEDGER_BALANCE));
                String loyaltyPoints = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LOYALTY_POINTS));
                String lastpayDate = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTPAY_DATE));
                String lastpayAmount = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTPAY_AMOUNT));
                String lastpayMode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTPAY_MODE));
                String lastchqNum = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTCHQ_NO));
                String lastchqDate = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTCHQ_DATE));
                String date = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_DATE));

                allItems.add(new CustomersDataSource(id, customerCode, ledgerBalance, loyaltyPoints,
                        lastpayDate, lastpayAmount, lastpayMode, lastchqNum, lastchqDate, date));

            }
            while (customer.moveToNext());
        }
        customer.close();

        return allItems;
    }

    /* Show the Customer account Details*/
    public List<CustomersDataSource> getCustomerAccountDB(String value, String value1) {
        List<CustomersDataSource> allItems = new ArrayList<CustomersDataSource>();
        //get the Table Name order by CustomerName
        Cursor customer = dbHelper.getTableList(DBHelper.TABLE_CUSTOMER_ACCOUNTS, DBHelper.COLUMN_CUSTOMER_CODE_SMALL, value);

        if (customer.getCount() > 0) {
            customer.moveToFirst();
            do {

                String id = String.valueOf(customer.getInt(customer.getColumnIndex(DBHelper.COLUMN_ID)));
                String customerCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                String ledgerBalance = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LEDGER_BALANCE));
                String loyaltyPoints = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LOYALTY_POINTS));
                String lastpayDate = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTPAY_DATE));
                String lastpayAmount = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTPAY_AMOUNT));
                String lastpayMode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTPAY_MODE));
                String lastchqNum = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTCHQ_NO));
                String lastchqDate = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LASTCHQ_DATE));
                String date = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_DATE));

                allItems.add(new CustomersDataSource(id, customerCode, ledgerBalance, loyaltyPoints,
                        lastpayDate, lastpayAmount, lastpayMode, lastchqNum, lastchqDate, date));

            }
            while (customer.moveToNext());
        }
        customer.close();

        return allItems;
    }

    /* Show the Customer Details*/
    public List<CustomersDataSource> getCustomerListDB(String key, String value) {
        List<CustomersDataSource> allItems = new ArrayList<CustomersDataSource>();
        //get the Table Name order by CustomerName
        //  Cursor customer = dbHelper.getTable(DBHelper.TABLE_CUSTOMER, DBHelper.COLUMN_CUSTOMER_NAME,DBHelper.COLUMN_LEAD_STATUS,value);
        Cursor customer = null;
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            customer = dbHelper.table(DBHelper.TABLE_CUSTOMER);
        } else {
            customer = dbHelper.getTableList(DBHelper.TABLE_CUSTOMER, key, value);
        }
        if (customer.getCount() > 0) {
            customer.moveToFirst();
            do {
                String customerID = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_ID));
                String customerName = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME));
                String customerPhoneNo = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_PHONE_NO));
                String date = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_DATE));
                String customerStreet = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_STREET));
                String customerCity = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_CITY));
                String customerRegion = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_REGION));
                String customerPostalCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_PINCODE));
                String customerGSTIN = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_GSTIN));
                String customerEmail = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_EMAIL));
                String stateCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_STATE_CODE));
                String leadStatus = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LEAD_STATUS));

                String companyCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE));
                String plantCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                String salesorgCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                String salesareaCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                String schemeDiscount = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_SCHEME_DISCOUNT));
                String priceCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_PRICE_CODE));
                String materialCode = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                String loyalty = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LOYALTY));
                String lastModified = customer.getString(customer.getColumnIndex(DBHelper.COLUMN_LAST_MODIFIED));


                String customerAddress = customerStreet + "," + customerCity + "," + customerRegion;

                customerAddress = dbHelper.removeDuplicateComma(customerAddress) + " - " + customerPostalCode;

                byte[] customerImage = customer.getBlob(customer.getColumnIndex(DBHelper.COLUMN_CUSTOMER_IMAGE));
                allItems.add(new CustomersDataSource(customerID, customerName, customerPhoneNo, date,
                        customerEmail, customerGSTIN, customerAddress, customerStreet, customerCity,
                        customerRegion, customerPostalCode, stateCode, customerImage, leadStatus,
                        companyCode, plantCode, salesorgCode, salesareaCode, schemeDiscount, priceCode, materialCode,
                        loyalty, lastModified));
            }
            while (customer.moveToNext());
        }
        customer.close();

        return allItems;
    }

    /* Show the Customer Details*/
    public List<InvoiceDataSource> getCustomerInvoiceDB(String value1) {
        List<InvoiceDataSource> allItems = new ArrayList<InvoiceDataSource>();
        Cursor invoice;
        invoice = dbHelper.getInvoicebillList(DBHelper.TABLE_INVOICE, DBHelper.COLUMN_CUSTOMER_CODE_SMALL, value1);

        if (invoice.getCount() > 0) {
            invoice.moveToFirst();
            do {
                String id = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ID));
                String erpId = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ERP_ID));
                String tripID = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_TRIPID));
                String billNo = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                String billDate = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                String customerCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                String customerName = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME_SMALL));
                String companyCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                String plantCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                String salesorgCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                String salesareaCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                String supervisorCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SUPERVISOR_CODE));

                String itemCount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ITEM_COUNT));
                String value = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_VALUE));
                String subTotal = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                String discount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
                String tax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_INVOICETAX));
                String grandTotal = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_GRAND_TOTAL));
                String roundUp = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ROUND_UP));
                String netAmount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_NET_AMOUNT));
                String received = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_RECEIVED));
                String balance = invoice.getString(invoice.getColumnIndex("balance"));
                String billStatus = invoice.getString(invoice.getColumnIndex("bill_status"));
                String comments = invoice.getString(invoice.getColumnIndex("comments"));
                String manualBilling = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MANUAL_BILLING));
                String randomkey = invoice.getString(invoice.getColumnIndex("randomkey"));
                String billKey = invoice.getString(invoice.getColumnIndex("bill_key"));
                String manualBillNo = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MANUAL_BILL_NO));
                String latitude = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_LATITUDE));
                String longitude = invoice.getString(invoice.getColumnIndex("longitude"));
                String enteredBy = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ENTERED_BY));
                String createdOn = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CREATED_ON));
                String modifiedBy = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MODIFIED_BY));
                String modifiedOn = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MODIFIED_ON));
//                String billId = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILLID));
//                String sgstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SGST_TAX));
//                String cgstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CGST_TAX));
//                String igstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_IGST_TAX));
//                String totalTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_TOTAL_TAX));
                String isOnline = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_IS_ONLINE));

                allItems.add(new InvoiceDataSource(id, erpId, tripID, billNo,
                        billDate, customerCode, customerName, companyCode, plantCode, salesorgCode,
                        salesareaCode, supervisorCode, itemCount, value, subTotal, discount, tax,
                        grandTotal, roundUp, netAmount, received,
                        balance, billStatus, comments, manualBilling, randomkey, billKey, manualBillNo,
                        latitude, "", enteredBy, createdOn, modifiedBy, modifiedOn,
                        isOnline));
            }
            while (invoice.moveToNext());
        }
        invoice.close();

        return allItems;
    }

    /* Show the Customer Details*/
    public List<InvoiceDataSource> getCustomerInvoiceLastBill(String value1) {
        List<InvoiceDataSource> allItems = new ArrayList<InvoiceDataSource>();
        Cursor invoice;
        invoice = dbHelper.getInvoiceLastbillList(DBHelper.TABLE_INVOICE, DBHelper.COLUMN_CUSTOMER_CODE_SMALL, value1);

        if (invoice.getCount() > 0) {
            invoice.moveToFirst();
            do {
                String id = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ID));
                String erpId = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ERP_ID));
                String tripID = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_TRIPID));
                String billNo = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                String billDate = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                String customerCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                String customerName = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME_SMALL));
                String companyCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                String plantCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                String salesorgCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                String salesareaCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                String supervisorCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SUPERVISOR_CODE));

                String itemCount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ITEM_COUNT));
                String value = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_VALUE));
                String subTotal = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                String discount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
                String tax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_INVOICETAX));
                String grandTotal = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_GRAND_TOTAL));
                String roundUp = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ROUND_UP));
                String netAmount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_NET_AMOUNT));
                String received = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_RECEIVED));
                String balance = invoice.getString(invoice.getColumnIndex("balance"));
                String billStatus = invoice.getString(invoice.getColumnIndex("bill_status"));
                String comments = invoice.getString(invoice.getColumnIndex("comments"));
                String manualBilling = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MANUAL_BILLING));
                String randomkey = invoice.getString(invoice.getColumnIndex("randomkey"));
                String billKey = invoice.getString(invoice.getColumnIndex("bill_key"));
                String manualBillNo = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MANUAL_BILL_NO));
                String latitude = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_LATITUDE));
                String longitude = invoice.getString(invoice.getColumnIndex("longitude"));
                String enteredBy = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ENTERED_BY));
                String createdOn = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CREATED_ON));
                String modifiedBy = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MODIFIED_BY));
                String modifiedOn = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MODIFIED_ON));
//                String billId = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILLID));
//                String sgstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SGST_TAX));
//                String cgstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CGST_TAX));
//                String igstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_IGST_TAX));
//                String totalTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_TOTAL_TAX));
                String isOnline = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_IS_ONLINE));

                allItems.add(new InvoiceDataSource(id, erpId, tripID, billNo,
                        billDate, customerCode, customerName, companyCode, plantCode, salesorgCode,
                        salesareaCode, supervisorCode, itemCount, value, subTotal, discount, tax,
                        grandTotal, roundUp, netAmount, received,
                        balance, billStatus, comments, manualBilling, randomkey, billKey, manualBillNo,
                        latitude, "", enteredBy, createdOn, modifiedBy, modifiedOn,
                        isOnline));
            }
            while (invoice.moveToNext());
        }
        invoice.close();

        return allItems;
    }

    /* Show the Customer Details*/
    public List<InvoiceDataSource> getCustomerInvoice(String key, String value1) {
        List<InvoiceDataSource> allItems = new ArrayList<InvoiceDataSource>();
        Cursor invoice = dbHelper.getInvoicebilloffline(DBHelper.TABLE_INVOICE, key, value1);

        if (invoice.getCount() > 0) {
            invoice.moveToFirst();
            do {
                String id = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ID));
                String erpId = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ERP_ID));
                String tripID = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_TRIPID));
                String billNo = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                String billDate = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                String customerCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                String customerName = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME_SMALL));
                String companyCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                String plantCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                String salesorgCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                String salesareaCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                String supervisorCode = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SUPERVISOR_CODE));

                String itemCount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ITEM_COUNT));
                String value = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_VALUE));
                String subTotal = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                String discount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
                String tax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_INVOICETAX));
                String grandTotal = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_GRAND_TOTAL));
                String roundUp = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ROUND_UP));
                String netAmount = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_NET_AMOUNT));
                String received = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_RECEIVED));
                String balance = invoice.getString(invoice.getColumnIndex("balance"));
                String billStatus = invoice.getString(invoice.getColumnIndex("bill_status"));
                String comments = invoice.getString(invoice.getColumnIndex("comments"));
                String manualBilling = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MANUAL_BILLING));
                String randomkey = invoice.getString(invoice.getColumnIndex("randomkey"));
                String billKey = invoice.getString(invoice.getColumnIndex("bill_key"));
                String manualBillNo = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MANUAL_BILL_NO));
                String latitude = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_LATITUDE));
                String longitude = invoice.getString(invoice.getColumnIndex("longitude"));
                String enteredBy = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_ENTERED_BY));
                String createdOn = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CREATED_ON));
                String modifiedBy = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MODIFIED_BY));
                String modifiedOn = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_MODIFIED_ON));
//                String billId = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_BILLID));
//                String sgstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_SGST_TAX));
//                String cgstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_CGST_TAX));
//                String igstTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_IGST_TAX));
//                String totalTax = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_TOTAL_TAX));
                String isOnline = invoice.getString(invoice.getColumnIndex(DBHelper.COLUMN_IS_ONLINE));

                allItems.add(new InvoiceDataSource(id, erpId, tripID, billNo,
                        billDate, customerCode, customerName, companyCode, plantCode, salesorgCode,
                        salesareaCode, supervisorCode, itemCount, value, subTotal, discount, tax,
                        grandTotal, roundUp, netAmount, received,
                        balance, billStatus, comments, manualBilling, randomkey, billKey, manualBillNo,
                        latitude, "", enteredBy, createdOn, modifiedBy, modifiedOn,
                        isOnline));
            }
            while (invoice.moveToNext());
        }
        invoice.close();

        return allItems;
    }

    /* Show the Payment Details*/
    public List<PaymentDataSource> getPaymentListDB() {
        List<PaymentDataSource> allItems = new ArrayList<PaymentDataSource>();
        Cursor payment = dbHelper.getTable(DBHelper.TABLE_PAYMENT);
        if (payment.getCount() > 0) {
            payment.moveToFirst();
            do {
                String paymentID = payment.getString(payment.getColumnIndex(DBHelper.COLUMN_PAYMENT_ID));
                String paymentBillNo = payment.getString(payment.getColumnIndex(DBHelper.COLUMN_BILL_ID));
                String paymentMode = payment.getString(payment.getColumnIndex(DBHelper.COLUMN_PAYMENT_MODE));
                String date = payment.getString(payment.getColumnIndex(DBHelper.COLUMN_DATE));
                String paymentBillAmount = payment.getString(payment.getColumnIndex(DBHelper.COLUMN_BILL_AMOUNT));
                allItems.add(new PaymentDataSource(paymentID, paymentBillNo, paymentMode, date, paymentBillAmount));
            }
            while (payment.moveToNext());
        }
        payment.close();

        return allItems;
    }

    //Get Orders List
    public List<OrdersDataSource> getOrdersListDB(String value, String key1, String value1) {
        List<OrdersDataSource> allItems = new ArrayList<OrdersDataSource>();
        Cursor cutomrReprot = dbHelper.getTableList(DBHelper.TABLE_ORDERS, DBHelper.COLUMN_ORDER_STATUS, value, key1, value1, DBHelper.COLUMN_BILL_ID, "DESC");
        if (cutomrReprot.getCount() > 0) {
            cutomrReprot.moveToFirst();
            do {
                String cutomrReprotID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_ID));
                String date = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_DATE));
                String customerID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CUSTOMER_ID));
                String customerName = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME));
                String cutomrReprotManID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_USER_ID));
                String cutomrReprotManName = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_USER_NAME));
                String cutomrReprotBillID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILL_ID));
                String cutomrReprotTotalItems = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILL_TOTAL_ITEMS));
                String customerStateCode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CUSTOMER_STATE_CODE));
                String cutomrReprotManStateCode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_SALES_MAN_STATE_CODE));
                String cutomrReprotodercode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_STATUS));

                Log.e(TAG, "ORDERS LIST: " + date);
                List<CustomersDataSource> customerDB = getCustomerListDB(DBHelper.COLUMN_SALES_AREA_CODE, Constants.salesAreaCode);
                if (customerDB.size() > 0) {
                    for (CustomersDataSource customersDataSource : customerDB) {
                        if (customerID.equals(customersDataSource.getCustomerID())) {
                            String customerEmail = customersDataSource.getCustomerEmail();
                            String customerGSTIN = customersDataSource.getCustomerGSTIN();
                            String customerAddress = customersDataSource.getCustomerAddress();
                            byte[] customerImage = null;
                            customerImage = customersDataSource.getCustomerImageByte();
                            allItems.add(new OrdersDataSource(cutomrReprotID, date, customerID, customerName, customerEmail, customerGSTIN,
                                    customerAddress, cutomrReprotManID, cutomrReprotManName, cutomrReprotBillID, cutomrReprotTotalItems, customerStateCode, cutomrReprotManStateCode,
                                    customerImage, ""));
                        }
                    }
                }
            }
            while (cutomrReprot.moveToNext());

        }
        cutomrReprot.close();
        return allItems;
    }

    public List<OrdersDataSource> getOrdersListDB(String value, String value1, String key2, String value2) {
        List<OrdersDataSource> allItems = new ArrayList<OrdersDataSource>();
        Cursor cutomrReprot = dbHelper.getTableListorder(DBHelper.TABLE_ORDERS,
                DBHelper.COLUMN_CUSTOMER_ID, value, DBHelper.COLUMN_ORDER_STATUS, value1, key2, value2);
        if (cutomrReprot.getCount() > 0) {
            cutomrReprot.moveToFirst();
            do {
                String cutomrReprotID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_ID));
                String date = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_DATE));
                String customerID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CUSTOMER_ID));
                String customerName = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME));
                String cutomrReprotManID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_USER_ID));
                String cutomrReprotManName = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_USER_NAME));
                String cutomrReprotBillID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILL_ID));
                String cutomrReprotTotalItems = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILL_TOTAL_ITEMS));
                String customerStateCode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CUSTOMER_STATE_CODE));
                String cutomrReprotManStateCode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_SALES_MAN_STATE_CODE));
                String cutomrReprotodercode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_STATUS));


                Log.e(TAG, "ORDERS LIST: " + date);
                List<CustomersDataSource> customerDB = getCustomerListDB(DBHelper.COLUMN_SALES_AREA_CODE, Constants.salesAreaCode);
                if (customerDB.size() > 0) {
                    for (CustomersDataSource customersDataSource : customerDB) {
                        if (customerID.equals(customersDataSource.getCustomerID())) {
                            String customerEmail = customersDataSource.getCustomerEmail();
                            String customerGSTIN = customersDataSource.getCustomerGSTIN();
                            String customerAddress = customersDataSource.getCustomerAddress();
                            byte[] customerImage = null;
                            customerImage = customersDataSource.getCustomerImageByte();
                            allItems.add(new OrdersDataSource(cutomrReprotID, date, customerID, customerName, customerEmail, customerGSTIN,
                                    customerAddress, cutomrReprotManID, cutomrReprotManName, cutomrReprotBillID, cutomrReprotTotalItems, customerStateCode, cutomrReprotManStateCode,
                                    customerImage, ""));
                        }
                    }
                }
            }
            while (cutomrReprot.moveToNext());

        }
        cutomrReprot.close();
        return allItems;
    }


    //Get Orders Details
    public List<OrdersDataSource> getOrderDetails(String billNo) {
        List<OrdersDataSource> allItems = new ArrayList<OrdersDataSource>();
        Cursor cutomrReprot = dbHelper.getDetailsList(DBHelper.TABLE_ORDERS_ITEMS, "order", billNo);
        if (cutomrReprot.getCount() > 0) {
            cutomrReprot.moveToFirst();
            do {
                String cutomrReprotID = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDERS_ITEMS_ID));
                String date = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_DATE));
                String cutomrReprotBillNo = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILL_ID));
                String cutomrReprotProductCode = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_CODE));
                String cutomrReprotProductName = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_NAME));
                String cutomrReprotProductRate = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_RATE));
                String cutomrReprotProductQTY = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_PRODUCT_QTY));
                String cutomrReprotProductUOM = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_PRODUCT_UOM));
                String cutomrReprotProductTaxPrice = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_PRODUCT_TAX_PRICE));
                String cutomrReprotTaxPercentage = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_PERCENTAGE));
                String cutomrReprotTaxAmount = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_AMOUNT));
                String cutomrReprotTaxSubTotal = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL));
                String netTotal = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_NET_AMOUNT));
                String cutomrReprotTaxSGST = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_SGST));
                String cutomrReprotTaxSGSTAmount = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_SGST_AMOUNT));
                String cutomrReprotTaxCGST = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_CGST));
                String cutomrReprotTaxCGSTAmount = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_CGST_AMOUNT));
                String cutomrReprotTaxIGST = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_IGST));
                String cutomrReprotTaxIGSTAmount = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_TAX_IGST_AMOUNT));
                Log.e(TAG, "ORDERS DETAIL: " + date);

                allItems.add(new OrdersDataSource(cutomrReprotID, date, cutomrReprotBillNo, cutomrReprotTaxSubTotal, cutomrReprotTaxSGST, cutomrReprotTaxCGST,
                        cutomrReprotTaxIGST, cutomrReprotProductCode, cutomrReprotProductName, cutomrReprotProductRate, cutomrReprotProductQTY, cutomrReprotProductUOM,
                        cutomrReprotProductTaxPrice, cutomrReprotTaxPercentage, cutomrReprotTaxSGSTAmount, cutomrReprotTaxCGSTAmount, cutomrReprotTaxIGSTAmount, cutomrReprotTaxAmount, netTotal));

            }
            while (cutomrReprot.moveToNext());

        }
        cutomrReprot.close();
        return allItems;
    }

    public List<CustomersDataSource> getCustomerReport(String date) {
        List<CustomersDataSource> allItems = new ArrayList<CustomersDataSource>();
        Cursor cutomrReprot = dbHelper.customerReport(date);
        if (cutomrReprot != null && cutomrReprot.getCount() > 0) {
            cutomrReprot.moveToFirst();
            do {
                String id = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ID));
                String name = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_NAME));
                String billing = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILLING));
                String payment = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_PAYMENT));
                String order = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER));
                String ostatus = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_STATUS));
                String pstatus = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_STATUS));
                String bstatus = cutomrReprot.getString(cutomrReprot.getColumnIndex("bill_status"));
                Log.e("TAG", "in or out=" + ostatus + pstatus + bstatus);

                allItems.add(new CustomersDataSource(name, id, billing, payment, order, date, ostatus, pstatus, bstatus));

            }
            while (cutomrReprot.moveToNext());

        }
        cutomrReprot.close();
        return allItems;
    }

    //get customerlatlngreport
    public List<CustomersDataSource> getCustomerReportLatlang(String date) {
        List<CustomersDataSource> allItems = new ArrayList<CustomersDataSource>();
        Cursor cutomrReprot = dbHelper.customerReport(date);
        if (cutomrReprot != null && cutomrReprot.getCount() > 0) {
            cutomrReprot.moveToFirst();
            do {
                String id = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ID));
                String name = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_NAME));
                String billing = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILLING));
                String payment = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_PAYMENT));
                String order = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER));
                String ostatus = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_STATUS));
                String pstatus = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_STATUS));
                String bstatus = cutomrReprot.getString(cutomrReprot.getColumnIndex("bill_status"));
                String latitude = cutomrReprot.getString(cutomrReprot.getColumnIndex("latitude"));
                String longitude = cutomrReprot.getString(cutomrReprot.getColumnIndex("longitude"));
                Log.e("TAG", "in =" + latitude + longitude + pstatus);

                allItems.add(new CustomersDataSource(name, id, billing, payment, order, date, ostatus, pstatus, bstatus,
                        latitude, longitude));

            }
            while (cutomrReprot.moveToNext());

        }
        if (cutomrReprot != null)
            cutomrReprot.close();
        return allItems;
    }

    //get customerNotlatlngreport
    public List<CustomersDataSource> getCustomerNotReportLatlang(String date) {
        List<CustomersDataSource> allItems = new ArrayList<CustomersDataSource>();
        Cursor cutomrReprot = dbHelper.customerNotlocateReport(date);
        if (cutomrReprot != null && cutomrReprot.getCount() > 0) {
            cutomrReprot.moveToFirst();
            do {
                String id = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ID));
                String name = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_NAME));
                String billing = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_BILLING));
                String payment = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_PAYMENT));
                String order = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER));
                String ostatus = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_ORDER_STATUS));
                String pstatus = cutomrReprot.getString(cutomrReprot.getColumnIndex(DBHelper.COLUMN_STATUS));
                String bstatus = cutomrReprot.getString(cutomrReprot.getColumnIndex("bill_status"));
                String latitude = cutomrReprot.getString(cutomrReprot.getColumnIndex("latitude"));
                String longitude = cutomrReprot.getString(cutomrReprot.getColumnIndex("longitude"));
                Log.e("TAG", "out =" + latitude + longitude + pstatus);

                allItems.add(new CustomersDataSource(name, id, billing, payment, order, date, ostatus, pstatus, bstatus,
                        latitude, longitude));
            }
            while (cutomrReprot.moveToNext());

        }
        if (cutomrReprot != null)
            cutomrReprot.close();
        return allItems;
    }


    //get Sales Details
    public List<ProductsDataSource> getSalesDetails(String billNo) {
        List<ProductsDataSource> allItems = new ArrayList<ProductsDataSource>();
        Cursor sales = dbHelper.getDetailsList(DBHelper.TABLE_SALES_ITEMS, "sales", billNo);
        if (sales.getCount() > 0) {
            sales.moveToFirst();
            do {
                String salesID = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_SALES_ITEMS_ID));
                String date = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_DATE));
                String salesBillNo = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_BILL_ID));
                String salesProductCode = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_CODE));
                String salesProductName = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_NAME));
                String salesProductRate = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_RATE));
                String salesProductQTY = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_PRODUCT_QTY));
                String salesProductUOM = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_PRODUCT_UOM));
                String salesProductTaxPrice = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_PRODUCT_TAX_PRICE));
                String salesTaxPercentage = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_PERCENTAGE));
                String salesTaxAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_AMOUNT));
                String salesTaxSubTotal = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL));
                String netTotal = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_NET_AMOUNT));
                String salesTaxSGST = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_SGST));
                String salesTaxSGSTAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_SGST_AMOUNT));
                String salesTaxCGST = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_CGST));
                String salesTaxCGSTAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_CGST_AMOUNT));
                String salesTaxIGST = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_IGST));
                String salesTaxIGSTAmount = sales.getString(sales.getColumnIndex(DBHelper.COLUMN_TAX_IGST_AMOUNT));
                Log.e(TAG, "SALES LIST: " + date);


                allItems.add(new ProductsDataSource(salesID, date, salesBillNo, salesTaxSubTotal, salesTaxSGST, salesTaxCGST,
                        salesTaxIGST, salesProductCode, salesProductName, salesProductRate, salesProductQTY, salesProductUOM,
                        salesProductTaxPrice, salesTaxPercentage, salesTaxSGSTAmount, salesTaxCGSTAmount, salesTaxIGSTAmount, salesTaxAmount, netTotal));

            }
            while (sales.moveToNext());

        }
        sales.close();
        return allItems;
    }

    /* DB User Name*/
    public List<UserDataSource> getUserList() {
        List<UserDataSource> allItems = new ArrayList<UserDataSource>();
        Cursor userList = dbHelper.getUserTable(DBHelper.TABLE_USER);
        if (userList.getCount() > 0) {
            userList.moveToFirst();
            do {
                String userName = userList.getString(userList.getColumnIndex(DBHelper.COLUMN_USER_NAME));
                String userID = userList.getString(userList.getColumnIndex(DBHelper.COLUMN_USER_ID));
                byte[] userImage = userList.getBlob(userList.getColumnIndex(DBHelper.COLUMN_USER_IMAGE));
                //Convert byte to Bitmap
                Bitmap bitmapUserImage = null;
                if (userImage != null) {
                    try {
                        bitmapUserImage = bitmapUtility.getImage(userImage);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else {
                    bitmapUserImage = null;
                }

                Log.e(TAG, "USER: " + "USER");
                allItems.add(new UserDataSource(userName, userID, bitmapUserImage));
            }
            while (userList.moveToNext());
        }
        userList.close();
        return allItems;
    }

    /* DB Data UOM List Name*/
    public List<SelectionDataSource> getUOMList() {
        List<SelectionDataSource> allItems = new ArrayList<SelectionDataSource>();
        Cursor uomList = dbHelper.getTable(DBHelper.TABLE_UOM);
        if (uomList.getCount() > 0) {
            uomList.moveToFirst();
            do {
                int id = uomList.getInt(uomList.getColumnIndex(DBHelper.COLUMN_ID));
                String uomName = uomList.getString(uomList.getColumnIndex(DBHelper.COLUMN_UOM_NAME));
                String uomShort = uomList.getString(uomList.getColumnIndex(DBHelper.COLUMN_UOM_SHORT));
                String date = uomList.getString(uomList.getColumnIndex(DBHelper.COLUMN_DATE));
                Log.e(TAG, "getUOMList: " + date);
                allItems.add(new SelectionDataSource(id, uomName, uomShort));
            }
            while (uomList.moveToNext());
        }
        uomList.close();
        return allItems;
    }

    /* DB Data Tax List Name*/
    public List<SelectionDataSource> getGSTList() {
        List<SelectionDataSource> allItems = new ArrayList<SelectionDataSource>();
        Cursor gstList = dbHelper.getTable(DBHelper.TABLE_TAX);
        if (gstList.getCount() > 0) {
            gstList.moveToFirst();
            do {
                int id = gstList.getInt(gstList.getColumnIndex(DBHelper.COLUMN_ID));
                String taxName = gstList.getString(gstList.getColumnIndex(DBHelper.COLUMN_TAX));
                String taxPercentage = gstList.getString(gstList.getColumnIndex(DBHelper.COLUMN_TAX_PERCENTAGE));
                String date = gstList.getString(gstList.getColumnIndex(DBHelper.COLUMN_DATE));
                Log.e(TAG, "getTaxName: " + date);
                allItems.add(new SelectionDataSource(id, taxPercentage, taxName));
            }
            while (gstList.moveToNext());
        }
        gstList.close();
        return allItems;
    }

    /* Get the Bill Number from Company Table*/
    public String getBillNoDB(String bill) {
        Cursor companyList = dbHelper.getTable(DBHelper.TABLE_COMPANY_BILLS);
        String billNo = null;
        if (companyList.getCount() > 0) {
            companyList.moveToFirst();
            if (bill.equals("cutomrReprot")) {
                billNo = companyList.getString(companyList.getColumnIndex(DBHelper.COLUMN_ORDER_BILL_NO));
            } else if (bill.equals("sales")) {
                billNo = companyList.getString(companyList.getColumnIndex(DBHelper.COLUMN_SALES_BILL_NO));
            }
        }
        companyList.close();
        return billNo;
    }

    /* Read the Database BILL */
    public String getBillDB(String bill, String customerCode) {
        String dbName = null;
        String lastCount = null;
        if (bill.equals(Constants.orders)) {
            dbName = DBHelper.TABLE_FUTURE_REQUIRED;
            lastCount = dbHelper.lastCount(DBHelper.COLUMN_RANDOM_KEY, dbName, DBHelper.COLUMN_ENTERED_BY, customerCode);
        } else if (bill.equals("sales")) {
            dbName = DBHelper.TABLE_BILLING_DETAILS;
            lastCount = dbHelper.lastCount(DBHelper.COLUMN_BILL_NO, dbName, DBHelper.COLUMN_ENTERED_BY, customerCode);
        }

     /*   if (dbName != null) {
            lastCount = dbHelper.lastCount(DBHelper.COLUMN_BILL_NO, dbName);
        }*/

        return lastCount;
    }

    /* Show the Company Details*/
    public List<CompanyDataSource> getCompanyListDB() {
        List<CompanyDataSource> allItems = new ArrayList<CompanyDataSource>();
        Cursor company = dbHelper.getTable(DBHelper.TABLE_COMPANY);
        if (company.getCount() > 0) {
            company.moveToFirst();
            do {
                String companyID = company.getString(company.getColumnIndex(DBHelper.COLUMN_COMPANY_ID));
                String companyName = company.getString(company.getColumnIndex(DBHelper.COLUMN_NAME));
                String companyPhoneNo = company.getString(company.getColumnIndex(DBHelper.COLUMN_PHONE_NO));
                String date = company.getString(company.getColumnIndex(DBHelper.COLUMN_DATE));
                String companyStreet = company.getString(company.getColumnIndex(DBHelper.COLUMN_STREET));
                String companyCity = company.getString(company.getColumnIndex(DBHelper.COLUMN_CITY));
                String companyRegion = company.getString(company.getColumnIndex(DBHelper.COLUMN_REGION));
                String companyPostalCode = company.getString(company.getColumnIndex(DBHelper.COLUMN_PINCODE));
                String companyGSTIN = company.getString(company.getColumnIndex(DBHelper.COLUMN_GSTIN));
                String companyEmail = company.getString(company.getColumnIndex(DBHelper.COLUMN_EMAIL));
                String companyStateCode = company.getString(company.getColumnIndex(DBHelper.COLUMN_STATE_CODE));
                String companyOrderBillNo = company.getString(company.getColumnIndex(DBHelper.COLUMN_ORDER_BILL_NO));
                String companySalesBillNo = company.getString(company.getColumnIndex(DBHelper.COLUMN_SALES_BILL_NO));
                byte[] companyImage = company.getBlob(company.getColumnIndex(DBHelper.COLUMN_COMPANY_IMAGE));
                String companyAddress = companyStreet + "," + companyCity + "," + companyRegion + "," + companyPostalCode;
                companyAddress = dbHelper.removeDuplicateComma(companyAddress);
                //Convert byte to Bitmap
                Bitmap bitmapCompanyImage = null;
                if (companyImage != null) {
                    try {
                        bitmapCompanyImage = bitmapUtility.getImage(companyImage);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else {
                    bitmapCompanyImage = null;
                }
                Log.e(TAG, "Customer Name: " + date);
                Log.e(TAG, "companyAddress: " + companyAddress);
                allItems.add(new CompanyDataSource(companyID, companyName, companyPhoneNo, date, companyEmail, companyGSTIN,
                        companyStreet, companyCity, companyRegion, companyPostalCode, companyAddress, companyStateCode,
                        companyOrderBillNo, companySalesBillNo, bitmapCompanyImage));
            }
            while (company.moveToNext());
        }
        company.close();

        return allItems;
    }

    /* Show the Company Details*/
    public List<CompanyBillsDataSource> getCompanyBillsListDB() {
        List<CompanyBillsDataSource> allItems = new ArrayList<CompanyBillsDataSource>();
        Cursor company = dbHelper.getTable(DBHelper.TABLE_COMPANY_BILLS);
        if (company.getCount() > 0) {
            company.moveToFirst();
            do {
                String companyID = company.getString(company.getColumnIndex(DBHelper.COLUMN_COMPANY_ID));
                String companyOrderBillNo = company.getString(company.getColumnIndex(DBHelper.COLUMN_ORDER_BILL_NO));
                String companySalesBillNo = company.getString(company.getColumnIndex(DBHelper.COLUMN_SALES_BILL_NO));
                allItems.add(new CompanyBillsDataSource(companyID, companyOrderBillNo, companySalesBillNo));
            }
            while (company.moveToNext());
        }
        company.close();

        return allItems;
    }

    /* Show the Company Details*/
    public List<RegionModel> getRegionListDB() {
        List<RegionModel> allItems = new ArrayList<RegionModel>();
        Cursor company = dbHelper.getTableList(DBHelper.TABLE_REGION);
        if (company.getCount() > 0) {
            company.moveToFirst();
            do {
                String level1Id = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL1_ID));
                String level1 = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL1));

                allItems.add(new RegionModel(level1Id, level1));
            }
            while (company.moveToNext());
        }
        company.close();

        return allItems;
    }

    //menu list from table
    public List<MenuDataSource> getMenuList(String value, String value2) {
        //public List<MenuDataSource> getMenuListDB(String value){
        List<MenuDataSource> allItems = new ArrayList<>();
        Cursor menus = dbHelper.getTableList(DBHelper.TABLE_MENU, DBHelper.COLUMN_SCREEN_TYPE, value, DBHelper.COLUMN_LANGUAGE_CODE, value2);
        //  Cursor menus = dbHelper.getTableList(DBHelper.TABLE_MENU,DBHelper.COLUMN_SCREEN_NAME,value);
        if (menus.getCount() > 0) {
            menus.moveToFirst();
            do {
                String Screen = menus.getString(menus.getColumnIndex(DBHelper.COLUMN_SCREEN_NAME));
                String Menu_key = menus.getString(menus.getColumnIndex(DBHelper.COLUMN_SCREEN_VALUE));
                String Image_id = menus.getString(menus.getColumnIndex(DBHelper.COLUMN_IMAGE_ID));

                allItems.add(new MenuDataSource(Menu_key, Screen, Image_id));

            }
            while (menus.moveToNext());
        }
        menus.close();
        return allItems;
    }


    /* Show the Company Details*/
    public List<RegionModel> getDistrictListDB() {
        List<RegionModel> allItems = new ArrayList<RegionModel>();
        Cursor company = dbHelper.getRegionTable(DBHelper.TABLE_DISTRICT);
        if (company.getCount() > 0) {
            company.moveToFirst();
            do {
                String level1Id = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL1_ID));
                String level2Id = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL2_ID));
                String level2 = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL2));

                allItems.add(new RegionModel(level1Id, level2Id, level2));
            }
            while (company.moveToNext());
        }
        company.close();

        return allItems;
    }

    /* Show the Company Details*/
    public List<RegionModel> getRouteListDB() {
        List<RegionModel> allItems = new ArrayList<RegionModel>();
        Cursor company = dbHelper.getRegionTable(DBHelper.TABLE_ROUTE);
        if (company.getCount() > 0) {
            company.moveToFirst();
            do {
                String level1Id = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL1_ID));
                String level2Id = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL2_ID));
                String level3Id = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL3_ID));
                String level3 = company.getString(company.getColumnIndex(DBHelper.COLUMN_LEVEL3));

                allItems.add(new RegionModel(level1Id, level2Id, level3Id, level3));
            }
            while (company.moveToNext());
        }
        company.close();

        return allItems;
    }

    //Get Products List
    public List<MaterialMasterDataSource> getMaterialMasterDB(String value) {
        List<MaterialMasterDataSource> allItems = new ArrayList<MaterialMasterDataSource>();
        // Get the products order by Product Name
        Cursor products = dbHelper.getTableList(DBHelper.TABLE_MATERIAL_MASTER, DBHelper.COLUMN_SALESORG_CODE, value);
        if (products.getCount() > 0) {
            products.moveToFirst();
            do {
                String company_code = products.getString(products.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                String plant_code = products.getString(products.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                String salesorg_code = products.getString(products.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
                String material_code = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_CODE));
                String material_name = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_NAME));
                String material_category = products.getString(products.getColumnIndex(DBHelper.COLUMN_MATERIAL_CATEGORY));
                String uom = products.getString(products.getColumnIndex(DBHelper.COLUMN_UOM));
                String taxdetails = products.getString(products.getColumnIndex(DBHelper.COLUMN_TAX_DETAILS));
                String net_weight = products.getString(products.getColumnIndex(DBHelper.COLUMN_NET_WEIGHT));
                byte[] image = products.getBlob(products.getColumnIndex(DBHelper.COLUMN_PRODUCT_IMAGE));
                //Product Price and Product Total is Same
                allItems.add(new MaterialMasterDataSource(company_code, plant_code, salesorg_code, material_code, material_name, material_category
                        , uom, taxdetails, net_weight, image));
            }
            while (products.moveToNext());
        }
        products.close();
        return allItems;
    }

    public List<SalesDataSource> getCoustmerSalesProductsList(String condition, String value, String key, String value1, String key2, String value2
    ) {
        List<SalesDataSource> allItems = new ArrayList<>();
        //get the table Name order by customerName
        Cursor prod = dbHelper.getTableList(DBHelper.TABLE_BILLING_DETAILS, DBHelper.COLUMN_SUPERVISOR_CODE, value, key,
                value1, key2, value2, DBHelper.COLUMN_BILL_NO, "DESC");
        try {
            if (prod != null && prod.getCount() > 0) {
                prod.moveToFirst();
                do {
                    String billNo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                    String billDate = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                    String billstatus = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_STATUS));
                    String customerCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                    String customerName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME_SMALL));
                    String companyCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                    String plantCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                    String salesorgCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
//                    String salesareaCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                    String itemCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_ITEM_COUNT));
                    String subTotal = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                    String netAmount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_AMOUNT));
                    String total = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_GRAND_TOTAL));
//                    String validFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_FROM));
//                    String validTo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_TO));
//                    String availableQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AVAILABLE_QUANTITY));
//                    String Qty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_QUANTITY));
//                    String discountFromQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM_QUANTITY));
//                    String discountToQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TO_QUANTITY));
//                    String discnt = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
//                    String discountType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TYPE));
//                    String discountWot = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_WOT));
//                    String discountFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM));
//                    String discountTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TILL));
//                    String dayCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DAY_COUNT));
//                    String replaceQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_REPLACE_QUANTITY));
//                    String soldQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SOLD_QUANTITY));
//                    String scheme = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME));
//                    String schemeCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
//                    String schemeName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
//                    String schemeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_QUANTITY));
//                    String schemeFreeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FREE_QUANTITY));
//                    String schemeFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
//                    String schemeTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
//                    String schemeType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
//                    String schemeMaterial1 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL1));
//                    String schemeMaterial2 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL2));
//                    String loyalty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_LOYALTY));
                    byte[] customerImage = dbHelper.getSpcByte(DBHelper.TABLE_CUSTOMER, DBHelper.COLUMN_CUSTOMER_IMAGE, DBHelper.COLUMN_CUSTOMER_CODE, customerCode);

                    DateFormat dateFormat = new SimpleDateFormat(Constants.inputFormat3);
                    Date date = new Date();
                    //  Log.e(TAG, "getDateTime: " + dateFormat.format(date));
                    String currentDate = dateFormat.format(date);
                    allItems.add(new SalesDataSource(billNo, billDate, customerCode, customerName, itemCount, subTotal, netAmount, customerImage, total, billstatus));
                }
                while (prod.moveToNext());
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        assert prod != null;
        prod.close();

        return allItems;
    }

    public List<SalesDataSource> getCoustmerSalesProductsList(String condition, String value, String key, String value1, String key2, String value2, String key3, String value3
    ) {
        List<SalesDataSource> allItems = new ArrayList<>();
        //get the table Name order by customerName
        Cursor prod = dbHelper.getTableList(DBHelper.TABLE_BILLING_DETAILS, DBHelper.COLUMN_SUPERVISOR_CODE,
                value, key, value1, key2, value2, key3, value3, DBHelper.COLUMN_BILL_NO, "DESC");
        try {
            if (prod != null && prod.getCount() > 0) {
                prod.moveToFirst();
                do {
                    String billNo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                    String billDate = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                    String billstatus = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_STATUS));
                    String customerCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                    String customerName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME_SMALL));
                    String companyCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                    String plantCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                    String salesorgCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
//                    String salesareaCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                    String itemCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_ITEM_COUNT));
                    String subTotal = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                    String netAmount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_AMOUNT));
                    String total = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_GRAND_TOTAL));
//                    String validFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_FROM));
//                    String validTo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_TO));
//                    String availableQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AVAILABLE_QUANTITY));
//                    String Qty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_QUANTITY));
//                    String discountFromQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM_QUANTITY));
//                    String discountToQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TO_QUANTITY));
//                    String discnt = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
//                    String discountType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TYPE));
//                    String discountWot = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_WOT));
//                    String discountFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM));
//                    String discountTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TILL));
//                    String dayCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DAY_COUNT));
//                    String replaceQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_REPLACE_QUANTITY));
//                    String soldQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SOLD_QUANTITY));
//                    String scheme = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME));
//                    String schemeCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
//                    String schemeName TABLE_SALES= prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
//                    String schemeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_QUANTITY));
//                    String schemeFreeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FREE_QUANTITY));
//                    String schemeFrom TABLE_SALES= prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
//                    String schemeTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
//                    String schemeType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
//                    String schemeMaterial1 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL1));
//                    String schemeMaterial2 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL2));
//                    String loyalty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_LOYALTY));
                    byte[] customerImage = dbHelper.getSpcByte(DBHelper.TABLE_CUSTOMER, DBHelper.COLUMN_CUSTOMER_IMAGE, DBHelper.COLUMN_CUSTOMER_CODE, customerCode);

                    DateFormat dateFormat = new SimpleDateFormat(Constants.inputFormat3);
                    Date date = new Date();
                    //  Log.e(TAG, "getDateTime: " + dateFormat.format(date));
                    String currentDate = dateFormat.format(date);
                    allItems.add(new SalesDataSource(billNo, billDate, customerCode, customerName, itemCount, subTotal, netAmount, customerImage, total, billstatus));
                }
                while (prod.moveToNext());
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        assert prod != null;
        prod.close();

        return allItems;
    }


    public List<SalesDataSource> getLastRecord(String condition, String value, String key, String value1
    ) {
        List<SalesDataSource> allItems = new ArrayList<>();
        //get the table Name order by customerName
        Cursor prod = dbHelper.table(DBHelper.TABLE_BILLING_DETAILS, DBHelper.COLUMN_BILL_NO);
        try {
            if (prod != null && prod.getCount() > 0) {
                prod.moveToFirst();
                do {
                    String billNo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_NO));
                    String billDate = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_DATE));
                    String billstatus = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_BILL_STATUS));
                    String customerCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_CUSTOMER_CODE_SMALL));
                    String customerName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_CUSTOMER_NAME_SMALL));
                    String companyCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_COMPANY_CODE_SMALL));
                    String plantCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_PLANT_CODE));
                    String salesorgCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALESORG_CODE));
//                    String salesareaCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SALES_AREA_CODE));
                    String itemCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_ITEM_COUNT));
                    String subTotal = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SUB_TOTAL_SMALL));
                    String netAmount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_NET_AMOUNT));
                    String total = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_GRAND_TOTAL));
//                    String validFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_FROM));
//                    String validTo = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_VALID_TO));
//                    String availableQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_AVAILABLE_QUANTITY));
//                    String Qty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_QUANTITY));
//                    String discountFromQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM_QUANTITY));
//                    String discountToQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TO_QUANTITY));
//                    String discnt = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT));
//                    String discountType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TYPE));
//                    String discountWot = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_WOT));
//                    String discountFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_FROM));
//                    String discountTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DISCOUNT_TILL));
//                    String dayCount = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_DAY_COUNT));
//                    String replaceQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_REPLACE_QUANTITY));
//                    String soldQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SOLD_QUANTITY));
//                    String scheme = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME));
//                    String schemeCode = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_CODE));
//                    String schemeName = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_NAME));
//                    String schemeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_QUANTITY));
//                    String schemeFreeQty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FREE_QUANTITY));
//                    String schemeFrom = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_FROM));
//                    String schemeTill = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TILL));
//                    String schemeType = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_TYPE));
//                    String schemeMaterial1 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL1));
//                    String schemeMaterial2 = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_SCHEME_MATERIAL2));
//                    String loyalty = prod.getString(prod.getColumnIndex(DBHelper.COLUMN_LOYALTY));
                    byte[] customerImage = dbHelper.getSpcByte(DBHelper.TABLE_CUSTOMER, DBHelper.COLUMN_CUSTOMER_IMAGE, DBHelper.COLUMN_CUSTOMER_CODE, customerCode);

                    DateFormat dateFormat = new SimpleDateFormat(Constants.inputFormat3);
                    Date date = new Date();
                    //  Log.e(TAG, "getDateTime: " + dateFormat.format(date));
                    String currentDate = dateFormat.format(date);
                    allItems.add(new SalesDataSource(billNo, billDate, customerCode, customerName, itemCount, subTotal, netAmount, customerImage, total, billstatus));
                }
                while (prod.moveToNext());
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        assert prod != null;
        prod.close();

        return allItems;
    }

    /* Show the Reasons Details*/
    public List<Dropdownlist> getReasonListDB() {
        List<Dropdownlist> allItems = new ArrayList<Dropdownlist>();
        Cursor reason = dbHelper.getAllDetails(DBHelper.TABLE_CUSTOMER_REASONS);
        if (reason.getCount() > 0) {
            reason.moveToFirst();
            do {
                String reasonId = reason.getString(reason.getColumnIndex(DBHelper.COLUMN_REASONID));
                String reasonName = reason.getString(reason.getColumnIndex(DBHelper.COLUMN_REASONNAME));
                String isonline = reason.getString(reason.getColumnIndex(DBHelper.COLUMN_IS_ONLINE));
                allItems.add(new Dropdownlist(reasonName, reasonId, isonline));
            }
            while (reason.moveToNext());
        }
        reason.close();
        return allItems;
    }

}