package com.example.betnews.data.models.response

data class NewsData(val response: NewsResponse)

data class NewsResponse(
    val status: String,
    val total: Int,
    val startIndex: String,
    val pageSize: Int,
    val currentPage: Int,
    val pages: Int,
    var results: MutableList<NewsResult>
)

data class NewsResult(
    val id: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String
)
