package com.example.jetphotos.util

import com.example.jetphotos.data.dto.Unsplash

data class ImageListState(
    val isLoading: Boolean = false,
    val images: List<Unsplash> = emptyList() ,
    val error: String = ""
)
