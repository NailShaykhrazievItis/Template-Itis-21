package com.itis.templateitis.utils

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable

fun EditText.observeText(): Flowable<String> = Flowable.create({ emitter ->
    addTextChangedListener {
        if (it != null)
            emitter.onNext(it.toString())
    }
}, BackpressureStrategy.LATEST)
