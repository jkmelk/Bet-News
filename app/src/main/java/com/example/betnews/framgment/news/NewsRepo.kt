package com.example.betnews.framgment.news

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.betnews.api.NewsApi
import com.example.betnews.base.BaseRepository
import com.example.betnews.data.locale.db.dao.NewsDao
import com.example.betnews.data.locale.db.entity.NewsEntity
import com.example.betnews.data.maper.toNews

class NewsRepo(private val api: NewsApi, private val dao: NewsDao) : BaseRepository() {

    suspend fun getRemoteNews(key: String, page: Int, pageSize: Int) = safeApiCall {
        val newsData = api.authenticationApi.getNewsAsync(key, page, pageSize).await()
        val newsEntityList = mutableListOf<NewsEntity>()
        val results = newsData.response.results
        val favorites = dao.getFavoritesAsync()
        results.forEach { res ->
            val element = res.toNews()
            favorites.forEach {
                if (it.id == element.id) {
                    element.isFavorite = true
                }
            }
            newsEntityList.add(element)
        }
        dao.insert(newsEntityList)

        return@safeApiCall newsData.response
    }

    fun getLocaleNews() = dao.getNews()

    fun getFavorites() = dao.getFavorites()

    suspend fun updateFavoriteStatus(newsEntity: NewsEntity) {
        newsEntity.isFavorite = newsEntity.isFavorite.not()
        dao.updateFavoriteStatus(newsEntity)
    }
}