package com.demo.orders.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.demo.orders.db.table.LoginTable;
import com.demo.orders.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String COLUMN_VEHICLE = "vehicle";
    public static final String COLUMN_PRICE_LIST = "price_list";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_PRICE_TAX = "pricetax";
    public static final String COLUMN_NET_PRICE = "net_price";
    public static final String COLUMN_VALID_FROM = "valid_from";
    public static final String COLUMN_VALID_TO = "valid_to";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_FROM_QUANTITY = "from_quantity";
    public static final String COLUMN_DISCOUNT_FROM_QUANTITY = "discount_from_quantity";
    public static final String COLUMN_DISCOUNT_TO_QUANTITY = "discount_to_quantity";
    public static final String COLUMN_TO_QUANTITY = "to_quantity";
    public static final String COLUMN_DISCOUNT = "discount";
    public static final String COLUMN_DISCOUNT_TYPE = "discount_type";
    public static final String COLUMN_DISCOUNT_WOT = "discount_wot";
    public static final String COLUMN_DISCOUNT_FROM = "discount_from";
    public static final String COLUMN_DISCOUNT_TILL = "discount_till";
    public static final String COLUMN_DAY_COUNT = "daycount";
    public static final String COLUMN_REPLACE_QUANTITY = "replace_quantity";
    public static final String COLUMN_SOLD_QUANTITY = "sold_quantity";
    public static final String COLUMN_SCHEME_CODE = "scheme_code";
    public static final String COLUMN_SCHEME_NAME = "scheme_name";
    public static final String COLUMN_SCHEME_QUANTITY = "scheme_quantity";
    public static final String COLUMN_SCHEME_FREE_QUANTITY = "scheme_free_quantity";
    public static final String COLUMN_SCHEME_FROM = "scheme_from";
    public static final String COLUMN_SCHEME = "scheme";
    public static final String COLUMN_SCHEME_TILL = "scheme_till";
    public static final String COLUMN_SCHEME_TYPE = "scheme_type";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_SCHEME_MATERIAL1 = "scheme_material1";
    public static final String COLUMN_SCHEME_MATERIAL2 = "scheme_material2";
    public static final String COLUMN_ORDER = "orders";
    public static final String COLUMN_PAYMENT = "payment";
    public static final String COLUMN_BILLING = "billing";
    public static final String COLUMN_RANDOM_KEY_SMALL = "randomkey";
    public static final String DATABASE_NAME = "pos.db";
    /*User Table*/
    public static final String TABLE_USER = "user";
    /* Customer Table*/
    public static final String TABLE_CUSTOMER = "customer";
    /* Lead Table*/
    public static final String TABLE_Lead = "lead";
    /* UOM  Table*/
    public static final String TABLE_UOM = "uom";
    /* Tax Table*/
    public static final String TABLE_TAX = "tax";
    /* Product  Table*/
    public static final String TABLE_PRODUCT = "products";
    /* Orders  Table*/
    public static final String TABLE_ORDERS = "orders";
    /* Orders Items Table*/
    public static final String TABLE_ORDERS_ITEMS = "ordersItems";
    /* Sales  Table*/
    public static final String TABLE_SALES = "sales";
    /* Sales Man Table*/
    public static final String TABLE_SALES_MAN = "salesMan";
    /* Leader Table*/
    public static final String TABLE_LEADER = "leader";
    /* Sales Items Table*/
    public static final String TABLE_SALES_ITEMS = "salesItems";
    /* Company  Table*/
    public static final String TABLE_COMPANY = "company";
    /* Company Bills  Table*/
    public static final String TABLE_COMPANY_BILLS = "company_Bills";
    /* Payment Table*/
    public static final String TABLE_PAYMENT = "payment";
    /* API Table*/
    public static final String TABLE_API = "api";
    /* Region */
    public static final String TABLE_REGION = "region";
    /* District */
    public static final String TABLE_DISTRICT = "district";
    /* Route */
    public static final String TABLE_ROUTE = "route";
    /*Replacement entry*/
    public static final String TABLE_REPLACEMENT_ENTRY = "replacement_entry";
    /*Supervisor master*/
    public static final String TABLE_SUPERVISOR = "supervisor_master";
    // Customer master table
    public static final String TABLE_CUSTOMER_MASTER = "customer_master";
    /*customer account*/
    public static final String TABLE_CUSTOMER_ACCOUNTS = "customer_accounts";
    /*material master*/
    public static final String TABLE_MATERIAL_MASTER = "material_master";
    /*billing_details*/
    public static final String TABLE_BILLING_DETAILS = "billing_details";
    /*future_require*/
    public static final String TABLE_FUTURE_REQUIRED = "future_require";
    /*return_entry*/
    public static final String TABLE_RETURN_ENTRY = "return_entry";
    /*return_entry*/
    public static final String TABLE_APP_DETAILS = "app_details";
    /*payment_details*/
    public static final String TABLE_PAYMENT_DETAILS = "payment_details";
    /*transaction_details*/
    public static final String TABLE_TRANSACTION_DETAILS = "transaction_details";
    /*product_details*/
    public static final String TABLE_PRODUCT_DETAILS = "product_details";
    /*inventory*/
    public static final String TABLE_INVENTORY = "inventory";
    /*trip log*/
    public static final String TABLE_TRIP_LOG = "trip_log";
    /*trip log*/
    public static final String TABLE_SUPERVISOR_MASTER = "supervisor_master";
    /*menu table*/
    public static final String TABLE_MENU = "menu";
    /*invoice table*/
    public static final String TABLE_INVOICE = "invoice";
    /*Reasons table*/
    public static final String TABLE_CUSTOMER_REASONS = "reasons";
    //customer_feedback//
    public static final String TABLE_CUSTOMER_FEEDBACK = "customer_feedback";
    public static final String COLUMN_REASONNAME = "reason";
    public static final String COLUMN_REASONID = "reason_id";

    //customer_feedback column//
    /* Common Column*/
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NO = "phoneNo";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_REGION = "region";
    public static final String COLUMN_PINCODE = "pincode";
    public static final String COLUMN_GSTIN = "GSTIN";
    public static final String COLUMN_STATE_CODE = "stateCode";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_IS_ONLINE = "isOnline";
    public static final String COLUMN_ORDER_STATUS = "orderstatus";
    public static final String COLUMN_SALES_MAN_STATE_CODE = "salesManStateCode";
    public static final String COLUMN_SALES_MAN_IMAGE = "salesManImg";
    public static final String COLUMN_CUSTOMER_STATE_CODE = "customerStateCode";
    /* Tax column*/
    public static final String COLUMN_TAX_NAME = "taxname";
    public static final String COLUMN_TAX_PERCENTAGE = "taxpercent";
    public static final String COLUMN_TAX = "merge";
    /* UOM  Column*/
    public static final String COLUMN_UOM_NAME = "uomname";
    public static final String COLUMN_UOM_SHORT = "uomshort";
    /* Product Column*/
    public static final String COLUMN_PRODUCT_UOM = "productUOM";
    public static final String COLUMN_PRODUCT_PRICE = "productPrice";
    public static final String COLUMN_PRODUCT_TAX_PRICE = "productTaxPrice";
    public static final String COLUMN_PRODUCT_TAX_VALUE = "productTaxValue";
    public static final String COLUMN_PRODUCT_QTY = "productQty";
    public static final String COLUMN_PRODUCT_TOTAL = "productTotal";
    public static final String COLUMN_PRODUCT_SELECTED_MODE = "productSelectedMode";
    public static final String COLUMN_PRODUCT_SELECTED_GST = "productSelectedGST";
    public static final String COLUMN_PRODUCT_IMAGE = "productImage";
    /* column Unique ID*/
    public static final String COLUMN_COMPANY_ID = "companyID";
    public static final String COLUMN_COMPANY_IMAGE = "companyImage";
    public static final String COLUMN_COMPANY_CODE = "companyCode";
    public static final String COLUMN_ORDER_ID = "orderID";
    public static final String COLUMN_PAYMENT_ID = "paymentID";
    public static final String COLUMN_PROD_ID = "productID";
    /* Bill */
    public static final String COLUMN_ORDER_BILL_NO = "orderBillNo";
    public static final String COLUMN_SALES_BILL_NO = "salesBillNo";
    public static final String COLUMN_LAT_LNG = "latlng";
    /* Payment */
    public static final String COLUMN_BILL_ID = "billID";
    public static final String COLUMN_BILL_AMOUNT = "billAmount";
    public static final String COLUMN_PAYMENT_MODE = "paymentMode";
    public static final String COLUMN_CASH = "cashAmount";
    public static final String COLUMN_CHEQUE_NO = "chequeNo";
    public static final String COLUMN_CHEQUE_DATE = "chequeDate";
    public static final String COLUMN_BANK = "bankName";
    public static final String COLUMN_BANK_CODE = "bankCode";
    public static final String COLUMN_PAYMENT_KEY = "payment_key";
    /* Customer Column*/
    public static final String COLUMN_CUSTOMER_CODE = "customerCode";
    public static final String COLUMN_CUSTOMER_NAME = "customerName";
    public static final String COLUMN_CUSTOMER_ID = "customerID";
    public static final String COLUMN_CUSTOMER_IMAGE = "customerImage";
    /* User Column*/
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_ID = "userID";
    public static final String COLUMN_USER_IMAGE = "userImage";
    public static final String COLUMN_BASE_URL = "baseURL";
    /* Sales Column*/
    public static final String COLUMN_SALES_ID = "salesID";
    public static final String COLUMN_BILL_TOTAL_ITEMS = "billItems";
    public static final String COLUMN_SUB_TOTAL = "subTotal";
    public static final String COLUMN_TAX_TOTAL = "taxTotal";
    public static final String COLUMN_TAX_SGST = "taxSGST";
    public static final String COLUMN_TAX_CGST = "taxCGST";
    public static final String COLUMN_TAX_IGST = "taxIGST";
    public static final String COLUMN_TAX_NET_AMOUNT = "netTotal";
    public static final String COLUMN_PAID_AMOUNT = "paidAmount";
    public static final String COLUMN_PAID_MODE = "mode";
    /* Sales Items Column*/
    public static final String COLUMN_SALES_ITEMS_ID = "salesItemsID";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_LEAD_STATUS = "lead_status";
    public static final String COLUMN_TAX_AMOUNT = "taxAmount";
    public static final String COLUMN_TAX_SGST_AMOUNT = "taxSGSTAmount";
    public static final String COLUMN_TAX_CGST_AMOUNT = "taxCGSTAmount";
    public static final String COLUMN_TAX_IGST_AMOUNT = "taxIGSTAmount";
    /* orders Items Column*/
    public static final String COLUMN_ORDERS_ITEMS_ID = "ordersItemsID";
    public static final String COLUMN_RANDOM_KEY = "random_key";
    /* API Table*/
    public static final String COLUMN_API_NAME = "apiName";
    public static final String COLUMN_API_URL = "apiURL";
    //region
    public static final String COLUMN_LEVEL1_ID = "level1Id";
    public static final String COLUMN_LEVEL1 = "level1";
    public static final String COLUMN_LEVEL2_ID = "level2Id";
    public static final String COLUMN_LEVEL2 = "level2";
    public static final String COLUMN_LEVEL3_ID = "level3Id";
    public static final String COLUMN_LEVEL3 = "level3";
    public static final String COLUMN_PLANT_CODE = "plant_code";
    public static final String COLUMN_SALESORG_CODE = "salesorg_code";
    public static final String COLUMN_SALES_AREA_CODE = "salesarea_code";
    public static final String COLUMN_SCHEME_DISCOUNT = "scheme_discount";
    public static final String COLUMN_PRICE_CODE = "price_code";
    public static final String COLUMN_MATERIAL_CODE = "material_code";
    public static final String COLUMN_LOYALTY = "loyalty";
    public static final String COLUMN_LAST_MODIFIED = "last_modified";
    public static final String COLUMN_SUPERVISOR_CODE = "supervisor_code";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_SUPERVISOR_NAME = "supervisor_name";
    public static final String COLUMN_POSTAL_CODE = "postal_code";
    public static final String COLUMN_IP_ADDRESS = "ip_address";
    public static final String COLUMN_PHONE_NUM = "phone_num";
    public static final String COLUMN_SPECIAL_DISCOUNT = "special_discount";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_INVENTORY = "inventory";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_ENTERED_BY = "entered_by";
    public static final String COLUMN_CREATED_ON = "created_on";
    public static final String COLUMN_MODIFIED_BY = "modified_by";
    public static final String COLUMN_MODIFIED_ON = "modified_on";
    // Customer Account
    public static final String COLUMN_CUSTOMER_CODE_SMALL = "customer_code";
    public static final String COLUMN_LEDGER_BALANCE = "ledger_balance";
    public static final String COLUMN_LOYALTY_POINTS = "loyalty_points";
    public static final String COLUMN_LASTPAY_DATE = "lastpay_date";
    public static final String COLUMN_LASTPAY_AMOUNT = "lastpay_amount";
    public static final String COLUMN_LASTPAY_MODE = "lastpay_mode";
    public static final String COLUMN_LASTCHQ_NO = "lastchq_num";
    public static final String COLUMN_LASTCHQ_DATE = "lastchq_date";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COMPANY_CODE_SMALL = "company_code";
    public static final String COLUMN_MATERIAL_NAME = "material_name";

    // Inventory
    public static final String COLUMN_MATERIAL_TYPE = "material_type";
    public static final String COLUMN_AVAILABLE_QUANTITY = "available_quantity";
    public static final String COLUMN_UNIT_PRICE = "unit_price";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_DISCOUNT_AMOUNT = "discount_amount";
    public static final String COLUMN_TOTAL_DISCOUNT = "total_discount";
    public static final String COLUMN_TAX_PERCENTAGE_SMALL = "tax_percent";
    public static final String COLUMN_SGST = "sgst";
    public static final String COLUMN_CGST = "cgst";
    public static final String COLUMN_IGST = "igst";
    public static final String COLUMN_SGST_PERCENT = "sgst_percent";
    public static final String COLUMN_CGST_PERCENT = "cgst_percent";
    public static final String COLUMN_IGST_PERCENT = "igst_percent";
    public static final String COLUMN_MAIN_TOTAL_DISCOUNT = "main_total_discount";
    public static final String COLUMN_INVENTORY_DATE = "inventory_date";
    //material master
    public static final String COLUMN_MATERIAL_CATEGORY = "material_category";
    public static final String COLUMN_TAX_DETAILS = "taxdetails";
    public static final String COLUMN_NET_WEIGHT = "net_weight";
    public static final String COLUMN_UOM = "uom";
    public static final String COLUMN_ORDER_NO = "order_no";
    //    BillingBilling details
    public static final String COLUMN_BILL_NO = "bill_no";
    public static final String COLUMN_BILL_DATE = "bill_date";
    public static final String COLUMN_CUSTOMER_NAME_SMALL = "customer_name";
    public static final String COLUMN_ITEM_COUNT = "item_count";
    public static final String COLUMN_SUB_TOTAL_SMALL = "sub_total";
    public static final String COLUMN_NET_AMOUNT = "net_amount";
    public static final String COLUMN_TAX_PERCENT = "tax_percent";
    public static final String COLUMN_TOTAL_TAX = "total_tax";
    public static final String COLUMN_TATAL_PRICE = "total_price";
    public static final String COLUMN_MAIN_DISCOUNT_AMOUNT = "main_discount_amount";
    public static final String COLUMN_GRAND_TOTAL = "grand_total";
    //return entry
    public static final String COLUMN_RETURN_DATE = "return_date";
    //trip
    public static final String COLUMN_TRIP = "trip";
    public static final String COLUMN_TRIP_ID = "trip_id";
    public static final String COLUMN_ORDER_DATE = "order_date";
    public static final String COLUMN_TRANSACTION_KEY = "transaction_key";
    public static final String COLUMN_TRIPID = "tripID";
    public static final String COLUMN_TIME = "time";
    //menu table
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_MENU_KEY = "menu_key";
    public static final String COLUMN_SCREEN_NAME = "screen_name";
    public static final String COLUMN_MENU_NAME = "menu_name";
    public static final String COLUMN_LANGUAGE_CODE = "language_code";
    public static final String COLUMN_SCREEN_TYPE = "screen_type";
    public static final String COLUMN_SCREEN_VALUE = "screen_value";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_SCREEN_STATUS = "screen_status";
    public static final String COLUMN_CREATED_DATE = "created_date";
    public static final String COLUMN_SALESMAN_CODE = "salesman_code";
    //invoice table
    public static final String COLUMN_INVOICE_ID = "invoiceid";
    public static final String COLUMN_ERP_ID = "erp_id";
    public static final String COLUMN_INVOICETAX = "tax";
    public static final String COLUMN_ROUND_UP = "round_up";
    public static final String COLUMN_RECEIVED = "received";
    public static final String COLUMN_BALANCE = "balance";
    public static final String COLUMN_BILL_STATUS = "bill_status";
    public static final String COLUMN_MANUAL_BILLING = "manual_billing";
    public static final String COLUMN_MANUAL_BILL_NO = "manual_bill_no";
    public static final String COLUMN_MANUAL_BILLNO = "manual_billno";
    public static final String COLUMN_BILLID = "bill_id";
    public static final String COLUMN_SGST_TAX = "sgst_tax";
    public static final String COLUMN_CGST_TAX = "cgst_tax";
    public static final String COLUMN_IGST_TAX = "igst_tax";
    public static final String COLUMN_PRODUCT_TYPE = "product_type";
    private static final int DATABASE_VERSION = 1;
    private String TAG = "DBHelper";


    /*  public static final String COLUMN_LASTPAY_DATE = "lastpay_date";
    public static final String COLUMN_LASTPAY_AMOUNT = "lastpay_amount";
    public static final String COLUMN_LASTPAY_MODE = "lastpay_mode";
    public static final String COLUMN_LASTCHQ_NO = "lastchq_num";
    public static final String COLUMN_LASTCHQ_DATE = "lastchq_date";*/

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA automatic_index = off;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //customer_frrdback//
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMER_FEEDBACK + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_ID + " TEXT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_TIME + " TEXT ,"
                + COLUMN_DATE + " TEXT, " + COLUMN_LATITUDE + " TEXT ," + COLUMN_LONGITUDE + " TEXT, " + COLUMN_REASONNAME + " TEXT, " + COLUMN_REASONID + " TEXT ,"
                + COLUMN_IS_ONLINE + " TEXT )"
        );
        /*close Reasons*/
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMER_REASONS + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REASONID + " TEXT, " + COLUMN_REASONNAME + " TEXT, "
                + COLUMN_IS_ONLINE + " TEXT )"
        );
        /* invoice */
        db.execSQL("CREATE TABLE " + TABLE_INVOICE + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INVOICE_ID + " TEXT, " + COLUMN_ERP_ID + " TEXT, " + COLUMN_TRIPID + " TEXT, " + COLUMN_BILL_NO + " TEXT, " +
                COLUMN_BILL_DATE + " TEXT," + COLUMN_CUSTOMER_CODE_SMALL + " TEXT, " +
                COLUMN_CUSTOMER_NAME_SMALL + " TEXT, " + COLUMN_COMPANY_CODE_SMALL + " TEXT, " + COLUMN_PLANT_CODE + " TEXT, " +
                COLUMN_SALESORG_CODE + " TEXT, " + COLUMN_SALES_AREA_CODE + " TEXT, " + COLUMN_SUPERVISOR_CODE + " TEXT, " + COLUMN_ITEM_COUNT + " TEXT, " +
                COLUMN_VALUE + " TEXT, " + COLUMN_SUB_TOTAL_SMALL + " TEXT, " + COLUMN_DISCOUNT + " TEXT, " + COLUMN_INVOICETAX + " TEXT, " +
                COLUMN_GRAND_TOTAL + " TEXT, " + COLUMN_ROUND_UP + " TEXT, " + COLUMN_NET_AMOUNT + " TEXT, " + COLUMN_RECEIVED + " TEXT, " +
                "balance DECIMAL(10,2),bill_status VARCHAR(100),comments VARCHAR(200)," + COLUMN_MANUAL_BILLING + " TEXT ," +
                "randomkey VARCHAR(100),bill_key VARCHAR(100)," + COLUMN_MANUAL_BILL_NO + " TEXT ," +
                COLUMN_LATITUDE + " TEXT, " + COLUMN_LONGITUDE + " TEXT, " + COLUMN_ENTERED_BY + " TEXT, " + COLUMN_CREATED_ON + " TEXT," +
                COLUMN_MODIFIED_BY + " TEXT, " + COLUMN_MODIFIED_ON + " TEXT, " + COLUMN_BILLID + " TEXT ," + COLUMN_SGST_TAX + " TEXT ," +
                COLUMN_CGST_TAX + " TEXT, " + COLUMN_IGST_TAX + " TEXT, " + COLUMN_TOTAL_TAX + " TEXT, " + COLUMN_IS_ONLINE + " TEXT )"
        );
        /* User */
        db.execSQL("CREATE TABLE " + TABLE_USER + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COMPANY_CODE + " TEXT, " + COLUMN_PLANT_CODE + " TEXT, " + COLUMN_SALESORG_CODE + " TEXT, " +
                COLUMN_SALES_AREA_CODE + " TEXT," + COLUMN_USER_ID + " TEXT, " +
                COLUMN_SUPERVISOR_CODE + " TEXT, " + COLUMN_EMPLOYEE_ID + " TEXT, " + COLUMN_USER_IMAGE + " BLOB, " +
                COLUMN_SUPERVISOR_NAME + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_POSTAL_CODE + " TEXT, " +
                COLUMN_IP_ADDRESS + " TEXT, " + COLUMN_PHONE_NUM + " TEXT, " + COLUMN_SPECIAL_DISCOUNT + " TEXT, " + COLUMN_USER_NAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " + COLUMN_STATUS + " TEXT, " + COLUMN_INVENTORY + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_COUNTRY + " TEXT, " +
                COLUMN_REGION + " TEXT, " + COLUMN_ENTERED_BY + " TEXT, " + COLUMN_CREATED_ON + " TEXT, " + COLUMN_LAST_MODIFIED + " TEXT, " +
                COLUMN_MODIFIED_BY + " TEXT, " + COLUMN_MODIFIED_ON + " TEXT, " +
                COLUMN_BASE_URL + " TEXT ," + COLUMN_IS_ONLINE + " TEXT )"
        );
        // CUSTOMER  Table
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_ID + " TEXT , "
                + COLUMN_ERP_ID + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_CUSTOMER_CODE + " TEXT, "
                + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_PHONE_NO + " TEXT, " + COLUMN_EMAIL + " TEXT, "
                + COLUMN_GSTIN + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_CITY + " TEXT, "
                + COLUMN_REGION + " TEXT, " + COLUMN_PINCODE + " TEXT, " + COLUMN_STATE_CODE + " TEXT ,"
                + COLUMN_LEAD_STATUS + " TEXT ," + COLUMN_CUSTOMER_IMAGE + " BLOB, " + COLUMN_COMPANY_CODE + " TEXT, "
                + COLUMN_PLANT_CODE + " TEXT, " + COLUMN_SALESORG_CODE + " TEXT, " + COLUMN_SALES_AREA_CODE + " TEXT, " + COLUMN_SCHEME_DISCOUNT + " TEXT, " + COLUMN_PRICE_CODE + " TEXT, "
                + COLUMN_MATERIAL_CODE + " TEXT, " + COLUMN_LOYALTY + " TEXT, " + COLUMN_LAST_MODIFIED + " TEXT, " + COLUMN_IS_ONLINE + " TEXT , " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text)"
        );
        // Products  Table
        db.execSQL("CREATE TABLE " + TABLE_PRODUCT + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROD_ID + " TEXT , "
                + COLUMN_DATE + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_CODE + " TEXT, " + COLUMN_PRODUCT_QTY + " TEXT, "
                + COLUMN_PRODUCT_UOM + " TEXT, " + COLUMN_PRODUCT_PRICE + " TEXT, " + COLUMN_PRODUCT_TAX_PRICE + " TEXT, "
                + COLUMN_PRODUCT_TAX_VALUE + " TEXT, " + COLUMN_TAX_PERCENTAGE + " TEXT , " + COLUMN_PRODUCT_SELECTED_MODE + " TEXT, "
                + COLUMN_PRODUCT_SELECTED_GST + " TEXT , " + COLUMN_PRODUCT_IMAGE + " BLOB, " + COLUMN_IS_ONLINE + " TEXT ) "
        );

        // Company Table
        db.execSQL("CREATE TABLE " + TABLE_COMPANY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMPANY_ID + " TEXT , "
                + COLUMN_NAME + " TEXT, " + COLUMN_PHONE_NO + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_GSTIN + " TEXT, "
                + COLUMN_DATE + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_REGION + " TEXT, "
                + COLUMN_PINCODE + " TEXT, " + COLUMN_STATE_CODE + " TEXT, " + COLUMN_ORDER_BILL_NO + " TEXT, " + COLUMN_SALES_BILL_NO + " TEXT ,"
                + COLUMN_COMPANY_IMAGE + " BLOB, " + COLUMN_IS_ONLINE + " TEXT )"
        );
        // Company Bill Table
        db.execSQL("CREATE TABLE " + TABLE_COMPANY_BILLS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMPANY_ID + " TEXT , " + COLUMN_DATE + " TEXT, "
                + COLUMN_ORDER_BILL_NO + " TEXT, " + COLUMN_SALES_BILL_NO + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )"
        );

        // Company Table
        db.execSQL("CREATE TABLE " + TABLE_REGION + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LEVEL1_ID + " TEXT , " + COLUMN_LEVEL1 + " TEXT, "
                + COLUMN_IS_ONLINE + " TEXT  )"
        );

        // Company Table
        db.execSQL("CREATE TABLE " + TABLE_DISTRICT + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LEVEL1_ID + " TEXT , " + COLUMN_LEVEL2_ID + " TEXT, "
                + COLUMN_LEVEL2 + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )"
        );

        // Company Table
        db.execSQL("CREATE TABLE " + TABLE_ROUTE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LEVEL1_ID + " TEXT , " + COLUMN_LEVEL2_ID + " TEXT, "
                + COLUMN_LEVEL3_ID + " TEXT, " + COLUMN_LEVEL3 + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )"
        );


        /* Tax  Table */
        db.execSQL("CREATE TABLE " + TABLE_TAX + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " + COLUMN_TAX_NAME + " TEXT, " + COLUMN_TAX_PERCENTAGE + " TEXT ,"
                + COLUMN_TAX + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )"
        );
        /* UOM  Table */
        db.execSQL("CREATE TABLE " + TABLE_UOM + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " + COLUMN_UOM_NAME + " TEXT, " + COLUMN_UOM_SHORT + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )"
        );
        // Payment Table
        db.execSQL("CREATE TABLE " + TABLE_PAYMENT + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PAYMENT_ID + " TEXT , "
                + COLUMN_LEDGER_BALANCE + " TEXT, " + COLUMN_PAYMENT_MODE + " TEXT, " + COLUMN_CASH + " TEXT, " + COLUMN_CHEQUE_NO + " TEXT, "
                + COLUMN_CHEQUE_DATE + " TEXT, " + COLUMN_BANK + " TEXT, " + COLUMN_BANK_CODE + " TEXT, " + COLUMN_BILL_ID + " TEXT, "
                + COLUMN_DATE + " TEXT, " + COLUMN_TRIPID + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )");
        /* API Table */
        db.execSQL("CREATE TABLE " + TABLE_API + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " + COLUMN_API_NAME + " TEXT, " + COLUMN_API_URL + " TEXT, " + COLUMN_IS_ONLINE + " TEXT  )"
        );
        db.execSQL("CREATE TABLE " + TABLE_SALES_MAN + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_PHONE_NO + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_REGION + " TEXT, "
                + COLUMN_STATE + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_SALES_MAN_IMAGE + " BLOB, " + COLUMN_IS_ONLINE + " TEXT  )"
        );
        db.execSQL("CREATE TABLE " + TABLE_LEADER + "( " + COLUMN_ID + "   INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_PHONE_NO + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_REGION + " TEXT, "
                + COLUMN_STATE + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_SALES_MAN_IMAGE + " BLOB, " + COLUMN_IS_ONLINE + " TEXT  )"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS user_master (id INTEGER , " + COLUMN_USER_NAME + " VARCHAR PRIMARY KEY, " + COLUMN_PASSWORD + " VARCHAR,version VARCHAR, app_version VARCHAR, today_date DATE, " + COLUMN_IS_ONLINE + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS bill_setting (id INTEGER PRIMARY KEY  NOT NULL , header_1 VARCHAR DEFAULT (null) ,header_2 VARCHAR DEFAULT (null), " +
                "header_3 VARCHAR, setting_id INTEGER, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BILLING_DETAILS + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + COLUMN_BILL_NO + " VARCHAR(100) NOT NULL ," +
                COLUMN_BILL_DATE + " DATE DEFAULT (null) ," + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100)," + COLUMN_CUSTOMER_NAME_SMALL + " VARCHAR(100), " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100), " + COLUMN_PLANT_CODE + "  VARCHAR(100)," +
                " " + COLUMN_SALESORG_CODE + "  VARCHAR(100), " + COLUMN_SALES_AREA_CODE + "  VARCHAR(100), " + COLUMN_SUPERVISOR_CODE + "  VARCHAR(100)," + COLUMN_ITEM_COUNT + " INTEGER, value DECIMAL(10,2)," + COLUMN_DISCOUNT + " DECIMAL(10,2)," +
                COLUMN_SUB_TOTAL_SMALL + " DECIMAL(10,2),tax DECIMAL(10,2)," + COLUMN_GRAND_TOTAL + " DECIMAL(10,2),round_up DECIMAL(10,2),net_amount DECIMAL(10,2),main_discount DECIMAL(10,2)," +
                "main_sub_total DECIMAL(10,2),main_tax DECIMAL(10,2),main_grand_total DECIMAL(10,2),main_net_amount DECIMAL(10,2),received DECIMAL(10,2)," +
                "balance DECIMAL(10,2),bill_status VARCHAR(100),comments VARCHAR(200),randomkey VARCHAR(100),entered_by VARCHAR(100),created_on VARCHAR(100)," +
                "bill_key VARCHAR(100),total_sgst DECIMAL(10,2),total_cgst DECIMAL(10,2),total_igst DECIMAL(10,2), show_total_sgst DECIMAL(10,2)," +
                "show_total_cgst DECIMAL(10,2),show_total_igst DECIMAL(10,2),manual_billno VARCHAR(100), " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text, "
                + COLUMN_TRIPID + " TEXT, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS  " + TABLE_CUSTOMER_ACCOUNTS + "  (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100)," + COLUMN_LEDGER_BALANCE + " decimal(10,2)," + COLUMN_LOYALTY_POINTS + " VARCHAR(100) DEFAULT (null) , " + COLUMN_LASTPAY_DATE + "  date, " + COLUMN_LASTPAY_AMOUNT + "  decimal(10,2), " + COLUMN_LASTPAY_MODE + "  VARCHAR(100), " + COLUMN_LASTCHQ_NO + "  INTEGER, " + COLUMN_LASTCHQ_DATE + "  VARCHAR, " + COLUMN_DATE + " VARCHAR, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS  " + TABLE_CUSTOMER_MASTER + "  (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_PLANT_CODE + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_SALESORG_CODE + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_SALES_AREA_CODE + "  VARCHAR(100) DEFAULT (null) ," + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100) DEFAULT (null) ,customer_name VARCHAR(100) DEFAULT (null) ,street VARCHAR(100) DEFAULT (null) ,city VARCHAR(100) DEFAULT (null) ,postal_code NUMERIC,price_code VARCHAR,loyalty VARCHAR DEFAULT (null),scheme_discount VARCHAR,gst_num VARCHAR , " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS discount_master (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , discount_code VARCHAR(100),  " + COLUMN_MATERIAL_CODE + "  VARCHAR(100), " + COLUMN_FROM_QUANTITY + " INTEGER, " + COLUMN_TO_QUANTITY + " INTEGER, " + COLUMN_DISCOUNT + " INTEGER, " + COLUMN_DISCOUNT_WOT + " VARCHAR, " + COLUMN_DISCOUNT_FROM + " date, " + COLUMN_DISCOUNT_TILL + " date, " + COLUMN_DISCOUNT_TYPE + " VARCHAR, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_INVENTORY + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100), " + COLUMN_PLANT_CODE + "  VARCHAR(100), " + COLUMN_SALESORG_CODE + "  VARCHAR(100), " + COLUMN_SALES_AREA_CODE + "  VARCHAR(100), " + COLUMN_MATERIAL_CODE + "  VARCHAR(100), " + COLUMN_MATERIAL_NAME + "  VARCHAR(100)," + COLUMN_QUANTITY + " INTEGER, " + COLUMN_AVAILABLE_QUANTITY + "  INTEGER, " + COLUMN_SUPERVISOR_CODE + "  VARCHAR(100),machine_id VARCHAR(100)," + COLUMN_VEHICLE + " VARCHAR(100)," + COLUMN_INVENTORY_DATE + " date,randomkey VARCHAR(100),inv_key VARCHAR(100)," + COLUMN_SOLD_QUANTITY + " INTEGER ," + COLUMN_REPLACE_QUANTITY + " INTEGER, " + COLUMN_DAY_COUNT + " INTEGER, " + COLUMN_PRODUCT_IMAGE + " BLOB, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS machine_details (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , date DATE,  " + COLUMN_SUPERVISOR_CODE + "  VARCHAR(100), machine_id VARCHAR(100), machine_name VARCHAR(100), " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_MATERIAL_MASTER + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_PLANT_CODE + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_SALESORG_CODE + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_MATERIAL_CODE + "  VARCHAR(100) DEFAULT (null) , " + COLUMN_MATERIAL_NAME + "  VARCHAR(100) DEFAULT (null) ," + COLUMN_MATERIAL_CATEGORY + " VARCHAR(100) DEFAULT (null) ," + COLUMN_UOM + " VARCHAR(100) DEFAULT (null) ," + COLUMN_TAX_DETAILS + " DECIMAL(10,2), " + COLUMN_NET_WEIGHT + " DECIMAL(10,2) DEFAULT (null) , " + COLUMN_PRODUCT_IMAGE + " text, " + COLUMN_IS_ONLINE + " TEXT)");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PAYMENT_DETAILS + " (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
                COLUMN_DATE + " DATE,  " + COLUMN_TIME + " TEXT, " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100),  " + COLUMN_SALES_AREA_CODE + "  VARCHAR(100), "
                + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100), mode VARCHAR(100), " + COLUMN_AMOUNT + " DECIMAL(10,2), cheque_number VARCHAR(100), " +
                "cheque_date DATE, bank_name VARCHAR(100), randomkey VARCHAR(100), entered_by VARCHAR(100), created_on VARCHAR(100), " +
                "status VARCHAR(100), bill_no VARCHAR(100)," + COLUMN_PAYMENT_KEY + " VARCHAR(100), " + COLUMN_LATITUDE + " text, "
                + COLUMN_LONGITUDE + " text, " + COLUMN_TRIPID + " TEXT, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS price_master (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,  " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100),  " + COLUMN_PLANT_CODE + "  VARCHAR(100),  " + COLUMN_SALESORG_CODE + "  VARCHAR(100)," +
                " " + COLUMN_PRICE_LIST + " VARCHAR(100),  " + COLUMN_MATERIAL_CODE + "  VARCHAR(100), " + COLUMN_AMOUNT + " decimal(10,2), tax decimal(10,2), " + COLUMN_TAX_AMOUNT + "  VARCHAR(100),   " + COLUMN_NET_PRICE + " decimal(10,2), " + COLUMN_VALID_FROM + " date, valid_to date, price_id INTEGER, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS remember_me (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " + COLUMN_USER_NAME + " VARCHAR(100), " + COLUMN_PASSWORD + " VARCHAR(100), " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS product_details (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, bill_no VARCHAR(100), bill_date DATE,  "
                + COLUMN_MATERIAL_CODE + "  VARCHAR(100),  " + COLUMN_MATERIAL_NAME + "  VARCHAR(100), material_type VARCHAR(100), " +
                COLUMN_QUANTITY + " INTEGER, unit_price DECIMAL(10,2) , value DECIMAL(10,2) , discount_amount DECIMAL(10,2) , total_discount DECIMAL(10,2), " +
                "sub_total DECIMAL(10,2) , tax_percent DECIMAL(10,2) , total_tax  DECIMAL(10,2), total_price DECIMAL(10,2), " +
                "main_discount_amount DECIMAL(10,2) , main_total_discount DECIMAL(10,2), main_sub_total DECIMAL(10,2) , " +
                "main_total_tax  DECIMAL(10,2), main_total_price DECIMAL(10,2), " + COLUMN_SCHEME + " VARCHAR(100), " +
                COLUMN_SCHEME_CODE + " VARCHAR(100), randomkey VARCHAR(100), entered_by VARCHAR(100), created_on DATE, status VARCHAR(100)," +
                "product_key VARCHAR(100),sgst DECIMAL(10,2),cgst DECIMAL(10,2),igst DECIMAL(10,2),sgst_percent DECIMAL(10,2)," +
                "cgst_percent DECIMAL(10,2),igst_percent DECIMAL(10,2), " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text, "
                + COLUMN_TRIPID + " TEXT, " + COLUMN_PRODUCT_TYPE + " TEXT, " + COLUMN_IS_ONLINE + " TEXT)");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FUTURE_REQUIRED + " (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, order_no VARCHAR(100), order_date DATE,  "
                + COLUMN_MATERIAL_CODE + "  VARCHAR(100),  " + COLUMN_MATERIAL_NAME + "  VARCHAR(100), material_type VARCHAR(100), " +
                COLUMN_QUANTITY + " INTEGER, unit_price DECIMAL(10,2) , value DECIMAL(10,2) , discount_amount DECIMAL(10,2) , total_discount DECIMAL(10,2), " +
                "sub_total DECIMAL(10,2) , tax_percent DECIMAL(10,2) , total_tax  DECIMAL(10,2), total_price DECIMAL(10,2), " +
                "main_discount_amount DECIMAL(10,2) , main_total_discount DECIMAL(10,2), main_sub_total DECIMAL(10,2) , " +
                "main_total_tax  DECIMAL(10,2), main_total_price DECIMAL(10,2), scheme_discount VARCHAR(100), " +
                COLUMN_SCHEME_CODE + " VARCHAR(100), random_key VARCHAR(100), entered_by VARCHAR(100), created_on DATE, status VARCHAR(100)," +
                "product_key VARCHAR(100),sgst DECIMAL(10,2),cgst DECIMAL(10,2),igst DECIMAL(10,2),sgst_percent DECIMAL(10,2)," +
                "cgst_percent DECIMAL(10,2),igst_percent DECIMAL(10,2), " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text, "
                + COLUMN_TRIPID + " TEXT, " + COLUMN_PRODUCT_TYPE + " TEXT,  loyalty_type TEXT, "
                + COLUMN_COMPANY_CODE_SMALL + " text, " + COLUMN_SALESORG_CODE + " text, " + COLUMN_PLANT_CODE + " TEXT, "
                + COLUMN_SALES_AREA_CODE + " TEXT, " + COLUMN_CUSTOMER_CODE_SMALL + " TEXT," + COLUMN_CUSTOMER_NAME_SMALL + " TEXT," +
                COLUMN_IS_ONLINE + " TEXT)");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_REPLACEMENT_ENTRY + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100), " + COLUMN_SALES_AREA_CODE + "  VARCHAR(100)," + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100), " + COLUMN_MATERIAL_CODE + "  VARCHAR(100), " + COLUMN_MATERIAL_NAME + "  VARCHAR(100),date DATE,return_quantity INTEGER," + COLUMN_REPLACE_QUANTITY + " INTEGER DEFAULT (null) ,balance_quantity INTEGER,replace_key VARCHAR(100),randomkey VARCHAR(100), " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RETURN_ENTRY + "(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,  " + COLUMN_COMPANY_CODE_SMALL + "  VARCHAR(100),  " + COLUMN_SALES_AREA_CODE + "  VARCHAR(100), " + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100), returns VARCHAR(100), " + COLUMN_RETURN_DATE + " DATE,  " + COLUMN_MATERIAL_CODE + "  VARCHAR(100),  " + COLUMN_MATERIAL_NAME + "  VARCHAR(100), return_quantity INTEGER, replacement_quantity INTEGER, balance_quantity INTEGER, replace_date DATE, status VARCHAR(100), " + COLUMN_ENTERED_BY + " VARCHAR(100), created_on DATE,return_key VARCHAR(100), randomkey VARCHAR(100), " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text, " + COLUMN_IS_ONLINE + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS scheme_master (id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " + COLUMN_SCHEME_CODE + " VARCHAR(100), " + COLUMN_SCHEME_NAME + " VARCHAR(100), material_code1 VARCHAR(100), material_code2 VARCHAR(100), " + COLUMN_QUANTITY + " INTEGER, free_quantity INTEGER, " + COLUMN_SCHEME + " VARCHAR(100), " + COLUMN_SCHEME_FROM + " date, " + COLUMN_SCHEME_TILL + " date, " + COLUMN_SCHEME_TYPE + " VARCHAR, " + COLUMN_IS_ONLINE + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SUPERVISOR + " (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,  " + COLUMN_SUPERVISOR_CODE + "  VARCHAR(100), " + COLUMN_SUPERVISOR_NAME + " VARCHAR(100), " + COLUMN_USER_NAME + " VARCHAR(100), " + COLUMN_PASSWORD + " VARCHAR(100), " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTION_DETAILS + " (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , date DATE, "
                + COLUMN_CUSTOMER_CODE_SMALL + " VARCHAR(100), " + COLUMN_DESCRIPTION + " VARCHAR(200), credit DECIMAL(10,2), debit DECIMAL(10,2), " +
                "randomkey VARCHAR(100), entered_by VARCHAR(100), created_on VARCHAR(100), status VARCHAR(100), " + COLUMN_TRANSACTION_KEY + " VARCHAR(100), "
                + COLUMN_TRIPID + " TEXT, " + COLUMN_LATITUDE + " text, " + COLUMN_LONGITUDE + " text, " + COLUMN_IS_ONLINE + " TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS updates (id INTEGER PRIMARY KEY  NOT NULL ,table_name VARCHAR(100) NOT NULL  DEFAULT (null) ,datetime DATETIME,date DATE, " + COLUMN_IS_ONLINE + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS backup_details (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , date DATE, time TIME, datetime VARCHAR, filename VARCHAR,  " + COLUMN_SUPERVISOR_CODE + "  VARCHAR, " + COLUMN_IS_ONLINE + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_APP_DETAILS + "(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , date DATE, address VARCHAR, name VARCHAR, " + COLUMN_IS_ONLINE + " TEXT)");
//        db.execSQL('INSERT INTO user_master (id, username, password, version, app_version, today_date) VALUES ("1", "admin", "21232f297a57a5a743894a0e4a801fc3", "0", "0", "'+todaydate+'")');
//        db.execSQL("SELECT * FROM user_master where version='0' ", [], function(tx, res) {
        db.execSQL("CREATE TABLE IF NOT EXISTS bill_header (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , header_1 VARCHAR(100), header_2 VARCHAR(100), header_3 VARCHAR(100), " + COLUMN_IS_ONLINE + " TEXT)");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TRIP_LOG + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + COLUMN_TRIP + " TEXT, " +
                COLUMN_TRIP_ID + " TEXT  )");

        //menu table
        db.execSQL("CREATE TABLE " + TABLE_MENU + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SCREEN_TYPE + " TEXT , " + COLUMN_SCREEN_NAME + " TEXT , " + COLUMN_SCREEN_VALUE + " TEXT, " + COLUMN_IMAGE_ID + " TEXT," + COLUMN_MENU_NAME + " TEXT,"
                + COLUMN_LANGUAGE_CODE + " TEXT," + COLUMN_SCREEN_STATUS + " TEXT," + COLUMN_CREATED_BY + " TEXT," + COLUMN_CREATED_DATE + " TEXT," + COLUMN_COMPANY_CODE + " TEXT,"
                + COLUMN_SALESMAN_CODE + " TEXT," + COLUMN_SALESORG_CODE + " TEXT," + COLUMN_SALES_AREA_CODE + " TEXT," + COLUMN_PLANT_CODE + "  DATE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY_BILLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_API);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES_MAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTRICT);

        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_CUSTOMER_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_CUSTOMER_MASTER);
        db.execSQL("DROP TABLE IF EXISTS discount_master ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        db.execSQL("DROP TABLE IF EXISTS machine_details ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIAL_MASTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS price_master ");
        db.execSQL("DROP TABLE IF EXISTS remember_me ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPLACEMENT_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RETURN_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS scheme_master ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPERVISOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS updates ");
        db.execSQL("DROP TABLE IF EXISTS backup_details ");
        db.execSQL("DROP TABLE IF EXISTS bill_header ");
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_APP_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_TRIP_LOG);
//        db.execSQL('INSERT INTO user_master (id, username, password, version, app_version, today_date) VALUES ("1", "admin", "21232f297a57a5a743894a0e4a801fc3", "0", "0", "'+todaydate+'")');
//        db.execSQL("SELECT * FROM user_master where version='0' ", [], function(tx, res) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUTURE_REQUIRED);
        //drop menu table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        //drop invoice table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICE);
        //drop reasons table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_REASONS);

        onCreate(db);
    }

    public void insertcustomer_feedback(String customer_id, String customer_name, String time, String date, String latitude, String longitude, String reason, String reason_id, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_ID, customer_id);
        contentValues.put(COLUMN_CUSTOMER_NAME, customer_name);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);
        contentValues.put(COLUMN_REASONNAME, reason);
        contentValues.put(COLUMN_REASONID, reason_id);
        contentValues.put(COLUMN_IS_ONLINE, isonline);
        db.insert(TABLE_CUSTOMER_FEEDBACK, null, contentValues);
    }

    //insert customer reasons
    public void insertreasons(String reason_id, String reason, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_REASONNAME, reason);
        contentValues.put(COLUMN_REASONID, reason_id);
        contentValues.put(COLUMN_IS_ONLINE, isonline);
        db.insert(TABLE_CUSTOMER_REASONS, null, contentValues);
    }

    public void tripInsert(String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRIP, status);
        db.insert(TABLE_TRIP_LOG, null, contentValues);
    }

    public void tripUpdate(String status, String tripId, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRIP, status);
        contentValues.put(COLUMN_TRIP_ID, tripId);
        db.update(TABLE_TRIP_LOG, contentValues, "id=?", new String[]{id});
    }

    public void ledgerUpdate(String customercode, String ledgeramount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LEDGER_BALANCE, ledgeramount);
        db.update(TABLE_CUSTOMER_ACCOUNTS, contentValues, COLUMN_CUSTOMER_CODE_SMALL + "=?", new String[]{customercode});
    }

    //Insert User Data
    public int insertBillingDetails(String bill_no, String bill_date, String customer_code, String customer_name, String companyCode, String plantCode,
                                    String salesOrgCode, String salesAreaCode, String supervisorCode, String item_count, String value, String discount,
                                    String sub_total, String tax, String grand_total, String round_up, String net_amount, String main_discount,
                                    String main_sub_total, String main_tax, String main_grand_total, String main_net_amount, String received, String balance,
                                    String bill_status, String comments,
                                    String randomkey, String entered_by, String created_on, String bill_key, String total_sgst, String total_cgst,
                                    String total_igst, String show_total_sgst, String show_total_cgst, String show_total_igst,
                                    String manual_billno, String latitude, String longtude, String isOnline, String tripId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BILL_NO, bill_no);
        contentValues.put("bill_date", bill_date);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put("customer_name", customer_name);
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, companyCode);
        contentValues.put(COLUMN_PLANT_CODE, plantCode);
        contentValues.put(COLUMN_SALESORG_CODE, salesOrgCode);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesAreaCode);
        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisorCode);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longtude);
        contentValues.put("item_count", item_count);
        contentValues.put("value", value);
        contentValues.put(COLUMN_DISCOUNT, discount);

        contentValues.put("sub_total", sub_total);
        contentValues.put("tax", tax);
        contentValues.put("grand_total", grand_total);
        contentValues.put("round_up", round_up);
        contentValues.put("net_amount", net_amount);
        contentValues.put("main_discount", main_discount);
        contentValues.put("main_sub_total", main_sub_total);
        contentValues.put("main_tax", main_tax);

        contentValues.put("main_grand_total", main_grand_total);
        contentValues.put("main_net_amount", main_net_amount);
        contentValues.put("received", received);
        contentValues.put("balance", balance);
        contentValues.put("bill_status", bill_status);
        contentValues.put("comments", comments);
        contentValues.put("randomkey", randomkey);
        contentValues.put("entered_by", entered_by);
        contentValues.put("created_on", created_on);
        contentValues.put("bill_key", bill_key);
        contentValues.put("total_sgst", total_sgst);
        contentValues.put("total_cgst", total_cgst);
        contentValues.put("total_igst", total_igst);
        contentValues.put("show_total_sgst", show_total_sgst);
        contentValues.put("show_total_cgst", show_total_cgst);
        contentValues.put("show_total_igst", show_total_igst);
        contentValues.put("manual_billno", manual_billno);
        contentValues.put(COLUMN_TRIPID, tripId);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);

        db.insert(TABLE_BILLING_DETAILS, null, contentValues);
        return 0;
    }

    //Insert User Data
    public int insertProductDetails(String bill_no, String bill_date, String material_code, String material_name, String material_type, String quantity,
                                    String unit_price, String discount_amount, String total_discount, String tax_percent, String total_tax, String scheme_code,
                                    String scheme, String value, String sub_total, String total_price, String entered_by, String created_on,
                                    String main_discount_amount, String main_total_discount, String main_total_tax, String main_sub_total, String main_total_price,
                                    String product_key, String sgst, String cgst, String igst, String sgst_percent, String cgst_percent, String igst_percent, String isOnline,
                                    String tripId, String productType, String order_status, String loyalty_type, LoginTable loginTable) {

        SQLiteDatabase db = this.getWritableDatabase();
        String main_dis_amt = "0";
        if (main_discount_amount != null && !main_discount_amount.equals("null")) {
            main_dis_amt = main_discount_amount;
        }
        String dis_amt = "0";
        if (discount_amount != null && !discount_amount.equals("null")) {
            dis_amt = discount_amount;
        }
        String sch = "";
        if (scheme != null && !scheme.equals("null")) {
            sch = scheme;
        }
        String sch_cod = "";
        if (scheme_code != null && !scheme_code.equals("null")) {
            sch_cod = scheme_code;
        }
        String amountPrice = String.valueOf(Double.parseDouble(main_total_price));
        String checkdata = "select * from " + TABLE_FUTURE_REQUIRED + " where " + COLUMN_ORDER_NO + "='" + bill_no +
                "' and material_code ='" + material_code + "' and " + COLUMN_ORDER_DATE + "='" + bill_date +
                "' and " + COLUMN_PRODUCT_TYPE + " ='" + productType + "'";
        Log.e(TAG, "Table: " + checkdata);
        Cursor res = db.rawQuery(checkdata, null);
        if (res != null && res.getCount() == 0) {
            String sample = "INSERT INTO " + TABLE_FUTURE_REQUIRED + " (order_no, order_date, material_code, material_name, " + COLUMN_MATERIAL_TYPE + ", quantity, " +
                    COLUMN_UNIT_PRICE + ", " + COLUMN_DISCOUNT_AMOUNT + ", " + COLUMN_TOTAL_DISCOUNT + ", " + COLUMN_TAX_PERCENT + ", " + COLUMN_TOTAL_TAX + ", scheme_code, scheme_discount, value, sub_total, total_price," +
                    " entered_by, created_on, " + COLUMN_MAIN_DISCOUNT_AMOUNT + ", " + COLUMN_MAIN_TOTAL_DISCOUNT + ", main_total_tax, main_sub_total, main_total_price, " +
                    " random_key, sgst, cgst, igst, sgst_percent, cgst_percent, igst_percent," + COLUMN_PRODUCT_TYPE + "," + COLUMN_STATUS + " , loyalty_type , company_code, salesorg_code, salesarea_code, customer_code,customer_name,plant_code, " + COLUMN_IS_ONLINE + ") " + "VALUES" +
                    "('" + bill_no + "', '" + bill_date + "', '" + material_code + "', '" + material_name + "', '" + productType + "', '" + quantity +
                    "', '" + unit_price + "', '" + dis_amt + "', '" + total_discount + "', '" + tax_percent + "', '" + total_tax + "', '" + sch_cod +
                    "', '" + sch + "', '" + value + "', '" + sub_total + "', '" + total_price + "', '" + entered_by + "', '" + created_on +
                    "', '" + main_dis_amt + "', '" + main_total_discount + "', '" + main_total_tax + "', '" + main_sub_total + "', '" + amountPrice + "', '" + product_key + "', '" + sgst + "', '" + cgst + "', '" + igst + "', '" +
                    sgst_percent + "', '" + cgst_percent + "', '" + igst_percent + "', '" + productType + "', '" + order_status + "', '" + loyalty_type + "', '" +
                    loginTable.getCompanyCode() + "', '" + loginTable.getSalesorgCode() + "', '" + loginTable.getSalesareaCode() + "', '" + loginTable.getCustomerCode() + "', '" + loginTable.getCustomerName() + "', '" + loginTable.getPlantCode() + "', '"
                    + isOnline + "')";
            db.execSQL(sample);
        }
        return 0;
    }

    //Update customer_image
    public void updateCUstomerImage(byte[] c_image, String c_code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_IMAGE, c_image);
        Log.e("TAG", "COLUMN_CUSTOMER_IMAGE==" + c_image.length);
        // contentValues.put(COLUMN_IS_ONLINE, isonline);
        db.update(TABLE_CUSTOMER, contentValues, COLUMN_CUSTOMER_CODE + "=?", new String[]{c_code});
    }

    //update billdetails Data
    public void updateBillingDetails(String bill_status, String billNo, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_status", bill_status);
        contentValues.put(COLUMN_IS_ONLINE, isonline);

        db.update(TABLE_BILLING_DETAILS, contentValues, COLUMN_BILL_NO + "=?", new String[]{billNo});
    }

    //update future required
    public void updateFutureRequired(String randomKey, String key, String value, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(key, value);
        contentValues.put(COLUMN_IS_ONLINE, isonline);

        db.update(TABLE_FUTURE_REQUIRED, contentValues, COLUMN_RANDOM_KEY + "=?", new String[]{randomKey});
    }

    public void updateBillingDetailsorder(String billNo, String orderstatus, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ORDER_STATUS, orderstatus);
        contentValues.put(COLUMN_IS_ONLINE, isonline);
        Log.e("TAG", "update order bill no==" + contentValues);
        db.update(TABLE_ORDERS, contentValues, COLUMN_BILL_ID + "=?", new String[]{billNo});
    }

    //update product details
    public void productDetails(String bill_status, String billNo, String date, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STATUS, bill_status);
        contentValues.put(COLUMN_IS_ONLINE, isonline);

        db.update(TABLE_PRODUCT_DETAILS, contentValues, COLUMN_BILL_NO + "=? and " + COLUMN_BILL_DATE + "=?", new String[]{billNo, date});
    }

    //update product details
    public void paymenttDetails(String bill_status, String billNo, String customerCode, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STATUS, bill_status);
        contentValues.put(COLUMN_IS_ONLINE, isonline);

        db.update(TABLE_PAYMENT_DETAILS, contentValues, COLUMN_PAYMENT_KEY + "=? and " + COLUMN_CUSTOMER_CODE_SMALL + "=?", new String[]{billNo, customerCode});
    }

    //transaction product details
    public void transactiontDetails(String status, String billNo, String date, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STATUS, status);
        contentValues.put(COLUMN_IS_ONLINE, isonline);

        db.update(TABLE_TRANSACTION_DETAILS, contentValues, COLUMN_DESCRIPTION + "=? and " + COLUMN_DATE + " =?", new String[]{billNo, date});
    }

    //transaction product details
    public void customerAccount(String ledgerbal, String customerCode, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_LEDGER_BALANCE, ledgerbal);
        contentValues.put(COLUMN_IS_ONLINE, isonline);

        db.update(TABLE_CUSTOMER_ACCOUNTS, contentValues, COLUMN_CUSTOMER_CODE_SMALL + " =?", new String[]{customerCode});
    }


    public void insertMaterialMaster(String material_code, String material_name, String uom, String taxdetails, String company_code, String plant_code,
                                     String salesorg_code, String material_category, String net_weight, String image) {
        SQLiteDatabase tx = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("material_code", material_code);
        contentValues.put("material_name", material_name);
        contentValues.put("uom", uom);
        contentValues.put("taxdetails", taxdetails);
        contentValues.put("company_code", company_code);
        contentValues.put("plant_code", plant_code);
        contentValues.put("salesorg_code", salesorg_code);
        contentValues.put("material_category", material_category);
        contentValues.put("net_weight", net_weight);
        contentValues.put(COLUMN_PRODUCT_IMAGE, image);

//        tx.execSQL("INSERT INTO material_master (material_code, material_name, uom, taxdetails, company_code," +
//                " plant_code, salesorg_code, material_category, net_weight ) VALUES ('" + material_code + "'" + ", '" + material_name + "', '"
//                + uom + "' , '" + taxdetails + "', '" + company_code + "', '" + plant_code + "', '" + salesorg_code + "', '" + material_category + "', '" + net_weight + "')");
        tx.insert(TABLE_MATERIAL_MASTER, null, contentValues);
        tx.close();
    }

    public void insertUpdates(String tableName, String datetime, String today) {
        SQLiteDatabase tx = getWritableDatabase();
        tx.execSQL("INSERT INTO updates (table_name, datetime, date) VALUES (" + "'" + tableName + "', '" + datetime + "', '" + today + "')");
        tx.close();
    }

    public void insertSupervisor(String supervisor_code, String supervisor_name, String user_name, String password, String last_modified) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisor_code);
        contentValues.put(COLUMN_SUPERVISOR_NAME, supervisor_name);
        contentValues.put(COLUMN_USER_NAME, user_name);
        contentValues.put(COLUMN_PASSWORD, password);
