package com.example.jetphotos.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_entity")
data class Unsplash(
    @PrimaryKey
    val id: String,
    //val likes: Int,
    //val links: Links,
    val urls: Urls,
    //val user: User,
)