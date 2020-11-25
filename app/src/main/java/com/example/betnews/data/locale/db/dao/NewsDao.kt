package com.example.betnews.data.locale.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.betnews.data.locale.db.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewsEntity>): List<Long>

    @Query("SELECT * FROM NewsEntity ")
    fun getNews(): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE isFavorite = 1")
    fun getFavorites(): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE isFavorite = 1")
    suspend fun getFavoritesAsync(): List<NewsEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavoriteStatus(news: NewsEntity)
}