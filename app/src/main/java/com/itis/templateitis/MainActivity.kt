package com.itis.templateitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var b: UserKt = UserKt("sds")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_text).apply {
            safeClickListener(
                600,
                onError = {

                },
                onSuccess = { test, text, b ->
                    print(test)
                })
        }

        val delay = 600
        findViewById<TextView>(R.id.tv_text).apply {
            setOnClickListener {
                if (delay > 500) {
                    print("test")
                } else {
//                    sdsfdsdf
                }
            }
        }

        val myCallback: MyCallback = Student(
            "ema",
            4
        )



        run {
            listOf(1, 2, 3, 4, 5, 6).forEach { value ->
                if (value == 5) return@forEach
                println("Test: $value")
            }
        }
        println("onCreate")
    }

    fun test(color: String): Int = when (color) {
        "Red" -> 3
        "Green", "Black" -> 5
        "Orange" -> {
            111111 * color.length
        }

        else -> 1
    }

    fun test2(color: String): Int = when {
        color.isEmpty() and true -> 4
        color.length < 5 -> 5
        else -> 1
    }
}
