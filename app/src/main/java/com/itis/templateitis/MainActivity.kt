package com.itis.templateitis

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.model.AppDatabase
import com.itis.template.model.entity.User
import kotlinx.coroutines.*

typealias User = Pair<String, Map<String, List<Int>>>

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Default
    )
    private val dao by lazy {
        AppDatabase(this).userDao()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_test)

        scope.launch {
            val user = dao.getUserById(1)
            withContext(Dispatchers.Main) {
                textView.text = user?.name
            }

            withContext(Dispatchers.IO) {
                dao.getUserById(1)
            }?.also {
                withContext(Dispatchers.Main) {
                    textView.text = it.name
                }
            }


//            rv.adapter = adapter.submitList(users)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
 }
