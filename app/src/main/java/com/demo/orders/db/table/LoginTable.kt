package com.demo.orders.db.table

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "login")
class LoginTable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    var id: String? = ""

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

    @SerializedName("customer_code")
    @Expose
    var customerCode: String? = null

    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("street")
    @Expose
    var street: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("region")
    @Expose
    var region: String? = null

    @SerializedName("postal_code")
    @Expose
    var postalCode: String? = null

    @SerializedName("tin_cst")
    @Expose
    var tinCst: String? = null

    @SerializedName("price_code")
    @Expose
    var priceCode: String? = null

    @SerializedName("material_code")
    @Expose
    var materialCode: String? = null

    @SerializedName("scheme_code")
    @Expose
    var schemeCode: String? = null

    @SerializedName("asm_name")
    @Expose
    var asmName: String? = null

    @SerializedName("aso_name")
    @Expose
    var asoName: String? = null

    @SerializedName("phone_num")
    @Expose
    var phoneNum: String? = null

    @SerializedName("contact_person")
    @Expose
    var contactPerson: String? = null

    @SerializedName("mail")
    @Expose
    var mail: String? = null

    @SerializedName("mobile_num")
    @Expose
    var mobileNum: String? = null

    @SerializedName("bank_key")
    @Expose
    var bankKey: String? = null

    @SerializedName("bank_account")
    @Expose
    var bankAccount: String? = null

    @SerializedName("account_holder")
    @Expose
    var accountHolder: String? = null

    @SerializedName("deposit")
    @Expose
    var deposit: String? = null

    @SerializedName("tray_type")
    @Expose
    var trayType: String? = null

    @SerializedName("card_num")
    @Expose
    var cardNum: String? = null

    @SerializedName("gift")
    @Expose
    var gift: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("loyalty")
    @Expose
    var loyalty: String? = null

    @SerializedName("scheme_discount")
    @Expose
    var schemeDiscount: String? = null

    @SerializedName("slab")
    @Expose
    var slab: String? = null

    @SerializedName("ledger_balance")
    @Expose
    var ledgerBalance: String? = null

    @SerializedName("loyalty_points")
    @Expose
    var loyaltyPoints: String? = null

    @SerializedName("entered_by")
    @Expose
    var enteredBy: String? = null

    @SerializedName("created_on")
    @Expose
    var createdOn: String? = null

    @SerializedName("last_modified")
    @Expose
    var lastModified: String? = null

    @SerializedName("modified_by")
    @Expose
    var modifiedBy: String? = null

    @SerializedName("modified_on")
    @Expose
    var modifiedOn: String? = null

    @SerializedName("erp_id")
    @Expose
    var erpId: String? = null

    @SerializedName("profile")
    @Expose
    var profile: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("mpin")
    @Expose
    var mpin: String? = null

    @SerializedName("otp_validiity")
    @Expose
    var otpValidiity: String? = null

    @SerializedName("otp")
    @Expose
    var otp: String? = null

    @SerializedName("isLogin")
    @Expose
    var uisLogin: String? = null

    @SerializedName("otp_status")
    @Expose
    var otpStatus: String? = null

}