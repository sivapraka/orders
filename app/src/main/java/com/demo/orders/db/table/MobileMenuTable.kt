package com.demo.orders.db.table

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mobile_menu")
class MobileMenuTable {

    var photo: Int = 0


    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    var id: String? = ""

    @SerializedName("screen_type")

    @Expose
    var screenType: String? = null

    @SerializedName("screen_name")
    @Expose
    var screenName: String? = null

    @SerializedName("screen_value")
    @Expose
    var screenValue: String? = null

    @SerializedName("image_id")
    @Expose
    var imageId: String? = null

    @SerializedName("language_code")
    @Expose
    var languageCode: String? = null

    @SerializedName("screen_status")
    @Expose
    var screenStatus: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("created_date")
    @Expose
    var createdDate: String? = null

    @SerializedName("company_id")
    @Expose
    var companyCode: String? = null

    @SerializedName("salesman_code")
    @Expose
    var salesmanCode: String? = null

    @SerializedName("salesorg_code")
    @Expose
    var salesorgCode: String? = null

    @SerializedName("salesarea_code")
    @Expose
    var salesareaCode: String? = null

    @SerializedName("plant_code")
    @Expose
    var plantCode: String? = null

}