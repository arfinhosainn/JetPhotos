package com.example.jetphotos.data.dto

data class User(
    val links: LinksX,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String,
    val username: String
)