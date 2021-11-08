package com.itis.templateitis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.itis.templateitis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var pagerAdapter: FragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.run {
            viewpager.adapter = FragmentAdapter(this@MainActivity).also {
                pagerAdapter = it
            }
            viewpager.setPageTransformer { page, position ->
                page.apply {
                    pivotX = if (position <= 0) width.toFloat() else 0.0f
                    pivotY = height * 0.5f
                    rotationY = 90f * position
                }
            }

            TabLayoutMediator(tab, viewpager) { tab, pos ->
                tab.text = "Title: $pos"
            }.attach()
        }
    }

    override fun onBackPressed() {
        binding?.run {
            if (viewpager.currentItem > 0) {
                viewpager.currentItem = viewpager.currentItem - 1
            } else {
                super.onBackPressed()
            }
        } ?: run {
            super.onBackPressed()
        }
    }

    inner class FragmentAdapter(
        activity: FragmentActivity
    ) : FragmentStateAdapter(activity) {

        private val colors = intArrayOf(
            android.R.color.white,
            android.R.color.holo_red_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_purple
        )

        override fun getItemCount(): Int = colors.size

        override fun createFragment(position: Int): Fragment = FirstFragment.newInstance(
            color = colors[position]
        )

    }
}
