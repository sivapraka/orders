package com.demo.orders.db.table

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "invoice")
class InvoiceModel {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    var id: String? = ""

    @SerializedName("erp_id")
    @Expose
    var erpId: String? = null

    @SerializedName("tripID")
    @Expose
    var tripID: String? = null

    @SerializedName("bill_no")
    @Expose
    var billNo: String? = null

    @SerializedName("bill_date")
    @Expose
    var billDate: String? = null

    @SerializedName("customer_code")
    @Expose
    var customerCode: String? = null

    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("company_code")
    @Expose
    var companyCode: String? = null

    @SerializedName("plant_code")
    @Expose
    var plantCode: String? = null

    @SerializedName("salesorg_code")
    @Expose
    var salesorgCode: String? = null

    @SerializedName("salesarea_code")
    @Expose
    var salesareaCode: String? = null

    @SerializedName("supervisor_code")
    @Expose
    var supervisorCode: String? = null

    @SerializedName("item_count")
    @Expose
    var itemCount: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null

    @SerializedName("sub_total")
    @Expose
    var subTotal: String? = null

    @SerializedName("discount")
    @Expose
    var discount: String? = null

    @SerializedName("tax")
    @Expose
    var tax: String? = null

    @SerializedName("grand_total")
    @Expose
    var grandTotal: String? = null

    @SerializedName("round_up")
    @Expose
    var roundUp: String? = null

    @SerializedName("net_amount")
    @Expose
    var netAmount: String? = null

    @SerializedName("received")
    @Expose
    var received: String? = null

    @SerializedName("balance")
    @Expose
    var balance: String? = null

    @SerializedName("bill_status")
    @Expose
    var billStatus: String? = null

    @SerializedName("comments")
    @Expose
    var comments: String? = null

    @SerializedName("manual_billing")
    @Expose
    var manualBilling: String? = null

    @SerializedName("randomkey")
    @Expose
    var randomkey: String? = null

    @SerializedName("bill_key")
    @Expose
    var billKey: String? = null

    @SerializedName("manual_bill_no")
    @Expose
    var manualBillNo: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("entered_by")
    @Expose
    var enteredBy: String? = null

    @SerializedName("created_on")
    @Expose
    var createdOn: String? = null

    @SerializedName("modified_by")
    @Expose
    var modifiedBy: String? = null

    @SerializedName("modified_on")
    @Expose
    var modifiedOn: String? = null

    @SerializedName("bill_id")
    @Expose
    var billId: String? = null

    @SerializedName("sgst_tax")
    @Expose
    var sgstTax: String? = null

    @SerializedName("cgst_tax")
    @Expose
    var cgstTax: String? = null

    @SerializedName("igst_tax")
    @Expose
    var igstTax: String? = null

    @SerializedName("total_tax")
    @Expose
    var totalTax: String? = null

    @ColumnInfo(name = "isOnline")
    var isOnline: Boolean = false

}
