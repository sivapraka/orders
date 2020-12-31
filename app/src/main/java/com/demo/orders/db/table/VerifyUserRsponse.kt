package com.demo.orders.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyUserRsponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("baseurl")
    @Expose
    var baseurl: String? = null

    @SerializedName("company")
    @Expose
    var company: List<Company>? = null

    @SerializedName("data")
    @Expose
    var data: List<MobileMenuTable>? = null

    @SerializedName("customer")
    @Expose
    var customer: List<Customer>? = null

}