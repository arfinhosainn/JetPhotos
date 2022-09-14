package com.example.jetphotos.domain.repository

import androidx.paging.PagingData
import com.example.jetphotos.data.dto.Unsplash
import com.example.jetphotos.util.Resource
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {

    fun getListOfImages(): Flow<PagingData<Unsplash>>

    fun getPhotoById(id: String): Flow<Resource<Unsplash>>

}