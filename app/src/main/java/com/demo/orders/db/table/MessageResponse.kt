package com.demo.orders.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<MessageTable>? = null

}