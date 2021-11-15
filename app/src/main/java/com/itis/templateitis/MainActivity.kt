package com.itis.templateitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var service: NotificationService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etText = findViewById<EditText>(R.id.et_text)

        service = NotificationService(this)

        findViewById<Button>(R.id.btn_notify).setOnClickListener {
            service?.showNotification(this, etText.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }
}
