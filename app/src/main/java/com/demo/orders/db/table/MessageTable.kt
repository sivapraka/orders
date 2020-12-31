package com.demo.orders.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "customer_message")
class MessageTable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("msg_id")
    @Expose
    var msgId: String? = null

    @SerializedName("customer_code")
    @Expose
    var customerCode: String? = null

    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("salesorg_code")
    @Expose
    var salesorgCode: String? = null

    @SerializedName("salesarea_code")
    @Expose
    var salesareaCode: String? = null

    @SerializedName("company_code")
    @Expose
    var companyCode: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("click_status")
    @Expose
    var clickStatus: String? = null

    @SerializedName("msg_status")
    @Expose
    var msgStatus: String? = null

    @SerializedName("datetime")
    @Expose
    var datetime: String? = null

    @SerializedName("doc_type")
    @Expose
    var docType: String? = null

    @SerializedName("doc_file")
    @Expose
    var docFile: String? = null


    constructor(msgId: String?, customerCode: String?, customerName: String?, salesorgCode: String?,
                salesareaCode: String?, companyCode: String?, title: String?, message: String?,
                clickStatus: String?, msgStatus: String?, datetime: String?, docType: String?, docFile: String?) {
        this.msgId = msgId
        this.customerCode = customerCode
        this.customerName = customerName
        this.salesorgCode = salesorgCode
        this.salesareaCode = salesareaCode
        this.companyCode = companyCode
        this.title = title
        this.message = message
        this.clickStatus = clickStatus
        this.msgStatus = msgStatus
        this.datetime = datetime
        this.docType = docType
        this.docFile = docFile
    }
}