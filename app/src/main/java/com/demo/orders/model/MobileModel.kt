package com.demo.orders.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MobileModel : Serializable {

    @SerializedName("company_id")
    lateinit var companyId: String

    @SerializedName("customer_id")
    lateinit var customerId: String

    @SerializedName("fcm_token")
    lateinit var fcmToken: String

    @SerializedName("imei_no")
    lateinit var imeiNo: String

    @SerializedName("model_no")
    lateinit var modelNo: String

    constructor(companyId: String, customerId: String, fcmToken: String, imeiNo: String, modelNo: String) {
        this.companyId = companyId
        this.customerId = customerId
        this.fcmToken = fcmToken
        this.imeiNo = imeiNo
        this.modelNo = modelNo
    }
}