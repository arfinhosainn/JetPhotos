package com.example.jetphotos.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetphotos.data.PhotoRemoteMediator
import com.example.jetphotos.data.SearchPagingSource
import com.example.jetphotos.data.database.PhotosDatabase
import com.example.jetphotos.data.dto.Unsplash
import com.example.jetphotos.data.network.UnsplashApi
import com.example.jetphotos.domain.repository.UnsplashRepository
import com.example.jetphotos.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    private val db: PhotosDatabase
) : UnsplashRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getImages():  Flow<PagingData<Unsplash>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEM_PER_PAGE
            ),
            remoteMediator = PhotoRemoteMediator(
                api = api,
                db = db
            ),
            pagingSourceFactory = {
                db.unsplashPhotoDao.getAllImages()
            }
        ).flow
    }

    fun searchImages(query: String): Flow<PagingData<Unsplash>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ), pagingSourceFactory = {
                SearchPagingSource(api = api,query = query)
            }
        ).flow
    }


}