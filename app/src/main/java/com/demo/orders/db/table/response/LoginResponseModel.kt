package com.demo.orders.db.table.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.demo.orders.db.table.CompanyMasterTable
import com.demo.orders.db.table.CurrencyTable
import com.demo.orders.db.table.LoginTable
import com.demo.orders.db.table.MobileMenuTable

class LoginResponseModel {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<LoginTable>? = null

    @SerializedName("mobile_menu")
    @Expose
    var mobile_menu: List<MobileMenuTable>? = null

    @SerializedName("currency")
    @Expose
    var currency: List<CurrencyTable>? = null

    @SerializedName("company")
    @Expose
    var company: List<CompanyMasterTable>? = null
}