//        contentValues.put("supervisor_code",last_modified);
        db.insert(TABLE_SUPERVISOR, null, contentValues);
    }

    public void insertSchemeMaster(String scheme_code, String scheme_name, String scheme_type, String material_code1, String material_code2,
                                   String quantity, String free_quantity, String scheme, String scheme_from,
                                   String scheme_till, String last_modified) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SCHEME_CODE, scheme_code);
        contentValues.put(COLUMN_SCHEME_NAME, scheme_name);
        contentValues.put(COLUMN_SCHEME_TYPE, scheme_type);
        contentValues.put("material_code1", material_code1);
        contentValues.put("material_code2", material_code2);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put("free_quantity", free_quantity);
        contentValues.put(COLUMN_SCHEME, scheme);
        contentValues.put(COLUMN_SCHEME_FROM, scheme_from);
        contentValues.put(COLUMN_SCHEME_TILL, scheme_till);
//        contentValues.put("supervisor_code",last_modified);
        db.insert("scheme_master", null, contentValues);
    }

    public void insertPriceMaster(String id, String company_code, String plant_code, String salesorg_code, String material_code,
                                  String price_list, String amount, String tax, String net_price,
                                  String valid_from, String valid_to, String last_modified, String taxValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_PLANT_CODE, plant_code);
        contentValues.put(COLUMN_SALESORG_CODE, salesorg_code);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_PRICE_LIST, price_list);
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put("tax", tax);
        contentValues.put(COLUMN_TAX_AMOUNT, taxValue);
        contentValues.put(COLUMN_NET_PRICE, net_price);
        contentValues.put(COLUMN_VALID_FROM, valid_from);
        contentValues.put(COLUMN_VALID_TO, valid_to);
