package com.demo.orders.db.table

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "company_master")
class CompanyMasterTable : Serializable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    var id: String = ""

    @SerializedName("company_code")
    @Expose
    var companyCode: String? = null

    @SerializedName("company_name")
    @Expose
    var companyName: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("state_code")
    @Expose
    var stateCode: String? = null

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

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
}