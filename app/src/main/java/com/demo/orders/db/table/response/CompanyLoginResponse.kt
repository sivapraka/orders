package com.wizesales.visitormanagent.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.demo.orders.db.table.Company
import com.demo.orders.db.table.MobileMenuTable

class CompanyLoginResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("baseurl")
    @Expose
    var baseurl: String? = null

    @SerializedName("logo")
    @Expose
    var logo: String? = null

    @SerializedName("company_name")
    @Expose
    var companyName: String? = null

    @SerializedName("unit")
    @Expose
    var units: List<UnitTable>? = null

    @SerializedName("data")
    @Expose
    var mobile_menu: List<MobileMenuTable>? = null

    @SerializedName("company")
    @Expose
    var company: List<Company>? = null

    @SerializedName("customer_online_payment")
    @Expose
    var payment: String? = null
}