//        contentValues.put("supervisor_code",last_modified);
        db.insert("price_master", null, contentValues);
    }

    public void insertDiscountMaster(String discount_code, String material_code, String from_quantity, String to_quantity, String discount,
                                     String discount_wot, String discount_from, String discount_till, String discount_type, String last_modified) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("discount_code", discount_code);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_FROM_QUANTITY, from_quantity);
        contentValues.put(COLUMN_TO_QUANTITY, to_quantity);
        contentValues.put(COLUMN_DISCOUNT, discount);
        contentValues.put(COLUMN_DISCOUNT_WOT, discount_wot);
        contentValues.put(COLUMN_DISCOUNT_FROM, discount_from);
        contentValues.put(COLUMN_DISCOUNT_TILL, discount_till);
        contentValues.put(COLUMN_DISCOUNT_TYPE, discount_type);
//        contentValues.put("supervisor_code",last_modified);
        db.insert("discount_master", null, contentValues);
    }

    public void insertBillHeader(String id, String header1, String header2, String header3, String last_modified) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("header_1", header1);
        contentValues.put("header_2", header2);
        contentValues.put("header_3", header3);
//        contentValues.put("supervisor_code",last_modified);
        db.insert("bill_header", null, contentValues);
    }

    public void insertAppDetails(String date, String address, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("address", address);
        contentValues.put("name", name);
//        contentValues.put("supervisor_code",last_modified);
        db.insert(TABLE_APP_DETAILS, null, contentValues);
    }

    public void insertCustomerAccount(String customer_code, String ledger_balance, String loyalty_points, String lastpay_date, String lastpay_amount,
                                      String lastpay_mode, String lastchq_num, String lastchq_date, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put(COLUMN_LEDGER_BALANCE, ledger_balance);
        contentValues.put(COLUMN_LOYALTY_POINTS, loyalty_points);
        contentValues.put(COLUMN_LASTPAY_DATE, lastpay_date);
        contentValues.put(COLUMN_LASTPAY_AMOUNT, lastpay_amount);
        contentValues.put(COLUMN_LASTPAY_MODE, lastpay_mode);
        contentValues.put(COLUMN_LASTCHQ_NO, lastchq_num);
        contentValues.put(COLUMN_LASTCHQ_DATE, lastchq_date);
        contentValues.put(COLUMN_DATE, date);
//        contentValues.put("supervisor_code",last_modified);
        db.insert(TABLE_CUSTOMER_ACCOUNTS, null, contentValues);
    }

    public int insertCustomerPayment(String date, String company_code, String salesarea_code, String customer_code, String mode, String amount,
                                     String cheque_number, String cheque_date, String bank_name, String randomkey, String entered_by,
                                     String created_on, String status, String bill_no, String payment_key, String latitude,
                                     String longtude, String tripID, String isOnline, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_PAYMENT_DETAILS, null, COLUMN_PAYMENT_KEY + " =?", new String[]{payment_key}, null, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put("mode", mode);
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put("cheque_number", cheque_number);
        contentValues.put("cheque_date", cheque_date);
        contentValues.put("bank_name", bank_name);
        contentValues.put("randomkey", randomkey);
        contentValues.put("entered_by", entered_by);
        contentValues.put("created_on", created_on);
        contentValues.put("status", status);
        contentValues.put("bill_no", bill_no);
        contentValues.put(COLUMN_PAYMENT_KEY, payment_key);
        contentValues.put(COLUMN_TRIPID, tripID);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longtude);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);
        if (cursor.getCount() == 0) {
            db.insert(TABLE_PAYMENT_DETAILS, null, contentValues);
            cursor.close();
            return 0;
        } else {
            db.update(TABLE_PAYMENT_DETAILS, contentValues, "bill_no = ? ", new String[]{bill_no});
            cursor.close();
            return 1;
        }
    }

    public void insertReplacement(String company_code, String salesarea_code, String customer_code, String material_code, String material_name,
                                  String date, String return_quantity, String replace_quantity, String balance_quantity, String replace_key,
                                  String latitude, String longtude, String randomkey) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put("return_quantity", return_quantity);
        contentValues.put(COLUMN_REPLACE_QUANTITY, replace_quantity);
        contentValues.put("balance_quantity", balance_quantity);
        contentValues.put("replace_key", replace_key);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longtude);
        contentValues.put("randomkey", randomkey);
