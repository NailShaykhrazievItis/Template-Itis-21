package com.itis.templateitis

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Process
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var binder: MusicService.LocaleBinder? = null

    private var binderAidl: ISongInterface? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binder = service as? MusicService.LocaleBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    private val connectionAidl = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binderAidl = ISongInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binderAidl = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tvHello)


        bindService(
            Intent(this, MusicService::class.java),
            connectionAidl,
            BIND_AUTO_CREATE
        )

        tv.setOnClickListener {
//            binder?.sum(3, 4)

            val sum = binderAidl?.sum(5, 10)
            val id = binderAidl?.processId()

            binderAidl?.play()

            Log.e("Tag", "Result: $sum")

            Log.e("Process", "Service Process: $id")
            Log.e("Process", "Current Process: ${Process.myPid()}")
        }
    }
}
