package com.wizesales.visitormanagent.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.demo.orders.db.table.OrdersDataSourceTable

class OderNewResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<OrdersDataSourceTable>? = null


}