//        contentValues.put("supervisor_code",last_modified);
        db.insert(TABLE_REPLACEMENT_ENTRY, null, contentValues);
    }

    public void insertUpdateReplacement(String company_code, String salesarea_code, String customer_code, String material_code, String material_name,
                                        String date, String return_quantity, String replace_quantity, String balance_quantity, String replace_key,
                                        String latitude, String longtude, String randomkey, String isOnline) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_REPLACEMENT_ENTRY + " where customer_code='" + customer_code + "' and material_code='" + material_code + "'";
        Log.e(TAG, "getReplacement: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getReplacement: get count : " + c.getCount());

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put("return_quantity", return_quantity);
        contentValues.put(COLUMN_REPLACE_QUANTITY, replace_quantity);
        contentValues.put("balance_quantity", balance_quantity);
        contentValues.put("replace_key", replace_key);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longtude);
        contentValues.put("randomkey", randomkey);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);
//        contentValues.put("supervisor_code",last_modified);
        if (c.getCount() > 0) {
            db.update(TABLE_REPLACEMENT_ENTRY, contentValues, COLUMN_CUSTOMER_CODE_SMALL + "=? and " + COLUMN_MATERIAL_CODE + "=?", new String[]{customer_code, material_code});
        } else {
            db.insert(TABLE_REPLACEMENT_ENTRY, null, contentValues);
        }
    }

    public void updateReplacement(String company_code, String salesarea_code, String customer_code, String material_code, String material_name,
                                  String date, String return_quantity, String replace_quantity, String balance_quantity, String replace_key,
                                  String latitude, String longitude, String randomkey) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.e(TAG, "updateReplacement: " + latitude + "," + longitude);
//        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
//        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
//        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
//        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
//        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
        contentValues.put("date", date);
//        contentValues.put("return_quantity", return_quantity);
        contentValues.put(COLUMN_REPLACE_QUANTITY, replace_quantity);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);
        contentValues.put("balance_quantity", balance_quantity);
        contentValues.put("replace_key", replace_key);
//        contentValues.put("randomkey", randomkey);
//        contentValues.put("supervisor_code",last_modified);
        db.update(TABLE_REPLACEMENT_ENTRY, contentValues, COLUMN_CUSTOMER_CODE_SMALL + "=? and " + COLUMN_MATERIAL_CODE + "=?", new String[]{customer_code, material_code});
    }

    public void updateReplacement(String customer_code, String material_code, String date, String return_quantity, String balance_quantity,
                                  String replace_key) {

        Log.e(TAG, "updateReplacement: " + return_quantity);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
//        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
//        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
//        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
//        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
        contentValues.put("date", date);
        contentValues.put("return_quantity", return_quantity);
//        contentValues.put(COLUMN_REPLACE_QUANTITY, replace_quantity);
        contentValues.put("balance_quantity", balance_quantity);
        contentValues.put("replace_key", replace_key);
//        contentValues.put("randomkey", randomkey);
//        contentValues.put("supervisor_code",last_modified);
        db.update(TABLE_REPLACEMENT_ENTRY, contentValues, COLUMN_CUSTOMER_CODE_SMALL + "=? and " + COLUMN_MATERIAL_CODE + "=?", new String[]{customer_code, material_code});
    }

    public void updateInventory(String material_code, String inventory_date, String available_quantity, String supervisor_code, String inv_key,
                                String replace_quantity, String soldQty, String module) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
//        contentValues.put(COLUMN_PLANT_CODE, plant_code);
//        contentValues.put(COLUMN_SALESORG_CODE, salesorg_code);
//        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
//        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
//        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
//        contentValues.put(COLUMN_QUANTITY, quantity);
//        contentValues.put("inventory_date", inventory_date);
        contentValues.put(COLUMN_AVAILABLE_QUANTITY, available_quantity);
//        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisor_code);
//        contentValues.put("machine_id", machine_id);
//        contentValues.put(COLUMN_VEHICLE, vehicle);
        contentValues.put("inv_key", inv_key);
//        contentValues.put(COLUMN_SOLD_QUANTITY, sold_quantity);
        contentValues.put(COLUMN_REPLACE_QUANTITY, replace_quantity);
//        contentValues.put(COLUMN_DAY_COUNT, daycount);
//        contentValues.put(COLUMN_PRODUCT_IMAGE, productImage);
//        contentValues.put("supervisor_code",last_modified);
        if (!module.equals(Constants.replacement)) {
            contentValues.put(COLUMN_SOLD_QUANTITY, soldQty);
        }
        db.update(TABLE_INVENTORY, contentValues, COLUMN_SUPERVISOR_CODE + "=? and " + COLUMN_INVENTORY_DATE + "=? and " + COLUMN_MATERIAL_CODE + "=?", new String[]{supervisor_code, inventory_date, material_code});
    }

    public void updateInventory(String material_code, String inventory_date, String available_quantity, String supervisor_code, String soldQty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AVAILABLE_QUANTITY, available_quantity);
        contentValues.put(COLUMN_SOLD_QUANTITY, soldQty);
        db.update(TABLE_INVENTORY, contentValues, COLUMN_SUPERVISOR_CODE + "=? and " + COLUMN_INVENTORY_DATE + "=? and " + COLUMN_MATERIAL_CODE + "=?", new String[]{supervisor_code, inventory_date, material_code});
    }


    public void insertReturnEntry(String company_code, String salesarea_code, String customer_code, String returns, String return_date,
                                  String material_code, String material_name, String return_quantity, String replacement_quantity,
                                  String balance_quantity, String replace_date, String status, String entered_by, String created_on,
                                  String return_key, String randomKey, String latitude, String longtude, String isOnline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put("returns", returns);
        contentValues.put(COLUMN_RETURN_DATE, return_date);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
        contentValues.put("return_quantity", return_quantity);
        contentValues.put("replacement_quantity", replacement_quantity);
        contentValues.put("balance_quantity", balance_quantity);
        contentValues.put("replace_date", replace_date);
        contentValues.put("status", status);
        contentValues.put("entered_by", entered_by);
        contentValues.put("created_on", created_on);
        contentValues.put("return_key", return_key);
        contentValues.put("randomkey", randomKey);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longtude);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);

//        contentValues.put("supervisor_code",last_modified);
        db.insert(TABLE_RETURN_ENTRY, null, contentValues);
    }


    public void insertInventory(String company_code, String plant_code, String salesorg_code, String salesarea_code, String material_code,
                                String material_name, String quantity, String inventory_date, String available_quantity, String supervisor_code,
                                String machine_id, String vehicle, String randomkey, String inv_key, String sold_quantity, String replace_quantity,
                                String daycount, byte[] productImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_PLANT_CODE, plant_code);
        contentValues.put(COLUMN_SALESORG_CODE, salesorg_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_MATERIAL_NAME, material_name);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put(COLUMN_INVENTORY_DATE, inventory_date);
        contentValues.put(COLUMN_AVAILABLE_QUANTITY, available_quantity);
        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisor_code);
        contentValues.put("machine_id", machine_id);
        contentValues.put(COLUMN_VEHICLE, vehicle);
        contentValues.put("randomkey", randomkey);
        contentValues.put("inv_key", inv_key);
        contentValues.put(COLUMN_SOLD_QUANTITY, sold_quantity);
        contentValues.put(COLUMN_REPLACE_QUANTITY, replace_quantity);
        contentValues.put(COLUMN_DAY_COUNT, daycount);
        contentValues.put(COLUMN_PRODUCT_IMAGE, productImage);
//        contentValues.put("supervisor_code",last_modified);
        db.insert(TABLE_INVENTORY, null, contentValues);
    }

    //        var mat = "SELECT * from material_master";

    //Insert User Data
    public int insertUser(String id, String company_code, String plant_code, String salesorg_code, String salesarea_code, String supervisor_code,
                          String employee_id, String supervisor_name, String street, String address, String postal_code, String ip_address,
                          String phone_num, String special_discount, String user_name, String password, String status, String inventory,
                          String city, String country, String region, String entered_by, String created_on, String last_modified, String modified_by,
                          String modified_on, byte[] userImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_COMPANY_CODE, company_code);
        contentValues.put(COLUMN_PLANT_CODE, plant_code);
        contentValues.put(COLUMN_SALESORG_CODE, salesorg_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisor_code);
        contentValues.put(COLUMN_EMPLOYEE_ID, employee_id);
        contentValues.put(COLUMN_SUPERVISOR_NAME, supervisor_name);
        contentValues.put(COLUMN_STREET, street);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_POSTAL_CODE, postal_code);
        contentValues.put(COLUMN_IP_ADDRESS, ip_address);

        contentValues.put(COLUMN_PHONE_NUM, phone_num);
        contentValues.put(COLUMN_SPECIAL_DISCOUNT, special_discount);
        contentValues.put(COLUMN_USER_NAME, user_name);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_STATUS, status);
        contentValues.put(COLUMN_INVENTORY, inventory);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_COUNTRY, country);

        contentValues.put(COLUMN_REGION, region);
        contentValues.put(COLUMN_ENTERED_BY, entered_by);
        contentValues.put(COLUMN_CREATED_ON, created_on);
        contentValues.put(COLUMN_LAST_MODIFIED, last_modified);
        contentValues.put(COLUMN_MODIFIED_BY, modified_by);
        contentValues.put(COLUMN_MODIFIED_ON, modified_on);
        contentValues.put(COLUMN_USER_IMAGE, userImage);
        db.insert(TABLE_USER, null, contentValues);
        db.close();
        return 0;
    }

    //Insert Invoice Data
    public int insertInvoice(String id, String erp_id, String tripID, String bill_no, String bill_date, String customer_code, String customer_name,
                             String company_code, String plant_code, String salesorg_code, String salesarea_code, String supervisor_code, String item_count,
                             String value, String sub_total, String discount, String tax, String grand_total, String round_up,
                             String net_amount, String received, String balance, String bill_status, String comments, String manual_billing,
                             String randomkey, String bill_key, String manual_bill_no, String latitude, String longitude, String entered_by, String created_on,
                             String modified_by, String modified_on, String isOnline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_INVOICE_ID, id);
        contentValues.put(COLUMN_ERP_ID, erp_id);
        contentValues.put(COLUMN_TRIPID, tripID);
        contentValues.put(COLUMN_BILL_NO, bill_no);
        contentValues.put(COLUMN_BILL_DATE, bill_date);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customer_code);
        contentValues.put(COLUMN_CUSTOMER_NAME_SMALL, customer_name);
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, company_code);
        contentValues.put(COLUMN_PLANT_CODE, plant_code);
        contentValues.put(COLUMN_SALESORG_CODE, salesorg_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisor_code);
        contentValues.put(COLUMN_ITEM_COUNT, item_count);

        contentValues.put(COLUMN_VALUE, value);
        contentValues.put(COLUMN_SUB_TOTAL_SMALL, sub_total);
        contentValues.put(COLUMN_DISCOUNT, discount);
        contentValues.put(COLUMN_INVOICETAX, tax);
        contentValues.put(COLUMN_GRAND_TOTAL, grand_total);
        contentValues.put(COLUMN_ROUND_UP, round_up);
        contentValues.put(COLUMN_NET_AMOUNT, net_amount);
        contentValues.put(COLUMN_RECEIVED, received);


        contentValues.put("balance", balance);
        contentValues.put("bill_status", bill_status);
        contentValues.put("comments", comments);
        contentValues.put(COLUMN_MANUAL_BILLING, manual_billing);
        contentValues.put("randomkey", randomkey);
        contentValues.put("bill_key", bill_key);
        contentValues.put(COLUMN_MANUAL_BILL_NO, manual_bill_no);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);
        contentValues.put(COLUMN_ENTERED_BY, entered_by);
        contentValues.put(COLUMN_CREATED_ON, created_on);
        contentValues.put(COLUMN_MODIFIED_BY, modified_by);
        contentValues.put(COLUMN_MODIFIED_ON, modified_on);
