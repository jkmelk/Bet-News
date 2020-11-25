package com.example.betnews.api

import com.example.betnews.data.models.response.NewsData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsSearchCall {

    @GET(SEARCH)
    fun getNewsAsync(
        @Header("api-key") key: String ,
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int
    ): Deferred<NewsData>
}