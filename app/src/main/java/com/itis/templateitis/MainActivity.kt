package com.itis.templateitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val TAG_HOME = "tag_home"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        supportFragmentManager.beginTransaction()
//            .add(R.id.one, HomeFragment())
//            .commit()

        supportFragmentManager.findFragmentByTag(TAG_HOME)?.let {

        }
        // A -> Home -> C -> f
        // A -> Home
        // A
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack(TAG_HOME, 1)
        supportFragmentManager.popBackStack(null, 1)

        supportFragmentManager.beginTransaction().run {
            add(R.id.one, UserFragment(), TAG_HOME)
            add(R.id.two, PaymentFragment())
            add(R.id.three, HomeFragment.newInstance(2))
//            1-2 параметр - > навигация вперед
            // 3-4 params -> навигация назад
//            setCustomAnimations()
            commit()
        }
    }
}
