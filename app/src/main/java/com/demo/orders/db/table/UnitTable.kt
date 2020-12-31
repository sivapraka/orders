package com.wizesales.visitormanagent.db.table

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "unit")
class UnitTable {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("unit_name")
    @Expose
    var unitName: String? = null

    @SerializedName("created_date")
    @Expose
    var createdDate: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("company_id")
    @Expose
    var companyId: String? = null

}
