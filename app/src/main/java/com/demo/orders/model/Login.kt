package com.demo.orders.model

class Login {
    lateinit var mobile: String
    lateinit var mpin: String
    lateinit var type: String

    constructor(mbe: String, mpn: String, type: String) {
        this.mobile = mbe
        this.mpin = mpn
        this.type = type
    }

    constructor(mobile: String, mpin: String) {
        this.mobile = mobile
        this.mpin = mpin
    }

}