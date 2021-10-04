package com.itis.templateitis

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.itis.templateitis.databinding.ActivityMainBinding

private const val REQUEST_CODE_1 = 123
private const val REQUEST_CODE_2 = 223

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding) {
            btnText.setOnClickListener {
//                openWebPage("https://google.com")

                startActivity(
                    Intent(
                        this@MainActivity,
                        MainActivity::class.java
                    ).apply {
                        putExtra("KEY", etMessage.text.toString())
                        putExtra("NUM", 12)
                    }
                )
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode != Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        when (requestCode) {
            REQUEST_CODE_1 -> {
                data?.extras?.getString("ASD")?.let {

                }
            }
            REQUEST_CODE_2 -> {

            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("onNewIntent", "HIIII")
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
