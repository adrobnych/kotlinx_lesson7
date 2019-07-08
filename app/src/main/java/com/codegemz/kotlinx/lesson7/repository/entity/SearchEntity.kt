package com.codegemz.kotlinx.lesson7.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val title: String,
    val pageid: Long,
    val snippet: String,
    val queryString: String
)