//        contentValues.put(COLUMN_BILLID, bill_id);
//        contentValues.put(COLUMN_SQST_TAX, sgst_tax);
//        contentValues.put(COLUMN_CGST_TAX, cgst_tax);
//        contentValues.put(COLUMN_IGST_TAX, igst_tax);
//        contentValues.put(COLUMN_TOTAL_TAX, total_tax);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);

        db.insert(TABLE_INVOICE, null, contentValues);
        db.close();
        return 0;
    }

    //Update Invoice table
    public void updateInvoice(String received, String balance, String bill_status, String isOnline, String
            customer_code, String billId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RECEIVED, received);
        contentValues.put("balance", balance);
        contentValues.put("bill_status", bill_status);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);
        Log.e(TAG, " UPDATE AMOUNT : " + balance + " customer code : " + customer_code);
        db.update(TABLE_INVOICE, contentValues, COLUMN_CUSTOMER_CODE_SMALL + " =? " +
                "and " + COLUMN_BILL_NO + " =? ", new String[]{customer_code, billId});
    }


    //Update Base url in UserTable
    public int updateUser(String userID, String baseURL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BASE_URL, baseURL);
        db.update(TABLE_USER, contentValues, COLUMN_USER_ID + " = ? ", new String[]{userID});

        return 1;
    }

    //Insert and  Update Customer Data
    public int insertUpdateCustomer(String customerID, String customerName, String customerCode, String customerEmail, String customerPhoneNo,
                                    String customerGSTIN, String customerStreet, String customerCity, String customerRegion, String customerPostalCode,
                                    String currentDate, String stateCode, byte[] customerImage, String leadstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMER, null, COLUMN_CUSTOMER_ID + "=?", new String[]{customerID}, null, null, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_ID, customerID);
        contentValues.put(COLUMN_CUSTOMER_NAME, customerName);
        contentValues.put(COLUMN_CUSTOMER_CODE, customerCode);
        contentValues.put(COLUMN_EMAIL, customerEmail);
        contentValues.put(COLUMN_PHONE_NO, customerPhoneNo);
        contentValues.put(COLUMN_GSTIN, customerGSTIN);
        contentValues.put(COLUMN_STREET, customerStreet);
        contentValues.put(COLUMN_CITY, customerCity);
        contentValues.put(COLUMN_REGION, customerRegion);
        contentValues.put(COLUMN_PINCODE, customerPostalCode);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_STATE_CODE, stateCode);
        contentValues.put(COLUMN_CUSTOMER_IMAGE, customerImage);
        contentValues.put(COLUMN_LEAD_STATUS, leadstatus);
        if (cursor.getCount() == 0) {
            /* If no data found insert the customer Details*/
            db.insert(TABLE_CUSTOMER, null, contentValues);
            cursor.close();
            db.close();
            return 0;
        } else {
            /*  If customer Details found update the database*/
            db.update(TABLE_CUSTOMER, contentValues, COLUMN_CUSTOMER_ID + " = ? ", new String[]{customerID});
            cursor.close();
            db.close();
            return 1;
        }
    }

    //Insert and  Update Customer Data
    public int customerInsertUpdate(String erp_id, String companyCode, String plant_code, String salesOrgCode, String salesarea_code, String customerID,
                                    String customerCode, String scheme_discount, String customerName, String priceCode, String material_code,
                                    String customerStreet, String customerCity, String customerPostalCode,
                                    String loyalty, String lastModified, String customerGSTIN,
                                    String currentDate, byte[] customerImage, String stateCode, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMER, null, COLUMN_CUSTOMER_ID + "=?", new String[]{customerID}, null, null, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ERP_ID, erp_id);
        contentValues.put(COLUMN_COMPANY_CODE, companyCode);
        contentValues.put(COLUMN_PLANT_CODE, plant_code);
        contentValues.put(COLUMN_SALESORG_CODE, salesOrgCode);
        contentValues.put(COLUMN_CUSTOMER_ID, customerID);
        contentValues.put(COLUMN_CUSTOMER_CODE, customerCode);
        contentValues.put(COLUMN_CUSTOMER_NAME, customerName);
        contentValues.put(COLUMN_SCHEME_DISCOUNT, scheme_discount);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_PRICE_CODE, priceCode);
        contentValues.put(COLUMN_MATERIAL_CODE, material_code);
        contentValues.put(COLUMN_STREET, customerStreet);
        contentValues.put(COLUMN_CITY, customerCity);
        contentValues.put(COLUMN_PINCODE, customerPostalCode);
        contentValues.put(COLUMN_LOYALTY, loyalty);
        contentValues.put(COLUMN_LAST_MODIFIED, lastModified);
        contentValues.put(COLUMN_GSTIN, customerGSTIN);
        contentValues.put(COLUMN_CUSTOMER_IMAGE, customerImage);
        contentValues.put(COLUMN_STATE_CODE, stateCode);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);
        if (cursor.getCount() == 0) {
            /* If no data found insert the customer Details*/
            db.insert(TABLE_CUSTOMER, null, contentValues);
            cursor.close();
            db.close();
            return 0;
        } else {
            /*  If customer Details found update the database*/
            db.update(TABLE_CUSTOMER, contentValues, COLUMN_CUSTOMER_ID + " = ? ", new String[]{customerID});
            cursor.close();
            db.close();
            return 1;
        }
    }

    public void updateCustomer(String customerID, String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value);
        db.update(TABLE_CUSTOMER, contentValues, COLUMN_CUSTOMER_ID + " = ? ", new String[]{customerID});
        db.close();
    }

    //Insert and  Update Customer Data
    public void insertUpdateSalesMan(String id, String name, String address, String mobileNo, String date, byte[] salesManImage, String street, String city,
                                     String state, String region, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_PHONE_NO, mobileNo);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_STREET, street);
        contentValues.put(COLUMN_STATE, state);
        contentValues.put(COLUMN_REGION, region);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_SALES_MAN_IMAGE, salesManImage);
        /* If no data found insert the customer Details*/
        db.insert(TABLE_SALES_MAN, null, contentValues);
        db.close();
    }

    //update sales man
    public void updateUpdateSalesMan(String id, String name, String address, String mobileNo, String date, byte[] salesManImage, String street, String city,
                                     String state, String region, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_PHONE_NO, mobileNo);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_STREET, street);
        contentValues.put(COLUMN_STATE, state);
        contentValues.put(COLUMN_REGION, region);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_SALES_MAN_IMAGE, salesManImage);
        /* If no data found insert the customer Details*/
        db.update(TABLE_SALES_MAN, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    //Insert and  Update Customer Data
    public void insertUpdateLeader(String id, String name, String address, String mobileNo, String date, byte[] salesManImage, String street, String city,
                                   String state, String region, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_PHONE_NO, mobileNo);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_STREET, street);
        contentValues.put(COLUMN_STATE, state);
        contentValues.put(COLUMN_REGION, region);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_SALES_MAN_IMAGE, salesManImage);
        /* If no data found insert the customer Details*/
        db.insert(TABLE_LEADER, null, contentValues);
    }

    //update sales man
    public void updateUpdateLeader(String id, String name, String address, String mobileNo, String date, byte[] salesManImage, String street, String city,
                                   String state, String region, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_PHONE_NO, mobileNo);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_STREET, street);
        contentValues.put(COLUMN_STATE, state);
        contentValues.put(COLUMN_REGION, region);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_SALES_MAN_IMAGE, salesManImage);
        /* If no data found insert the customer Details*/
        db.update(TABLE_LEADER, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }


    //sales Man list
    public List<SalesManListDataSource> getSalesManList() {
        List<SalesManListDataSource> allItems = new ArrayList<SalesManListDataSource>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * from " + TABLE_SALES_MAN;
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {

                int id = c.getInt(c.getColumnIndex(COLUMN_ID));
                String name = c.getString(c.getColumnIndex(COLUMN_NAME));
                String address = c.getString(c.getColumnIndex(COLUMN_ADDRESS));
                String mobileNo = c.getString(c.getColumnIndex(COLUMN_PHONE_NO));
                String state = c.getString(c.getColumnIndex(COLUMN_STATE));
                String city = c.getString(c.getColumnIndex(COLUMN_CITY));
                String region = c.getString(c.getColumnIndex(COLUMN_REGION));
                String street = c.getString(c.getColumnIndex(COLUMN_STREET));
                String date = c.getString(c.getColumnIndex(COLUMN_DATE));
                String email = c.getString(c.getColumnIndex(COLUMN_EMAIL));
                String password = c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                byte[] salesManImg = c.getBlob(c.getColumnIndex(COLUMN_SALES_MAN_IMAGE));
                allItems.add(new SalesManListDataSource(id, name, mobileNo, address, region, city, street, state, email, password, salesManImg, date));
            } while (c.moveToNext());
        }
        return allItems;
    }


    //sales Man list
    public List<LeaderListDataSource> getLeaderList() {
        List<LeaderListDataSource> allItems = new ArrayList<LeaderListDataSource>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * from " + TABLE_LEADER;
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {

                int id = c.getInt(c.getColumnIndex(COLUMN_ID));
                String name = c.getString(c.getColumnIndex(COLUMN_NAME));
                String address = c.getString(c.getColumnIndex(COLUMN_ADDRESS));
                String mobileNo = c.getString(c.getColumnIndex(COLUMN_PHONE_NO));
                String state = c.getString(c.getColumnIndex(COLUMN_STATE));
                String city = c.getString(c.getColumnIndex(COLUMN_CITY));
                String region = c.getString(c.getColumnIndex(COLUMN_REGION));
                String street = c.getString(c.getColumnIndex(COLUMN_STREET));
                String date = c.getString(c.getColumnIndex(COLUMN_DATE));
                String email = c.getString(c.getColumnIndex(COLUMN_EMAIL));
                String password = c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                byte[] salesManImg = c.getBlob(c.getColumnIndex(COLUMN_SALES_MAN_IMAGE));
                allItems.add(new LeaderListDataSource(id, name, mobileNo, address, region, city, street, state, email, password, salesManImg, date));
            } while (c.moveToNext());
        }
        return allItems;
    }

    // Insert and Update the Products
    public int insertUpdateProduct(String productID, String currentDate, String productName, String productCode,
                                   String productQty, String productUOM, String productPrice, String taxPrice,
                                   String taxValue, String productTaxPercentage, String taxSelectedMode,
                                   String selectedGST, byte[] productImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCT, null, COLUMN_PROD_ID + "=?", new String[]{productID}, null, null, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROD_ID, productID);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_NAME, productName);
        contentValues.put(COLUMN_CODE, productCode);
        contentValues.put(COLUMN_PRODUCT_QTY, productQty);
        contentValues.put(COLUMN_PRODUCT_UOM, productUOM);
        contentValues.put(COLUMN_PRODUCT_PRICE, productPrice);
        contentValues.put(COLUMN_PRODUCT_TAX_PRICE, taxPrice);
        contentValues.put(COLUMN_PRODUCT_TAX_VALUE, taxValue);
        contentValues.put(COLUMN_TAX_PERCENTAGE, productTaxPercentage);
        contentValues.put(COLUMN_PRODUCT_SELECTED_MODE, taxSelectedMode);
        contentValues.put(COLUMN_PRODUCT_SELECTED_GST, selectedGST);
        contentValues.put(COLUMN_PRODUCT_IMAGE, productImage);

        if (cursor.getCount() == 0) {
            /* If no data found insert the customer Details*/
            db.insert(TABLE_PRODUCT, null, contentValues);
            cursor.close();
            db.close();
            return 0;
        } else {
            /*  If customer Details found update the database*/
            db.update(TABLE_PRODUCT, contentValues, COLUMN_PROD_ID + " = ? ", new String[]{productID});
            cursor.close();
            db.close();
            return 1;
        }
    }

    //Insert Tax Details
    public int updateTax(String taxName, int id, String taxPercentage, String merge, String currentDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_TAX_NAME, taxName);
        contentValues.put(COLUMN_TAX_PERCENTAGE, taxPercentage);
        contentValues.put(COLUMN_TAX, merge);
        contentValues.put(COLUMN_DATE, currentDate);
        Log.e(TAG, "updateUOM: id : " + id);
        db.update(TABLE_TAX, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return 0;
    }

    //Insert UOM Details
    public int insertUOM(String uomName, String uomShort, String currentDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_UOM_NAME, uomName);
        contentValues.put(COLUMN_UOM_SHORT, uomShort);
        contentValues.put(COLUMN_DATE, currentDate);
        db.insert(TABLE_UOM, null, contentValues);
        db.close();
        return 0;

    }

    //Insert UOM Details
    public int updateUOM(int id, String uomName, String uomShort, String currentDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_UOM_NAME, uomName);
        contentValues.put(COLUMN_UOM_SHORT, uomShort);
        contentValues.put(COLUMN_DATE, currentDate);
        Log.e(TAG, "updateUOM: id : " + id);
        db.update(TABLE_UOM, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return 0;

    }

    //Insert Menu Table
    public int insertMenu(String id, String screen_type, String screen_name, String screen_value, String image_id, String language_code,
                          String screen_status, String created_by, String created_date, String company_code, String salesman_code,
                          String salesorg_code, String salesarea_code, String plant_code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SCREEN_TYPE, screen_type);
        contentValues.put(COLUMN_SCREEN_NAME, screen_name);
        contentValues.put(COLUMN_SCREEN_VALUE, screen_value);
        contentValues.put(COLUMN_IMAGE_ID, image_id);
        contentValues.put(COLUMN_LANGUAGE_CODE, language_code);
        contentValues.put(COLUMN_SCREEN_STATUS, screen_status);
        contentValues.put(COLUMN_CREATED_BY, created_by);
        contentValues.put(COLUMN_CREATED_DATE, created_date);
        contentValues.put(COLUMN_COMPANY_CODE, company_code);
        contentValues.put(COLUMN_SALESMAN_CODE, salesman_code);
        contentValues.put(COLUMN_SALESORG_CODE, salesorg_code);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesarea_code);
        contentValues.put(COLUMN_PLANT_CODE, plant_code);
        Log.e(TAG, "updateUOM: id : " + contentValues);
        db.insert(TABLE_MENU, null, contentValues);
        db.close();
        return 0;
    }

    //Insert Tax Details
    public int insertTax(String taxName, String taxPercentage, String merge, String currentDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TAX_NAME, taxName);
        contentValues.put(COLUMN_TAX_PERCENTAGE, taxPercentage);
        contentValues.put(COLUMN_TAX, merge);
        contentValues.put(COLUMN_DATE, currentDate);
        db.insert(TABLE_TAX, null, contentValues);
        db.close();
        return 0;
    }

    //Insert and Update Payment Details
    public int insertUpdatePayment(String orderID, String paymentID, String billAmount, String selectedPaymentMode, String currentDate, String cashAmount,
                                   String chequeNo, String chequeDate, String bankName, String bankCode) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_PAYMENT, null, COLUMN_PAYMENT_ID + "=?", new String[]{paymentID}, null, null, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BILL_ID, orderID);
        contentValues.put(COLUMN_PAYMENT_ID, paymentID);
        contentValues.put(COLUMN_LEDGER_BALANCE, billAmount);
        contentValues.put(COLUMN_PAYMENT_MODE, selectedPaymentMode);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_CASH, cashAmount);
        contentValues.put(COLUMN_CHEQUE_NO, chequeNo);
        contentValues.put(COLUMN_CHEQUE_DATE, chequeDate);
        contentValues.put(COLUMN_BANK, bankName);
        contentValues.put(COLUMN_BANK_CODE, bankCode);

        if (cursor.getCount() == 0) {
            /* If no data found insert the customer Details*/
            db.insert(TABLE_PAYMENT, null, contentValues);
            cursor.close();
            db.close();
            return 0;
        } else {
            /*  If customer Details found update the database*/
            db.update(TABLE_PAYMENT, contentValues, COLUMN_PAYMENT_ID + " = ? ", new String[]{paymentID});
            cursor.close();
            db.close();
            return 1;
        }
    }

    //Insert Sales Items
    public int insertFutureRequire(String orderNo, String orderDate, String customerCode, String customerName, String companyCode,
                                   String plantCode, String salesOrgCode, String salesAreaCode, String supervisorCode, String materialCode,
                                   String materialName, String quantity, String lat, String logn, String comments, String enteredBy,
                                   String createdOn, String modifiedBy, String modifiedOn, String latitude, String longtude,
                                   String isOnline, String orderStatus, String random, String tripID, String productType) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_FUTURE_REQUIRED, null, COLUMN_ORDER_NO + "=?", new String[]{orderNo}, null, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ORDER_NO, orderNo);
        contentValues.put(COLUMN_ORDER_DATE, orderDate);
        contentValues.put(COLUMN_RANDOM_KEY, random);
        contentValues.put(COLUMN_CUSTOMER_CODE_SMALL, customerCode);
        contentValues.put("customer_name", customerName);
        contentValues.put(COLUMN_COMPANY_CODE_SMALL, companyCode);
        contentValues.put(COLUMN_PLANT_CODE, plantCode);
        contentValues.put(COLUMN_SALESORG_CODE, salesOrgCode);
        contentValues.put(COLUMN_SALES_AREA_CODE, salesAreaCode);
        contentValues.put(COLUMN_SUPERVISOR_CODE, supervisorCode);
        contentValues.put(COLUMN_MATERIAL_CODE, materialCode);
        contentValues.put(COLUMN_MATERIAL_NAME, materialName);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longtude);
        contentValues.put("comments", comments);
        contentValues.put("entered_by", enteredBy);
        contentValues.put("created_on", createdOn);
        contentValues.put("modified_by", modifiedBy);
        contentValues.put("modified_on", modifiedOn);
        contentValues.put(COLUMN_TRIPID, tripID);
        contentValues.put(COLUMN_ORDER_STATUS, orderStatus);
        contentValues.put(COLUMN_PRODUCT_TYPE, productType);
        contentValues.put(COLUMN_IS_ONLINE, isOnline);
//if (cursor.getCount()>0){
//    db.update(TABLE_FUTURE_REQUIRED, contentValues,COLUMN_ORDER_NO + " = ? ", new String[]{orderNo});
//}else {
        db.insert(TABLE_FUTURE_REQUIRED, null, contentValues);
