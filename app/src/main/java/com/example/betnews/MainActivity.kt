package com.example.betnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.betnews.framgment.news.adapter.NewsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_pager.adapter = NewsPagerAdapter(this)
        view_pager.offscreenPageLimit = 2
        supportActionBar?.elevation = 0f
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_news)
                else -> tab.setIcon(R.drawable.ic_favorite_icon)
            }
        }.attach()
    }
}