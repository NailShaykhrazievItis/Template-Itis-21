package com.itis.templateitis

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

 fun View.safeClickListener(
    delay: Int,
    onSuccess: (Int, String, Boolean) -> Unit
    crossinline onError: (Throwable) -> Unit
) {

    onSuccess(delay, "Hello", true)

   setOnClickListener {
       if (delay > 500) {
           onSuccess(delay, "Hello", true)
       } else {
           onError(Exception("test"))
       }
   }
}

