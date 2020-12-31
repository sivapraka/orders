package com.demo.orders.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "company")
class Company {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("customer_id")
    @Expose
    var customerId: String? = null

    @SerializedName("company_name")
    @Expose
    var companyName: String? = null

    @SerializedName("customer_contact_name")
    @Expose
    var customerContactName: String? = null

    @SerializedName("customer_email")
    @Expose
    var customerEmail: String? = null

    @SerializedName("customer_phoneno")
    @Expose
    var customerPhoneno: String? = null

    @SerializedName("address_1")
    @Expose
    var address1: String? = null

    @SerializedName("address_2")
    @Expose
    var address2: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("gst")
    @Expose
    var gst: String? = null

    @SerializedName("logo")
    @Expose
    var logo: String? = null

    @SerializedName("contact_person")
    @Expose
    var contactPerson: String? = null

    @SerializedName("designation")
    @Expose
    var designation: String? = null

    @SerializedName("person_phone")
    @Expose
    var personPhone: String? = null

    @SerializedName("person_email")
    @Expose
    var personEmail: String? = null

    @SerializedName("site_url")
    @Expose
    var siteUrl: String? = null

    @SerializedName("db_name")
    @Expose
    var dbName: String? = null

    @SerializedName("db_username")
    @Expose
    var dbUsername: String? = null

    @SerializedName("db_password")
    @Expose
    var dbPassword: String? = null

    @SerializedName("no_of_units")
    @Expose
    var noOfUnits: String? = null

    @SerializedName("installation_date")
    @Expose
    var installationDate: String? = null

    @SerializedName("expiry_date")
    @Expose
    var expiryDate: String? = null

    @SerializedName("edate")
    @Expose
    var edate: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("delflag")
    @Expose
    var delflag: String? = null

    @SerializedName("customer_online_payment")
    @Expose
    var customerOnlinePayment: String? = null

}