package com.example.betnews.framgment.news.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.betnews.data.locale.db.entity.NewsEntity

class DiffUtilsItemCallBack<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        if(oldItem is NewsEntity && newItem is NewsEntity){
            return oldItem.id == newItem.id && oldItem.isFavorite == newItem.isFavorite
        }
        return true
    }
}