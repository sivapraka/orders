package com.demo.orders.db.table

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "review")
class FeedbackListTable : Serializable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    var id: String = ""

    @SerializedName("company_code")
    @Expose
    var companyCode: String? = null

    @SerializedName("salesorg_code")
    @Expose
    var salesorgCode: String? = null

    @SerializedName("salesarea_code")
    @Expose
    var salesareaCode: String? = null

    @SerializedName("material_code")
    @Expose
    var materialCode: String? = null

    @SerializedName("material_name")
    @Expose
    var materialName: String? = null

    @SerializedName("customer_code")
    @Expose
    var customerCode: String? = null

    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("feedback")
    @Expose
    var feedback: String? = null

    @SerializedName("datetime")
    @Expose
    var datetime: String? = null

    @SerializedName("customer_status")
    @Expose
    var customerStatus: String? = null

    @SerializedName("reply_feedback")
    @Expose
    var replyFeedback: String? = null

    @SerializedName("reply_datetime")
    @Expose
    var replyDatetime: String? = null

    @SerializedName("company_status")
    @Expose
    var companyStatus: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: Any? = null
}