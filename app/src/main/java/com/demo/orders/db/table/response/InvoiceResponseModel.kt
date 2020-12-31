package com.demo.orders.db.table.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.demo.orders.model.InvoiceDataSource

class InvoiceResponseModel {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<InvoiceDataSource>? = null

}