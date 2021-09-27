package com.itis.templateitis

import android.util.Log

open class UserKt constructor(
    val email: String,
) {

    var phone: String = ""
        get() {
            return "+7"
        }
        set(value) {
            field = "+7 $value"
        }

    constructor(
        email: String,
        phone: String
    ) : this(email) {
        this.phone = phone
    }

    init {
        Log.e("UserJava", email)
    }
}