//}
        db.close();
        return 0;
    }


    //Update the Payment Details  in Sales Table
    public int updateSalesPayment(String billNo, String paidMode, String paidAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BILL_ID, billNo);
        contentValues.put(COLUMN_PAID_AMOUNT, paidAmount);
        contentValues.put(COLUMN_PAID_MODE, paidMode);
        /*  If Sales Details found update the Payment Details*/
        db.update(TABLE_SALES, contentValues, COLUMN_BILL_ID + " = ? ", new String[]{billNo});

        db.close();
        return 1;
    }


    //Insert Orders Table
    public int insertOrders(String orderID, String currentDate, String customerID, String customerName, String userID,
                            String userName, String billID, String billItems, String salesManStateCode,
                            String customerStateCode, String latitude, String longitude, byte[] customerImage, String ordrtstatus, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ORDER_ID, orderID);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_CUSTOMER_ID, customerID);
        contentValues.put(COLUMN_CUSTOMER_NAME, customerName);
        contentValues.put(COLUMN_USER_ID, userID);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);
        contentValues.put(COLUMN_USER_NAME, userName);
        contentValues.put(COLUMN_BILL_ID, billID);
        contentValues.put(COLUMN_BILL_TOTAL_ITEMS, billItems);
        contentValues.put(COLUMN_SALES_MAN_STATE_CODE, salesManStateCode);
        contentValues.put(COLUMN_CUSTOMER_STATE_CODE, customerStateCode);
        contentValues.put(COLUMN_CUSTOMER_IMAGE, customerImage);
        contentValues.put(COLUMN_ORDER_STATUS, ordrtstatus);
        contentValues.put(COLUMN_IS_ONLINE, isonline);
        db.insert(TABLE_ORDERS, null, contentValues);
        db.close();
        return 0;
    }


    //Insert Sales Table
    public int insertSales(String salesID, String currentDate, String customerID, String customerName, String userID,
                           String userName, String billID, String billItems, String subTotal, String taxTotal,
                           String taxSGST, String taxCGST, String taxIGST, String netAmount, String paidAmount,
                           String paidMode, String salesManStateCode, String customerStateCode, String taxAmount,
                           byte[] customerImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SALES_ID, salesID);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_CUSTOMER_ID, customerID);
        contentValues.put(COLUMN_CUSTOMER_NAME, customerName);
        contentValues.put(COLUMN_USER_ID, userID);
        contentValues.put(COLUMN_USER_NAME, userName);
        contentValues.put(COLUMN_BILL_ID, billID);
        contentValues.put(COLUMN_BILL_TOTAL_ITEMS, billItems);
        contentValues.put(COLUMN_SUB_TOTAL, subTotal);
        contentValues.put(COLUMN_TAX_TOTAL, taxTotal);
        contentValues.put(COLUMN_TAX_SGST, taxSGST);
        contentValues.put(COLUMN_TAX_CGST, taxCGST);
        contentValues.put(COLUMN_TAX_IGST, taxIGST);
        contentValues.put(COLUMN_TAX_NET_AMOUNT, netAmount);
        contentValues.put(COLUMN_PAID_AMOUNT, paidAmount);
        contentValues.put(COLUMN_PAID_MODE, paidMode);
        contentValues.put(COLUMN_SALES_MAN_STATE_CODE, salesManStateCode);
        contentValues.put(COLUMN_CUSTOMER_STATE_CODE, customerStateCode);
        contentValues.put(COLUMN_TAX_AMOUNT, taxAmount);
        contentValues.put(COLUMN_CUSTOMER_IMAGE, customerImage);
        db.insert(TABLE_SALES, null, contentValues);
        db.close();
        return 0;
    }

    public int insertUpdateCompany(String companyID, String companyName, String companyPhoneNo, String companyEmail,
                                   String companyGST, String currentDate, String companyStreet, String companyCity,
                                   String companyRegion, String companyPostCode, String stateCode, String orderBillNo,
                                   String saleBillNo, byte[] companyImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_COMPANY, null, COLUMN_COMPANY_ID + "=?", new String[]{companyID}, null, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_ID, companyID);
        contentValues.put(COLUMN_NAME, companyName);
        contentValues.put(COLUMN_PHONE_NO, companyPhoneNo);
        contentValues.put(COLUMN_EMAIL, companyEmail);
        contentValues.put(COLUMN_GSTIN, companyGST);
        contentValues.put(COLUMN_DATE, currentDate);
        contentValues.put(COLUMN_STREET, companyStreet);
        contentValues.put(COLUMN_CITY, companyCity);
        contentValues.put(COLUMN_REGION, companyRegion);
        contentValues.put(COLUMN_PINCODE, companyPostCode);
        contentValues.put(COLUMN_STATE_CODE, stateCode);
        contentValues.put(COLUMN_ORDER_BILL_NO, orderBillNo);
        contentValues.put(COLUMN_SALES_BILL_NO, saleBillNo);
        contentValues.put(COLUMN_COMPANY_IMAGE, companyImage);

        if (cursor.getCount() == 0) {
            /* If no data found insert the company Details*/
            db.insert(TABLE_COMPANY, null, contentValues);
            cursor.close();
            db.close();
            return 0;
        } else {
            /*  If company Details found update the database*/
            db.update(TABLE_COMPANY, contentValues, COLUMN_COMPANY_ID + " = ? ", new String[]{companyID});
            cursor.close();
            db.close();
            return 1;

        }
    }

    public int insertUpdateCompanyBill(String companyID, String orderBillNo,
                                       String saleBillNo, String currentDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_COMPANY_BILLS, null, COLUMN_COMPANY_ID + "=?", new String[]{companyID}, null, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_ID, companyID);
        contentValues.put(COLUMN_ORDER_BILL_NO, orderBillNo);
        contentValues.put(COLUMN_SALES_BILL_NO, saleBillNo);
        contentValues.put(COLUMN_DATE, currentDate);

        if (cursor.getCount() == 0) {
            /* If no data found insert the company Details*/
            db.insert(TABLE_COMPANY_BILLS, null, contentValues);
            cursor.close();
            db.close();
            return 0;
        } else {
            /*  If company Details found update the database*/
            db.update(TABLE_COMPANY_BILLS, contentValues, COLUMN_COMPANY_ID + " = ? ", new String[]{companyID});
            cursor.close();
            db.close();
            return 1;

        }
    }

    //insert API Table
    public void insertRegion(String Id, String level1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LEVEL1_ID, Id);
        contentValues.put(COLUMN_LEVEL1, level1);
        db.insert(TABLE_REGION, null, contentValues);
    }

    //insert API Table
    public void insertDistrict(String Id, String level2Id, String level2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LEVEL1_ID, Id);
        contentValues.put(COLUMN_LEVEL2_ID, level2Id);
        contentValues.put(COLUMN_LEVEL2, level2);
        db.insert(TABLE_DISTRICT, null, contentValues);
    }

    //insert API Table
    public void insertRoute(String level1Id, String level2Id, String level3Id, String level3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LEVEL1_ID, level1Id);
        contentValues.put(COLUMN_LEVEL2_ID, level2Id);
        contentValues.put(COLUMN_LEVEL3_ID, level3Id);
        contentValues.put(COLUMN_LEVEL3, level3);
        db.insert(TABLE_ROUTE, null, contentValues);
    }

    //insert API Table
    public void insertAPI(String apiName, String apiURL, String currentDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_API_NAME, apiName);
        contentValues.put(COLUMN_API_URL, apiURL);
        contentValues.put(COLUMN_DATE, currentDate);
        db.insert(TABLE_API, null, contentValues);
    }

    //get the table list based on its date
    public Cursor getTable(String tableName) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " ORDER BY " + tableName + ".date DESC;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the table list based on its date
    public Cursor getRegionTable(String tableName) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    public String lastCount(String id, String tableName, String key, String value) {
        String count = null;
        String Query = "SELECT DISTINCT " + id + " FROM " + tableName + " where " + key + "='" + value + "' ORDER BY  id  DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        try {
            res.moveToFirst();
            count = res.getString(res.getColumnIndex(id));
            Log.e(TAG, "result: " + count);
            res.close();
        } catch (CursorIndexOutOfBoundsException e) {
            e.getLocalizedMessage();
        }
        return count;
    }

    //get the Data based on Name and Date
    public Cursor getTable(String tableName, String name) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " ORDER BY " + tableName + "." + name + "  ASC , " + tableName + ".date DESC;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }


    //get the Data based on Name and Date
    public Cursor product(String condition, String code, String date, String product, String discount,
                          String customerCode) {
        String Query = "SELECT pm.company_code,pm.plant_code,pm.salesorg_code,pm.price_list,pm.material_code,inventory.material_name, " +
                " mm.material_category, pm.amount,pm.tax as pricetax,pm.net_price,mm.uom,mm.taxdetails,pm.valid_from,pm.valid_to, " +
                " inventory.available_quantity,inventory.quantity,inventory.productImage,inventory.supervisor_code,user.supervisor_name, " +
                " user.salesarea_code,inventory.vehicle,ds.from_quantity as discount_from_quantity,ds.to_quantity   as discount_to_quantity, " +
                "ds.discount,ds.discount_type,ds.discount_wot, ds.discount_from ,ds.discount_till ,inventory.daycount,inventory.replace_quantity, " +
                "inventory.sold_quantity, sm.scheme,  sm.scheme_code,sm.scheme_name,sm.quantity as scheme_quantity, sm.free_quantity as scheme_free_quantity, " +
                " sm.scheme_from ,sm.scheme_till,sm.scheme_type,sm.material_code1 as scheme_material1, cm.loyalty ," +
                " sm.material_code2 as scheme_material2 FROM customer AS cm " +
                " left JOIN price_master AS pm ON pm.price_list" +
                " INNER JOIN inventory AS inventory ON inventory.material_code=pm.material_code " +
                " INNER JOIN material_master AS mm ON mm.material_code=inventory.material_code and mm.salesorg_code=cm.salesorg_code " +
                " INNER JOIN user AS user ON user.supervisor_code=inventory.supervisor_code " +
                " left JOIN discount_master AS ds ON mm.material_code=ds.material_code and ds.discount_code=cm.scheme_discount" +
                " left JOIN scheme_master AS sm ON sm.scheme_code=cm.scheme_discount " +
                " where pm.salesorg_code=mm.salesorg_code and cm.customerCode='" + customerCode + "' and inventory.supervisor_code='" + code + "' and pm.valid_from<='" + date + "' " +
                " AND pm.valid_to>='" + date + "' and inventory.available_quantity>'0'";

       /* if (condition.equals("0")) {
            Query = "SELECT inventory.material_code as material_code,inventory.material_name as material_name, inventory.available_quantity as available_quantity," + " " + " AS scheme_code FROM inventory " +
                    " WHERE supervisor_code = '" + code + "' AND inventory_date = '" + date + "'  AND inventory.material_name LIKE '" + product + "' % " +
                    " UNION SELECT (scheme_master.material_code1 || - || scheme_master.scheme_code || - || scheme_master.scheme) AS material_code," +
                    " (scheme_master.material_code1 || - || scheme_master.scheme) AS material_name,scheme_master.scheme_code FROM scheme_master " +
                    " WHERE scheme_from <='" + date + "' AND scheme_till>='" + date + "' AND scheme_master.scheme_code = '" + discount + "' AND scheme_master.material_code1 LIKE '" + product + "'%";
        } else if (condition.equals("1")) {

          } else if (condition.equals("2")) {
            Query = "SELECT * FROM inventory WHERE supervisor_code = '" + code + "' AND inventory_date= '" + date + "'  ";
        }*/

        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the Data based on Name and Date
    public Cursor productmaterialSchemeCode(String date, String productcode, String customerCode,
                                            String salesorg_code, String cmScheme_discount,
                                            String materialCode, String priceCode) {

        String Query = "SELECT  '' as scheme, '' as scheme_code,'' as scheme_name,'' as scheme_quantity, '' as scheme_free_quantity, " +
                " '' as scheme_from ,'' as scheme_till,'' as scheme_type,'' as scheme_material1, " +
                " '' as scheme_material2 , ds.from_quantity as discount_from_quantity,ds.to_quantity   as discount_to_quantity, " +
                " ds.discount,ds.discount_type,ds.discount_wot, ds.discount_from ,ds.discount_till," +
                " pm.valid_from,pm.valid_to,pm.amount,pm.tax as pricetax,pm.net_price, mm.* from material_master as mm " +
                " left JOIN price_master AS pm ON  mm.material_code=pm.material_code and   pm.price_list='" + priceCode + "'" +
                " left JOIN discount_master AS ds ON mm.material_code=ds.material_code and ds.discount_code='" +
                cmScheme_discount + "' where mm.salesorg_code ='" + salesorg_code + "' and mm.material_category='" + productcode + "'" +
                "and mm.material_code='" + materialCode + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "MATERIAL SCHEME " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "SCHEME COUNT: " + res.getCount());
        return res;
    }


    public Cursor product(String currenDate, String price_code, String salesorg_code, String cmScheme_discount) {


        String Query = "SELECT  mm.*,pm.valid_from,pm.valid_to,pm.amount,pm.tax as pricetax,pm.net_price,\n" +
                " pm.price_list,ds.from_quantity as discount_from_quantity,ds.to_quantity  as discount_to_quantity, \n" +
                " ds.discount,ds.discount_type,ds.discount_wot, ds.discount_from ,ds.discount_till ,  '' as scheme, \n" +
                " '' as scheme_code,'' as scheme_name,'' as scheme_quantity, '' as scheme_free_quantity, '' as scheme_from ,\n" +
                " '' as scheme_till,'' as scheme_type,'' as scheme_material1, '' as scheme_material2  from material_master as mm " +
                " left JOIN price_master AS pm ON pm.price_list='" + price_code + "' and  mm.material_code=pm.material_code" +
                " left JOIN discount_master AS ds ON mm.material_code=ds.material_code and ds.discount_code='" +
                cmScheme_discount + "' where mm.salesorg_code ='" + salesorg_code + "'  and pm.salesorg_code=mm.salesorg_code and pm.valid_from<='" + currenDate + "' " +
                "  and pm.valid_to>='" + currenDate + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the Data based on Name and Date
    public Cursor scheme(String cmSchemeCode, String materialCode, String date) {
        String query = "select sm.* from scheme_master  as sm " +
                "inner join material_master as mm on " +
                "mm.material_category=sm.material_code1 and mm.material_code='" + materialCode + "' " +
                "and  sm.scheme_code='" + cmSchemeCode + "' and scheme_from<='" + date + "' and scheme_till>='" + date + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "SCHEME: " + query);
        Cursor res = db.rawQuery(query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the Data based on Name and Date
    public Cursor getTableList(String tableName, String key, String value) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " ='" + value + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the Data based on Name and Date
    public Cursor getTableListGroup(String tableName, String key, String value) {

        String Query = "SELECT * FROM  " + tableName + " WHERE " + key + " ='" + value + "' GROUP by bill_no";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the Data based on Name and Date
    public Cursor getTable(String tableName, String name, String key, String value) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' ORDER BY " + tableName + "." + name + "  ASC , " + tableName + ".date DESC;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the table Date without
    public Cursor table(String tableName) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " ;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //get the table Date without
    public Cursor table(String tableName, String key) {

        String Query = "SELECT * FROM " + tableName + " ORDER BY " + key + " DESC LIMIT 1 ;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //check  the data is present in tax/UOM Table
    public boolean checkTable(String tableName, String input, String col1, String col2) {
        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE  " + "'" + input + "'" + " IN  ( " + col1 + " ," + col2 + " )";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        if (res.getCount() == 0) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    //get the UserDetails
    public Cursor getUserTable(String tableName) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " ;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    //Get the Table Value Group
    public Cursor getTableList(String tableName) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " GROUP BY " + tableName + "." + COLUMN_BILL_ID + " ORDER BY " + tableName + ".date DESC;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    public Cursor getTableListorder(String tableName, String
            key, String value, String key1, String value1, String key2, String value2) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' " + " AND " +
                key1 + " = '" + value1 + "' AND " + key2 + " = '" + value2 + "' GROUP BY " + tableName + "." + COLUMN_BILL_ID + " ORDER BY " +
                tableName + "." + COLUMN_BILL_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    public Cursor getTableList(String tableName, String key, String value, String key2, String value2, String order, String type) {

        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' AND " +
                key2 + " = '" + value2 + "'  ORDER BY " + order + " " + type;
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }
    //order

    public Cursor getTableList(String tableName, String key, String value, String key2, String value2) {
        String Query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key2 + " = '" + value2 + "' ";
        } else {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' AND " + key2 + " = '" + value2 + "' ";
        }
        Log.e(TAG, "getTableList:Query " + Query);
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    // sale in else
    public Cursor getTableList(String tableName, String key, String value, String key2, String value2,
                               String key3, String value3, String order, String type) {

        String Query;
        if (value2.equals(Constants.salesBill)) {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "'" +
                    " AND " + key2 + " != '" + Constants.cancel + "' AND " + key3 + " = '" + value3 + "'  ORDER BY " + order + " " + type;
            Log.e(TAG, "Table: getTableList if " + Query);
        } else {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "'" +
                    " AND " + key2 + " = '" + value2 + "' AND " + key3 + " = '" + value3 + "'  ORDER BY " + order + " " + type;
            Log.e(TAG, "Table: getTableList else" + Query);
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    // sale in else
//    public Cursor getTableList(String tableName, String key, String value, String key2, String value2,
//                               String key3, String value3, String order, String type) {
//
//        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "'" +
//                " AND " + key2 + " = '" + value2 + "' AND " + key3 + " = '" + value3 + "'  ORDER BY " + order + " " + type;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Log.e(TAG, "Table: " + Query);
//        Cursor res = db.rawQuery(Query, null);
//        Log.e(TAG, "Table: " + res.getCount());
//        return res;
//    }

    //sale in if

    public Cursor getTableList(String tableName, String key, String value, String key2, String value2,
                               String key3, String value3, String key4, String value4, String order, String type) {

        String Query;
        if (value3.equals(Constants.salesBill)) {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' " +
                    "AND " + key2 + " = '" + value2 + "'" + " AND " + key3 + " != '" + Constants.cancel + "'" + " AND "
                    + key4 + " = '" + value4 + "' ORDER BY " + order + " " + type;
            Log.e(TAG, "Table getTableList1 if: " + Query);
        } else {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' " +
                    "AND " + key2 + " = '" + value2 + "'" + " AND " + key3 + " = '" + value3 + "'" + " AND "
                    + key4 + " = '" + value4 + "' ORDER BY " + order + " " + type;
            Log.e(TAG, "Table getTableList1 else: " + Query);
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Log.e(TAG, "Table: " + value2);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }
//    public Cursor getTableList(String tableName, String key, String value, String key2, String value2,
//                               String key3, String value3, String key4, String value4, String order, String type) {
//
//        String Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE " + key + " = '" + value + "' " +
//                "AND " + key2 + " = '" + value2 + "'" + " AND " + key3 + " = '" + value3 + "'" + " AND "
//                + key4 + " = '" + value4 + "' ORDER BY " + order + " " + type;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Log.e(TAG, "Table: " + Query);
//        Cursor res = db.rawQuery(Query, null);
//        Log.e(TAG, "Table: " + res.getCount());
//        return res;
//    }

    public Cursor getTableList(String tableName, String tablename1, String key, String value, String key2, String value2) {

        String Query = "SELECT * FROM " + tableName + " inner join " + tablename1 + " on " + tablename1 + "." + key + "=" + tableName + "." + key + " where " + tablename1 + "." + key + "='" + value + "' and " + tablename1 + "." + key2 + "='" + value2 + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }


    //get the Specific value form the Table
    public String getTableValue(String tableName, String value) {
        String stateCode = null;
        String Query = "SELECT  DISTINCT " + tableName + "." + value + " FROM " + tableName + "  ;";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            stateCode = res.getString(0);
            Log.e(TAG, "Table: " + stateCode);
            res.close();
        }
        return stateCode;
    }

    public Cursor getInventory(String tableName, String key, String value, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " WHERE " + key1 + " = '" + value1 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " WHERE " + key + " = '" + value + "' AND " + key1 + " = '" + value1 + "'";
        }
        Log.e(TAG, "getInventory: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getFutureRequire(String tableName, String key, String value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getFutureRequireOrderID(String tableName, String key, String value, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
//        String query = "SELECT DISTINCT " + COLUMN_ORDER_NO + " FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + "='" + value1 + "'";
        String query = "SELECT DISTINCT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + "='" + value1 + "' group by " + COLUMN_ORDER_NO;
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getCustomerBills(String tableName, String key, String value, String key1, String value1, String key2, String value2) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getCustomerBills(String tableName, String key, String value, String key1, String value1, String key2, String value2, String key3, String value3) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "' and " + key3 + " = '" + value3 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "' and " + key3 + " = '" + value3 + "'";
        }
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getPaymentDetails(String tableName, String key, String value, String key1, String value1, String key2, String value2) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    //invoice upload list
    public Cursor getInvoiceuploadpp(String tableName, String key, String value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + "bill_status!='Unpaid' and " + " bill_status!='Cancelled'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    //invoice bill list
    public Cursor getInvoicebillList(String tableName, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "' and " + " bill_status!='Paid' and " + " bill_status!='Cancelled'";
        Log.e(TAG, "getInvoicebillList: " + query);
        return db.rawQuery(query, null);

    }

    //invoice bill list
    public Cursor getInvoiceLastbillList(String tableName, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "' and " + " bill_status!='Paid' and " + " bill_status!='Cancelled' Order by id limit 1";
        Log.e(TAG, "getInvoicebillList: " + query);
        return db.rawQuery(query, null);

    }

    //invoice bill list
    public Cursor getInvoicebilloffline(String tableName, String key, String value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getPaymentDetails(String tableName, String key, String value, String key1, String value1, String key2, String value2, String key3, String value3) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "' and " + key3 + " = '" + value3 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "' and " + key3 + " = '" + value3 + "'";
        }
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    //online Reviews upload
    public Cursor getReviewDetails(String tableName, String key, String value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }

    // Review details upload offline
    public Cursor getReviewDetailsoffline(String tableName, String key, String value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "'";
        Log.e(TAG, "getFutureRequire: " + query);
        return db.rawQuery(query, null);

    }


    public Cursor getReturnsEntry(String tableName, String key, String value, String key1, String value1, String key2, String value2) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        }
        Log.e(TAG, "getReturnsEntry: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getReturnsEntry(String tableName, String key, String value, String key1, String value1, String key2, String value2, String key3, String value3) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "' and " + key3 + " = '" + value3 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "' and " + key3 + " = '" + value3 + "'";
        }
        Log.e(TAG, "getReturnsEntry: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getReplaceEntryEntry(String tableName, String key, String value, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " where " + key1 + " = '" + value1 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "'";
        }
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getReplaceEntryEntry(String tableName, String key, String value, String key1, String value1, String key2, String value2) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (key.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value.equals("all")) {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        } else {
            query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        }
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }


    public Cursor getTransactionDetails(String tableName, String key, String value, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "'";
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getTransactionDetails(String tableName, String key, String value, String key1, String value1, String key2, String value2) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getProductDetails(String tableName, String key, String value, String key1, String value1) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "'";
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }

    //get all values list
    public Cursor getAllDetails(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName;
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }

    public Cursor getProductDetails(String tableName, String key, String value, String key1, String value1, String key2, String value2) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " where " + key + " = '" + value + "' and " + key1 + " = '" + value1 + "' and " + key2 + " = '" + value2 + "'";
        Log.e(TAG, "getReplaceEntryEntry: " + query);
        return db.rawQuery(query, null);

    }

    //Get Detail Bill List
    public Cursor getDetailsList(String tableName, String table, String billNo) {

        String Query = null;
        if (table.equals("order")) {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE  " + tableName + "." + COLUMN_BILL_ID + " = " + "'" + billNo + "'" + " ;";
        } else if (table.equals("sales")) {
            Query = "SELECT  DISTINCT  * FROM  " + tableName + " WHERE  " + tableName + "." + COLUMN_BILL_ID + " = " + "'" + billNo + "'" + " ;";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e(TAG, "Table: " + Query);
        Cursor res = db.rawQuery(Query, null);
        Log.e(TAG, "Table: " + res.getCount());
        return res;
    }

    public void deleteTable(String tableName) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + tableName);
    }

    public void tableDelete() {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_USER);
        db.execSQL("DELETE FROM " + TABLE_CUSTOMER);
        db.execSQL("DELETE FROM " + TABLE_PRODUCT);
        db.execSQL("DELETE FROM " + TABLE_ORDERS);
        db.execSQL("DELETE FROM " + TABLE_SALES);
        db.execSQL("DELETE FROM " + TABLE_SALES_ITEMS);
        db.execSQL("DELETE FROM " + TABLE_COMPANY);
        db.execSQL("DELETE FROM " + TABLE_COMPANY_BILLS);
        db.execSQL("DELETE FROM " + TABLE_PAYMENT);
        db.execSQL("DELETE FROM " + TABLE_TAX);
        db.execSQL("DELETE FROM " + TABLE_UOM);
        db.execSQL("DELETE FROM " + TABLE_API);

        db.execSQL("DELETE FROM  " + TABLE_SALES_MAN);
        db.execSQL("DELETE FROM  " + TABLE_LEADER);
        db.execSQL("DELETE FROM  " + TABLE_REGION);
        db.execSQL("DELETE FROM  " + TABLE_ROUTE);
        db.execSQL("DELETE FROM  " + TABLE_DISTRICT);

        db.execSQL("DELETE FROM   " + TABLE_CUSTOMER_ACCOUNTS);
        db.execSQL("DELETE FROM   " + TABLE_CUSTOMER_MASTER);
        db.execSQL("DELETE FROM  discount_master ");
        db.execSQL("DELETE FROM  " + TABLE_INVENTORY);
        db.execSQL("DELETE FROM  machine_details ");
        db.execSQL("DELETE FROM  " + TABLE_MATERIAL_MASTER);
        db.execSQL("DELETE FROM  " + TABLE_PAYMENT_DETAILS);
        db.execSQL("DELETE FROM  price_master ");
        db.execSQL("DELETE FROM  remember_me ");
        db.execSQL("DELETE FROM  " + TABLE_PRODUCT_DETAILS);
        db.execSQL("DELETE FROM  " + TABLE_REPLACEMENT_ENTRY);
        db.execSQL("DELETE FROM  " + TABLE_RETURN_ENTRY);
        db.execSQL("DELETE FROM  scheme_master ");
//        db.execSQL("DELETE FROM  " + TABLE_SUPERVISOR);
        db.execSQL("DELETE FROM  " + TABLE_TRANSACTION_DETAILS);
        db.execSQL("DELETE FROM  updates ");
        db.execSQL("DELETE FROM  backup_details ");
        db.execSQL("DELETE FROM  bill_header ");
        db.execSQL("DELETE FROM " + TABLE_BILLING_DETAILS);
        db.execSQL("DELETE FROM   " + TABLE_APP_DETAILS);
//        db.execSQL("DELETE FROM   " + TABLE_TRIP_LOG);
//        db.execSQL('INSERT INTO user_master (id, username, password, version, app_version, today_date) VALUES ("1", "admin", "21232f297a57a5a743894a0e4a801fc3", "0", "0", "'+todaydate+'")');
//        db.execSQL("SELECT * FROM user_master where version='0' ", [], function(tx, res) {
        db.execSQL("DELETE FROM  " + TABLE_FUTURE_REQUIRED);
        //  db.execSQL("DELETE FROM  " + TABLE_MENU);
        db.execSQL("DELETE FROM  " + TABLE_INVOICE);
        db.execSQL("DELETE FROM  " + TABLE_CUSTOMER_REASONS);
        db.execSQL("DELETE FROM  " + TABLE_CUSTOMER_FEEDBACK);

        db.close();
    }

    public void delete(String tableName, String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE  FROM " + tableName + " WHERE " + key + " = '" + value + "'";
        Log.e(TAG, "delete: " + query);
        db.execSQL(query);
//        db.rawQuery("DELETE  FROM " + tableName, null);
        db.close();
    }

    public String getSpcName(String tableName, String keyName, String name, String value) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + keyName + " FROM " + tableName + " Where " + name + " = '" + value + "'";
        Log.e("TAG", "query=" + query);
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            name = c.getString(c.getColumnIndex(keyName));
        }
        c.close();
        db.close();
        return name;
    }

    //getimage in db
    public byte[] getimg(String tableName, String keyName, String name, String value) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + keyName + " FROM " + tableName + " Where " + name + " = '" + value + "'";
        Log.e("TAG", "query=" + query);
        Cursor c = db.rawQuery(query, null);
        byte[] Image = null;
        if (c.getCount() > 0) {
            c.moveToFirst();
            Image = c.getBlob(c.getColumnIndex(keyName));
            c.close();
            db.close();
            return Image;
        } else {
            c.close();
            db.close();
            return Image;
        }

    }

    public byte[] getSpcByte(String tableName, String keyName, String name, String value) {

        SQLiteDatabase db = this.getWritableDatabase();
        byte[] img = null;
        String query = "SELECT " + keyName + " FROM " + tableName + " Where " + name + " = '" + value + "'";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            img = c.getBlob(c.getColumnIndex(keyName));
        }
        c.close();
        db.close();
        return img;
    }

    public String getSpcName(String tableName, String keyName) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + keyName + " FROM " + tableName;
        Cursor c = db.rawQuery(query, null);
        String name = null;
        if (c.getCount() > 0) {
            c.moveToFirst();
            name = c.getString(c.getColumnIndex(keyName));
        }
        c.close();
        db.close();
        return name;
    }

    public String getSpcLastName(String tableName, String keyName) {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + keyName + " FROM " + tableName;
        Cursor c = db.rawQuery(query, null);
        String name = null;
        if (c.getCount() > 0) {
            c.moveToLast();
            name = c.getString(c.getColumnIndex(keyName));
        }
        c.close();
        db.close();
        return name;
    }

    //checking contact number exists or not
    public boolean check_data(String tableName, String dbfield, String fieldValue) {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "Select * from " + tableName + " where " + dbfield + " = '" + fieldValue + "'";
        Log.e(TAG, "check_data: " + Query);
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public boolean check_data(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "Select * from " + tableName;
        Log.e(TAG, "check_data: " + Query);
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    //checking contact number exists or not
    public boolean check_data(String tableName, String dbfield, String fieldValue, String dbfield1, String fieldValue1) {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "Select * from " + tableName + " where " + dbfield + " = '" + fieldValue + "' and " + dbfield1 + " = '" + fieldValue1 + "'";
        Log.e(TAG, "check_data: " + Query);
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public Cursor customerReport(String date) {
        SQLiteDatabase db = getWritableDatabase();
//        String Query=
//                "select  c.customerCode as id , c.customerName as name, bd.bill_no as billing,bd.bill_status,pd.payment_key as payment, pd.status , o.orderID as orders,o.orderstatus "+
//                        " from   customer as c left join billing_details as bd on c .customerCode = bd.customer_code "+
//                        " and bd.created_on= '" + date +"' and bd.bill_status!='Cancelled' left join orders as o on c.customerCode = o.customerID "+
//                        " and o.date= '" + date +"' and o.orderstatus!='Cancelled' left join payment_details  as pd on c.customerCode = pd.customer_code "+
//                        "and  pd.created_on= '" + date +"' and pd.status!='0' left join invoice as i on c.customerCode =  i.customer_code  " +
//                        " and  i.bill_date= '" + date +"' and  i.bill_status!='Cancelled' OR i.bill_status!='Unpaid' group by  c.customerCode  order by c.customerCode ;";

        String Query =
                "select  c.customerCode as id , c.customerName as name, c.latitude as latitude,c.longitude as longitude, bd.bill_no as billing,bd.bill_status,pd.payment_key as payment, pd.status , o.orderID as orders,o.orderstatus " +
                        " from   customer as c left join billing_details as bd on c .customerCode = bd.customer_code " +
                        " and bd.created_on= '" + date + "' and bd.bill_status!='Cancelled' left join orders as o on c.customerCode = o.customerID " +
                        " and o.date= '" + date + "' and o.orderstatus!='Cancelled' left join payment_details  as pd on c.customerCode = pd.customer_code " +
                        "and  pd.created_on= '" + date + "' and pd.status!='0' group by  c.customerCode  order by c.customerCode ;";

        Log.e(TAG, "check_data located: " + Query);
        Cursor cursorr = null;
        Cursor cursor = db.rawQuery(Query, null);
        Log.e("TAG", "Customer Cursor1=" + cursor.getCount());
        if (cursor.getCount() > 0) {

            cursorr = cursor;
            //  cursor.close();
            return cursorr;
        }
        //  cursor.close();
        //db.close();
        Log.e("TAG", "Customer Cursor2=" + cursor.getCount());
        return cursorr;
    }

    //customerlocateNot report
    public Cursor customerNotlocateReport(String date) {
        SQLiteDatabase db = getWritableDatabase();
        String Query =
                "select  c.customerCode as id , c.customerName as name, c.latitude as latitude,c.longitude as longitude, bd.bill_no as billing,bd.bill_status,pd.payment_key as payment, pd.status , o.orderID as orders,o.orderstatus " +
                        " from   customer as c left join billing_details as bd on c .customerCode = bd.customer_code " +
                        " and bd.created_on= '" + date + "' and bd.bill_status='Cancelled' left join orders as o on c.customerCode = o.customerID " +
                        " and o.date= '" + date + "' and o.orderstatus='Cancelled' left join payment_details  as pd on c.customerCode = pd.customer_code " +
                        "and  pd.created_on= '" + date + "' and pd.status='0' group by  c.customerCode  order by c.customerCode ;";


        Log.e(TAG, "check_data not locate: " + Query);
        Cursor cursorr = null;
        Cursor cursor = db.rawQuery(Query, null);
        Log.e("TAG", "Customer Cursor1=" + cursor.getCount());
        if (cursor.getCount() > 0) {

            cursorr = cursor;
            //  cursor.close();
            return cursorr;
        }
        //  cursor.close();
        //db.close();
        Log.e("TAG", "Customer Cursor2=" + cursor.getCount());
        return cursorr;
    }

    //checking exists or not
    public boolean check_data(String tableName, String dbfield, String fieldValue, String dbfield1,
                              String fieldValue1, String dbfield2, String fieldValue2) {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "Select * from " + tableName + " where " + dbfield + " = '" + fieldValue +
                "' and " + dbfield1 + " = '" + fieldValue1 + "' and " + dbfield2 + " = '" + fieldValue2 + "'";
        Log.e(TAG, "check_data: " + Query);
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }


    public String[] getRegionList(String tableName, String keyName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName;
        Cursor c = db.rawQuery(query, null);
        String[] reagion = new String[c.getCount()];
        int i = 0;
        Log.e(TAG, "getRegionList: i count : " + i);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String name = c.getString(c.getColumnIndex(keyName));
                reagion[i] = name;
                Log.e(TAG, "getRegionList: do i count : " + i);
                i++;
                Log.e(TAG, "getRegionList:increment i count : " + i);
            } while (c.moveToNext());
        }
        Log.e(TAG, "getRegionList: region size : " + reagion.length);
        c.close();
        db.close();
        return reagion;
    }

    public List<RouteDayStartDatasource> getDayStart(String tableName, String tableName1, String key, String key1, String value1, String key2, String value2,
                                                     String key3, String value3) {
        List<RouteDayStartDatasource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "";
        String query1 = "SELECT * ,(select supervisor_name from " + TABLE_SUPERVISOR + " where supervisor_code='S029') as supervisor_name  from  inventory where supervisor_code='S029' AND inventory_date='2019-05-28' AND salesarea_code='PETTAVAITHALAI'";
        if (key3.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value3.equals("all")) {
            query = "SELECT * ,( select " + key + " FROM " + tableName + " WHERE " + key1 + " = '" + value1 + "') as " + key + " from " + tableName1
                    + " where " + key1 + " = '" + value1 + "' and " + key2 + "= '" + value2 + "'";
        } else {
            query = "SELECT * ,( select " + key + " FROM " + tableName + " WHERE " + key1 + " = '" + value1 + "') as " + key + " from " + tableName1
                    + " where " + key1 + " = '" + value1 + "' and " + key2 + "= '" + value2 + "' and " + key3 + "= '" + value3 + "'";
        }
        Log.e(TAG, "getDayStart: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getDayStart: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String companyCode = c.getString(c.getColumnIndex("company_code"));
                String plantCode = c.getString(c.getColumnIndex(COLUMN_PLANT_CODE));
                String salesOrgCode = c.getString(c.getColumnIndex(COLUMN_SALESORG_CODE));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialNmae = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String quantity = c.getString(c.getColumnIndex(COLUMN_QUANTITY));
                String avaQuantity = c.getString(c.getColumnIndex(COLUMN_AVAILABLE_QUANTITY));
                String supervisor_code = c.getString(c.getColumnIndex(COLUMN_SUPERVISOR_CODE));
                String machine_id = c.getString(c.getColumnIndex("machine_id"));
                String vehicle = c.getString(c.getColumnIndex(COLUMN_VEHICLE));
                String inventory_date = c.getString(c.getColumnIndex(COLUMN_INVENTORY_DATE));
                String supervisor_name = c.getString(c.getColumnIndex(COLUMN_SUPERVISOR_NAME));
                routeDayStartDatasourceList.add(new RouteDayStartDatasource(companyCode, plantCode, salesOrgCode,
                        salesAreaCode, materialcode, materialNmae, quantity,
                        avaQuantity, supervisor_code, machine_id, vehicle, inventory_date, supervisor_name));
            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<RouteDayStartDatasource> getDayEnd(String tableName, String tableName1, String key, String key1, String value1, String key2, String value2,
                                                   String key3, String value3) {
        List<RouteDayStartDatasource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "";
        String query1 = "SELECT * ,(select supervisor_name from " + TABLE_SUPERVISOR + " where supervisor_code='S029') as supervisor_name  from  inventory where supervisor_code='S029' AND inventory_date='2019-05-28' AND salesarea_code='PETTAVAITHALAI'";
        if (key3.equals(DBHelper.COLUMN_SALES_AREA_CODE) && value3.equals("all")) {
            query = "SELECT * ,( select " + key + " FROM " + tableName + " WHERE " + key1 + " = '" + value1 + "') as " + key + " from " + tableName1
                    + " where " + key1 + " = '" + value1 + "' and " + key2 + "= '" + value2 + "'";
        } else {
            query = "SELECT * ,( select " + key + " FROM " + tableName + " WHERE " + key1 + " = '" + value1 + "') as " + key + " from " + tableName1
                    + " where " + key1 + " = '" + value1 + "' and " + key2 + "= '" + value2 + "' and " + key3 + "= '" + value3 + "'";
        }
        Log.e(TAG, "getDayEnd: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getDayEnd: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String companyCode = c.getString(c.getColumnIndex("company_code"));
                String plantCode = c.getString(c.getColumnIndex(COLUMN_PLANT_CODE));
                String salesOrgCode = c.getString(c.getColumnIndex(COLUMN_SALESORG_CODE));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialNmae = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String quantity = c.getString(c.getColumnIndex(COLUMN_QUANTITY));
                String avaQuantity = c.getString(c.getColumnIndex(COLUMN_AVAILABLE_QUANTITY));
                String supervisor_code = c.getString(c.getColumnIndex(COLUMN_SUPERVISOR_CODE));
                String machine_id = c.getString(c.getColumnIndex("machine_id"));
                String vehicle = c.getString(c.getColumnIndex(COLUMN_VEHICLE));
                String inventory_date = c.getString(c.getColumnIndex(COLUMN_INVENTORY_DATE));
                String soldQuantity = c.getString(c.getColumnIndex(COLUMN_SOLD_QUANTITY));
                String replaceQuantity = c.getString(c.getColumnIndex(COLUMN_REPLACE_QUANTITY));
                String supervisor_name = c.getString(c.getColumnIndex(COLUMN_SUPERVISOR_NAME));
                routeDayStartDatasourceList.add(new RouteDayStartDatasource(companyCode, plantCode, salesOrgCode, salesAreaCode, materialcode, materialNmae, quantity,
                        avaQuantity, supervisor_code, machine_id, vehicle, inventory_date, supervisor_name, soldQuantity, replaceQuantity));
            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<ReturnProductDataSource> getReturnProduct(String tableName, String customer_code, String return_date1) {
        List<ReturnProductDataSource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT material_code,material_name,returns,sum(return_quantity) as qty from return_entry where entered_by='S029' AND return_date='2019-06-04' AND salesarea_code='OMR1'  group by material_code,returns ";
        String query = "SELECT * from " + tableName + " where " + COLUMN_CUSTOMER_CODE_SMALL + "='" + customer_code + "' " + " and " + COLUMN_RETURN_DATE + "='" + return_date1 + "' " + " ORDER By return_date DESC";
        Log.e(TAG, "getDayStart: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getDayStart: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String companyCode = c.getString(c.getColumnIndex(COLUMN_COMPANY_CODE_SMALL));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String customerCode = c.getString(c.getColumnIndex(COLUMN_CUSTOMER_CODE_SMALL));
                String returns = c.getString(c.getColumnIndex("returns"));
                String return_date = c.getString(c.getColumnIndex(COLUMN_RETURN_DATE));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialNmae = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String return_quantity = c.getString(c.getColumnIndex("return_quantity"));
                String replacement_quantity = c.getString(c.getColumnIndex("replacement_quantity"));
                String balance_quantity = c.getString(c.getColumnIndex("balance_quantity"));
                String replace_date = c.getString(c.getColumnIndex("replace_date"));
                String status = c.getString(c.getColumnIndex("status"));
                String entered_by = c.getString(c.getColumnIndex("entered_by"));
                String created_on = c.getString(c.getColumnIndex("created_on"));
                String return_key = c.getString(c.getColumnIndex("return_key"));
                String randomkey = c.getString(c.getColumnIndex("randomkey"));
                String is_Online = c.getString(c.getColumnIndex(COLUMN_IS_ONLINE));
                routeDayStartDatasourceList.add(new ReturnProductDataSource(companyCode, salesAreaCode, customerCode, returns, return_date, materialcode, materialNmae,
                        return_quantity, replacement_quantity, balance_quantity, replace_date, status, entered_by, created_on, return_key, randomkey));
            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<ReturnProductDataSource> getDayEndReturn(String tableName, String enteredBy, String date, String salesarea_code) {
        List<ReturnProductDataSource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "";
        String query1 = "SELECT material_code,material_name,returns,sum(return_quantity) as qty from return_entry where entered_by='S029' AND return_date='2019-06-04' AND salesarea_code='OMR1'  group by material_code,returns ";
        if (salesarea_code.equals("all")) {
            query = "SELECT material_code,material_name,returns,sum(return_quantity) as return_quantity from " + tableName + " where entered_by='" + enteredBy + "' AND return_date='" + date + "'  group by material_code,returns ";
        } else {
            query = "SELECT material_code,material_name,returns,sum(return_quantity) as return_quantity from " + tableName + " where entered_by='" + enteredBy + "' AND return_date='" + date + "' AND salesarea_code='" + salesarea_code + "'  group by material_code,returns ";
        }
        Log.e(TAG, "getDayStart: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getDayStart: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String returns = c.getString(c.getColumnIndex("returns"));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialNmae = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                int return_quantity = c.getInt(c.getColumnIndex("return_quantity"));
                routeDayStartDatasourceList.add(new ReturnProductDataSource(returns, materialcode, materialNmae, String.valueOf(return_quantity)));
            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<PaymentDataSource> getPaymentDetails(String tableName, String value) {
        List<PaymentDataSource> paymentDataSourceArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT (select sum(amount) FROM payment_details where date='2019-03-05' and mode='Cash'  and status='')  as cash , (select count(mode) FROM payment_details where date='2019-03-05'  and mode='Cheque' and status='')  as cheque  FROM payment_details where date='2019-03-05'  and status='' group by date";
        String query = "SELECT (select sum(amount) FROM " + tableName + " where date='" + value
                + "' and mode='" + Constants.CA + "'  and status='1')  as cash , (select count(mode) FROM " + tableName + " where date='" + value
                + "'  and mode='" + Constants.CH + "' and status='1')  as cheque  FROM " + tableName + " where date='" + value + "'  and status='1' group by date";

        Log.e(TAG, "getPaymentDetails: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getPaymentDetails: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String cash = c.getString(c.getColumnIndex("cash"));
                String cheque = c.getString(c.getColumnIndex("cheque"));

                paymentDataSourceArrayList.add(new PaymentDataSource(cash, cheque));
            } while (c.moveToNext());
        }
        return paymentDataSourceArrayList;
    }

    public List<PaymentDataSource> getPaymentDetails(String tableName, String key, String value, String key1, String value1) {
        List<PaymentDataSource> paymentDataSourceArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT (select sum(amount) FROM payment_details where date='2019-03-05' and mode='Cash'  and status='')  as cash , (select count(mode) FROM payment_details where date='2019-03-05'  and mode='Cheque' and status='')  as cheque  FROM payment_details where date='2019-03-05'  and status='' group by date";
        String query = "SELECT * FROM " + tableName + " where " + key + "='" + value + "' and " + key1 + " = '" + value1 + "' order by id desc ";

        Log.e(TAG, "getPaymentDetails: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getPaymentDetails: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String cheque_number = c.getString(c.getColumnIndex("cheque_number"));
                String date = c.getString(c.getColumnIndex(COLUMN_DATE));
                String companyCode = c.getString(c.getColumnIndex(COLUMN_COMPANY_CODE_SMALL));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String customerCode = c.getString(c.getColumnIndex(COLUMN_CUSTOMER_CODE_SMALL));
                String mode = c.getString(c.getColumnIndex("mode"));
                String amount = c.getString(c.getColumnIndex(COLUMN_AMOUNT));
                String cheque_date = c.getString(c.getColumnIndex("cheque_date"));
                String bank_name = c.getString(c.getColumnIndex("bank_name"));
                String randomkey = c.getString(c.getColumnIndex("randomkey"));
                String entered_by = c.getString(c.getColumnIndex("entered_by"));
                String created_on = c.getString(c.getColumnIndex(COLUMN_CREATED_ON));
                String status = c.getString(c.getColumnIndex(COLUMN_STATUS));
                String bill_no = c.getString(c.getColumnIndex("bill_no"));
                String time = c.getString(c.getColumnIndex(COLUMN_TIME));
                String payment_key = c.getString(c.getColumnIndex(COLUMN_PAYMENT_KEY));
                paymentDataSourceArrayList.add(new PaymentDataSource(amount, cheque_number, payment_key, bill_no, mode, date, companyCode, salesAreaCode, cheque_date,
                        customerCode, bank_name, randomkey, entered_by, created_on, status, time));
            } while (c.moveToNext());
        }
        return paymentDataSourceArrayList;
    }


    public List<PaymentDataSource> getPaymentDetailss(String tableName, String key, String tableName1, String tableName2, String key2,
                                                      String condition, String conditionValue
    ) {
        List<PaymentDataSource> paymentDataSourceArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + tableName + ".*," + tableName1 + "." + COLUMN_LEDGER_BALANCE + "," + tableName2 + "." + COLUMN_CUSTOMER_NAME + "," + tableName2 + "."
                + COLUMN_CUSTOMER_IMAGE + " FROM " + tableName + " INNER JOIN " + tableName1 + " ON " + tableName + "." + key + "=" + tableName1 + "." + key +
                " INNER JOIN " + tableName2 + " ON " + tableName + "." + key + "=" + tableName2 + "." + key2 +
                " where " + tableName + "." + condition + "='" + conditionValue + "'";

        Log.e(TAG, "getPaymentDetails: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getPaymentDetails: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String cheque_number = c.getString(c.getColumnIndex("cheque_number"));
                String date = c.getString(c.getColumnIndex(COLUMN_DATE));
                String companyCode = c.getString(c.getColumnIndex(COLUMN_COMPANY_CODE_SMALL));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String customerCode = c.getString(c.getColumnIndex(COLUMN_CUSTOMER_CODE_SMALL));
                String mode = c.getString(c.getColumnIndex("mode"));
                String amount = c.getString(c.getColumnIndex(COLUMN_AMOUNT));
                String cheque_date = c.getString(c.getColumnIndex("cheque_date"));
                String bank_name = c.getString(c.getColumnIndex("bank_name"));
                String randomkey = c.getString(c.getColumnIndex("randomkey"));
                String entered_by = c.getString(c.getColumnIndex("entered_by"));
                String created_on = c.getString(c.getColumnIndex(COLUMN_CREATED_ON));
                String status = c.getString(c.getColumnIndex(COLUMN_STATUS));
                String bill_no = c.getString(c.getColumnIndex("bill_no"));
                String payment_key = c.getString(c.getColumnIndex(COLUMN_PAYMENT_KEY));
                String customerName = c.getString(c.getColumnIndex("customerName"));
                String ledger_balance = c.getString(c.getColumnIndex("ledger_balance"));
                byte[] customerImage = c.getBlob(c.getColumnIndex("customerImage"));
                paymentDataSourceArrayList.add(new PaymentDataSource(amount, cheque_number, payment_key, bill_no,
                        mode, date, companyCode, salesAreaCode, cheque_date,
                        customerCode, bank_name, randomkey, entered_by, created_on, status, customerName, ledger_balance, customerImage));
            } while (c.moveToNext());
        }
        return paymentDataSourceArrayList;
    }

    public List<ReplaceProductDataSource> getReplacementProduct(String tableName, String tableName1, String salesarea_code, String inventory_date, String supervisor_code, String customer_code) {
        List<ReplaceProductDataSource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT *,replacement_entry.material_code,IFNULL((SELECT available_quantity FROM inventory WHERE salesarea_code='OMR2' AND inventory_date='2019-06-04' AND supervisor_code='S021' AND material_code=replacement_entry.material_code),0) AS avail FROM replacement_entry where customer_code='C00057' and balance_quantity > 0";
        String query = "";
        if (salesarea_code.equals("all")) {
            query = "SELECT *," + TABLE_REPLACEMENT_ENTRY + ".material_code,IFNULL((SELECT available_quantity FROM " + tableName + " WHERE inventory_date='" + inventory_date + "' AND supervisor_code='" + supervisor_code + "' AND material_code=" + TABLE_REPLACEMENT_ENTRY + ".material_code),0) AS avail FROM " + tableName1 + " where customer_code='" + customer_code + "' and balance_quantity > 0";
        } else {
            query = "SELECT *," + TABLE_REPLACEMENT_ENTRY + ".material_code,IFNULL((SELECT available_quantity FROM " + tableName + " WHERE salesarea_code='" + salesarea_code + "' AND inventory_date='" + inventory_date + "' AND supervisor_code='" + supervisor_code + "' AND material_code=" + TABLE_REPLACEMENT_ENTRY + ".material_code),0) AS avail FROM " + tableName1 + " where customer_code='" + customer_code + "' and balance_quantity > 0";
        }
        Log.e(TAG, "getReplacementProduct: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getReplacementProduct: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String companyCode = c.getString(c.getColumnIndex(COLUMN_COMPANY_CODE_SMALL));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String customerCode = c.getString(c.getColumnIndex(COLUMN_CUSTOMER_CODE_SMALL));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialNmae = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String date = c.getString(c.getColumnIndex("date"));
                String return_quantity = c.getString(c.getColumnIndex("return_quantity"));
                String replace_quantity = c.getString(c.getColumnIndex("replace_quantity"));
                String balance_quantity = c.getString(c.getColumnIndex("balance_quantity"));
                String replace_key = c.getString(c.getColumnIndex("replace_key"));
                String randomkey = c.getString(c.getColumnIndex("randomkey"));
                String is_Online = c.getString(c.getColumnIndex(COLUMN_IS_ONLINE));
                String avail = c.getString(c.getColumnIndex("avail"));

                if (Integer.parseInt(avail) > 0) {
                    routeDayStartDatasourceList.add(new ReplaceProductDataSource(companyCode, salesAreaCode, customerCode, materialcode, materialNmae, date,
                            return_quantity, replace_quantity, balance_quantity, replace_key, randomkey, avail, is_Online, true, false, true));
                } else {
                    routeDayStartDatasourceList.add(new ReplaceProductDataSource(companyCode, salesAreaCode, customerCode, materialcode, materialNmae, date,
                            return_quantity, replace_quantity, balance_quantity, replace_key, randomkey, avail, is_Online, true, false, false));
                }

            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<ReplaceProductDataSource> getReplacement(String tableName, String customer_code, String material_code) {
        List<ReplaceProductDataSource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT * FROM replacement_entry where customer_code='C00005' and material_code='IDB1K'";
        String query = "SELECT * FROM " + tableName + " where customer_code='" + customer_code + "' and material_code='" + material_code + "'";
        Log.e(TAG, "getReplacement: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getReplacement: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String companyCode = c.getString(c.getColumnIndex(COLUMN_COMPANY_CODE_SMALL));
                String salesAreaCode = c.getString(c.getColumnIndex(COLUMN_SALES_AREA_CODE));
                String customerCode = c.getString(c.getColumnIndex(COLUMN_CUSTOMER_CODE_SMALL));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialNmae = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String date = c.getString(c.getColumnIndex("date"));
                String return_quantity = c.getString(c.getColumnIndex("return_quantity"));
                String replace_quantity = c.getString(c.getColumnIndex("replace_quantity"));
                String balance_quantity = c.getString(c.getColumnIndex("balance_quantity"));
                String replace_key = c.getString(c.getColumnIndex("replace_key"));
                String randomkey = c.getString(c.getColumnIndex("randomkey"));
                String is_Online = c.getString(c.getColumnIndex(COLUMN_IS_ONLINE));

                routeDayStartDatasourceList.add(new ReplaceProductDataSource(companyCode, salesAreaCode, customerCode, materialcode, materialNmae, date,
                        return_quantity, replace_quantity, balance_quantity, replace_key, randomkey, is_Online));


            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<ReplaceProductDataSource> getDyEndReplacement(String tableName, String date, String salesarea_code, String screen) {
        List<ReplaceProductDataSource> routeDayStartDatasourceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "";
        //String query1 = "SELECT material_code,material_name,sum(balance_quantity) as pending_qty,sum(replace_quantity) as replace_qty from replacement_entry where date='2019-06-05' AND salesarea_code='OMR1'  group by material_code ";
        if (screen.equals("replace")) {
            if (salesarea_code.equals("all")) {
                query = "SELECT material_code,material_name,sum(balance_quantity) as pending_qty,sum(replace_quantity) as replace_qty from " + tableName + " where date='" + date + "' group by material_code ";
            } else {
                query = "SELECT material_code,material_name,sum(balance_quantity) as pending_qty,sum(replace_quantity) as replace_qty from " + tableName + " where date='" + date + "' AND salesarea_code='" + salesarea_code + "' group by material_code ";
            }
        } else {
            if (salesarea_code.equals("all")) {
                query = "SELECT material_code,material_name,sum(balance_quantity) as pending_qty,sum(replace_quantity) as replace_qty from " + tableName + " where date='" + date + "' AND replace_quantity !=0 group by material_code ";
            } else {
                query = "SELECT material_code,material_name,sum(balance_quantity) as pending_qty,sum(replace_quantity) as replace_qty from " + tableName + " where date='" + date + "' AND salesarea_code='" + salesarea_code + "' AND replace_quantity !=0 group by material_code ";
            }
        }
        Log.e(TAG, "getReplacementProduct: " + query);
        Cursor c = db.rawQuery(query, null);
        Log.e(TAG, "getReplacementProduct: get count : " + c.getCount());
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {

                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String materialName = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String pending_qty = c.getString(c.getColumnIndex("pending_qty"));
                String replace_quantity = c.getString(c.getColumnIndex("replace_qty"));

                routeDayStartDatasourceList.add(new ReplaceProductDataSource(materialcode, materialName, replace_quantity, pending_qty));
            } while (c.moveToNext());
        }
        return routeDayStartDatasourceList;
    }

    public List<RouteDayStartDatasource> getOrder(String orderdate1) {
        List<RouteDayStartDatasource> orderpreview = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT sum (quantity) as quantity, material_name, material_code from future_require where " + COLUMN_ORDER_DATE + " = '" + orderdate1 + "' group by material_code";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String materialname = c.getString(c.getColumnIndex(COLUMN_MATERIAL_NAME));
                String materialcode = c.getString(c.getColumnIndex(COLUMN_MATERIAL_CODE));
                String quantity = c.getString(c.getColumnIndex(COLUMN_QUANTITY));

                orderpreview.add(new RouteDayStartDatasource(materialname, materialcode, quantity));
            } while (c.moveToNext());
        }
        c.close();
        return orderpreview;
    }

    public String removeDuplicateComma(String customerAddress) {
        String[] functions = customerAddress.split(",");

        List<String> uniqueFunctions = new ArrayList<>();

        for (String function : functions) {
//            Log.e(TAG, "getCustomerListDB: function " + function.trim());
            if (!function.equals("")) {
                if (!function.equals("null")) {
                    uniqueFunctions.add(function.trim());
//                    Log.e(TAG, "getCustomerListDB: uniqueFunctions " + uniqueFunctions);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return String.join(",", uniqueFunctions);
        } else {
            return uniqueFunctions.toString();
        }
    }

    //update Online status
    public void updateOnlineStatus(String tableName, String columnName, String value1,
                                   String columnName2, String value2, String isonline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IS_ONLINE, isonline);
        db.update(tableName, contentValues, columnName + "=? and " + columnName2 + "=?", new String[]{value1, value2});
    }
}

