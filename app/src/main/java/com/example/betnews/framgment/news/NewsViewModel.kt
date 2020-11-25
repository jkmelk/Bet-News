package com.example.betnews.framgment.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.betnews.api.API_KEY
import com.example.betnews.base.BaseViewModel
import com.example.betnews.data.locale.db.entity.NewsEntity
import com.example.betnews.data.models.response.NewsResponse
import kotlinx.coroutines.launch

class NewsViewModel(private val repo: NewsRepo) : BaseViewModel() {

    var resultLiveData: LiveData<List<NewsEntity>> = repo.getLocaleNews()
    val responseLiveData = MutableLiveData<NewsResponse>()
    lateinit var favoriteLiveData: LiveData<List<NewsEntity>>

    fun getNews(page: Int, pageSize: Int) {
        viewModelScope.launch {
            manageCall(repo.getRemoteNews(API_KEY, page, pageSize)) { newsResponse ->
                responseLiveData.value = newsResponse
            }
        }
    }

    fun getFavoriteNews() = viewModelScope.launch {
        val favorites = repo.getFavorites()
        favoriteLiveData = favorites
    }

    fun makeFavorite(newsEntity: NewsEntity) = viewModelScope.launch {
        repo.updateFavoriteStatus(newsEntity)
    }
}