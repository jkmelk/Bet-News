package com.example.betnews.data.locale.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.betnews.data.locale.db.dao.NewsDao
import com.example.betnews.data.locale.db.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}