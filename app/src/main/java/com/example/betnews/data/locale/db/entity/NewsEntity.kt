package com.example.betnews.data.locale.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey
    var id: String,
    val sectionName: String,
    val title: String,
    val webUrl: String,
    val publicationDate: String
) {
    var entityId: Int = 0
    var isFavorite: Boolean = false
}