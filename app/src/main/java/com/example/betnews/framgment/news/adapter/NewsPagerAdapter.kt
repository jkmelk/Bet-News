package com.example.betnews.framgment.news.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.betnews.framgment.news.NewsFragment
import com.example.betnews.framgment.news.favorits.FavoriteFragment

class NewsPagerAdapter(act: AppCompatActivity) : FragmentStateAdapter(act) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> NewsFragment()
        else -> FavoriteFragment()
    }
}