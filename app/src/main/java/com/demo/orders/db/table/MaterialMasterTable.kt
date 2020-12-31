package com.demo.orders.db.table

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MaterialMasterTable {

    @PrimaryKey
    @SerializedName("material_code")
    @Expose
    var materialCode: String? = null

    @SerializedName("material_name")
    @Expose
    var materialName: String? = null

    @SerializedName("material_image")
    @Expose
    var materialImage: String? = null

    @SerializedName("uom")
    @Expose
    var uom: String? = null

    @SerializedName("taxdetails")
    @Expose
    var taxdetails: String? = null

    @SerializedName("net_weight")
    @Expose
    var netWeight: String? = null

    @SerializedName("gross_weight")
    @Expose
    var grossWeight: String? = null

    @SerializedName("company_code")
    @Expose
    var companyCode: String? = null

    @SerializedName("plant_code")
    @Expose
    var plantCode: String? = null

    @SerializedName("salesorg_code")
    @Expose
    var salesorgCode: String? = null

    @SerializedName("material_category")
    @Expose
    var materialCategory: String? = null

    @SerializedName("last_modified")
    @Expose
    var lastModified: String? = null

}