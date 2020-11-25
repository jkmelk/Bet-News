package com.example.betnews.data.maper

import com.example.betnews.data.locale.db.entity.NewsEntity
import com.example.betnews.data.models.response.NewsResult

fun NewsResult.toNews(): NewsEntity {
    return NewsEntity(id, sectionName, webTitle, webUrl, webPublicationDate)
}