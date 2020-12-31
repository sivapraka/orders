package com.demo.orders.db.table

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency")
class CurrencyTable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    var id: String? = ""

    @SerializedName("company_code")
    @Expose
    var company_code: String? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("web")
    @Expose
    var web: String? = null
}