package com.demo.orders.db.table

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "scheme_master")
class SchemeMasterTable {

    @SerializedName("scheme_code")
    @Expose
    var schemeCode: String? = null

    @SerializedName("scheme_name")
    @Expose
    var schemeName: String? = null

    @SerializedName("scheme_type")
    @Expose
    var schemeType: String? = null

    @SerializedName("material_code1")
    @Expose
    var materialCode1: String? = null

    @SerializedName("material_code2")
    @Expose
    var materialCode2: String? = null

    @SerializedName("quantity")
    @Expose
    var quantity: String? = null

    @SerializedName("free_quantity")
    @Expose
    var freeQuantity: String? = null

    @SerializedName("scheme")
    @Expose
    var scheme: String? = null

    @SerializedName("scheme_from")
    @Expose
    var schemeFrom: String? = null

    @SerializedName("scheme_till")
    @Expose
    var schemeTill: String? = null

    @SerializedName("last_modified")
    @Expose
    var lastModified: String? = null

}