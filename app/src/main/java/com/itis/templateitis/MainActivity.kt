package com.itis.templateitis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.navOptions

private const val TAG_HOME = "tag_home"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // old way:
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.also {

            }
        }
    }*/

    // NOW
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.also {

            }
        }
    }
    // Внутри у ActivityResultContracts есть много готовых уже контрактов,
    // StartActivityForResult - стандартный для общения между активити

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = findController(R.id.container)

        val data = arrayListOf<Int>(
            R.id.action_homeFragment_to_userFragment,
            R.id.action_homeFragment_to_paymentFragment,
            R.id.action_userFragment_to_paymentFragment
        )

        findViewById<Button>(R.id.btn_next).run {
            setOnClickListener {
                val options = navOptions {
                    launchSingleTop = false
                    anim {
                        enter = R.anim.enter_from_right
                        exit = R.anim.exit_to_right
                        popEnter = R.anim.fade_in
                        popExit = R.anim.fade_out
                    }
                }

                val destination = HomeFragmentDirections.actionHomeFragmentToUserFragment(
                    "HELLO", true
                )
                controller.navigate(
                    destination,
                    options
                )

//                controller.navigate(
//                    R.id.action_homeFragment_to_userFragment,
//                    HomeFragment.bundleArgs(5),
//                )



                // This way is deprecated:
                // startActivityForResult(Intent(this@MainActivity, SecondActivity::class.java), 1)

                // should use:
                startForResult.launch(
                    Intent(this@MainActivity, SecondActivity::class.java)
                )
            }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            replace(R.id.container, fragment)
            addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }
}
