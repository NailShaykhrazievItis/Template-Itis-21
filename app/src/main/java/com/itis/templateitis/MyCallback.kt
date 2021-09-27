package com.itis.templateitis

import android.util.Log

interface MyCallback {

    fun test()

    fun testDefault() {
        Log.e("MyCallback", "${System.currentTimeMillis()}")
    }
}
