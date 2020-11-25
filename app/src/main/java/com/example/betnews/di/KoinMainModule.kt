package com.example.betnews.di

import androidx.room.Room
import com.example.betnews.api.DATABASE_NAME
import com.example.betnews.api.NewsApi
import com.example.betnews.api.NewsSearchCall
import com.example.betnews.api.RestServiceClient
import com.example.betnews.base.BaseRepository
import com.example.betnews.base.shared_data.view_model.SharedViewModel
import com.example.betnews.data.locale.db.NewsDatabase
import com.example.betnews.framgment.news.NewsRepo
import com.example.betnews.framgment.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Room.databaseBuilder(get(), NewsDatabase::class.java, DATABASE_NAME).build() }
    single { get<NewsDatabase>().newsDao() }
    single { SharedViewModel() }
    factory { NewsRepo(get(), get()) }
    single { NewsApi() }
    factory { (uri: String) -> RestServiceClient.createService<NewsSearchCall>(uri) }
    viewModel { NewsViewModel(get()) }
}