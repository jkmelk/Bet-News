package com.example.betnews.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestServiceClient {

    private const val TIMEOUT_SECONDS = 20L

    fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    inline fun <reified S> createService(baseUrl: String): S {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getHttpClient())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(S::class.java)
    }
}