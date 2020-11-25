package com.example.betnews.base.shared_data.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betnews.data.locale.db.entity.NewsEntity

class SharedViewModel : ViewModel() {
    val newsData = MutableLiveData<NewsEntity>()
}