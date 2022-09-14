package com.example.jetphotos.util

import androidx.paging.PagingData
import com.example.jetphotos.data.dto.Unsplash
import kotlinx.coroutines.flow.Flow

data class ImageListState(
    val isLoading: Boolean = false,
    val images: Flow<PagingData<Unsplash>>? = null ,
    val error: String = ""
)
