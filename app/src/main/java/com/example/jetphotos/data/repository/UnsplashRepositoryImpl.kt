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
import com.example.jetphotos.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    private val db: PhotosDatabase
) : UnsplashRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getListOfImages(): Flow<PagingData<Unsplash>> {
        return Pager(config = PagingConfig(
            pageSize = ITEM_PER_PAGE,
        ), remoteMediator = PhotoRemoteMediator(
            api = api,
            db = db
        ),
            pagingSourceFactory = {
                db.unsplashPhotoDao.getAllImages()
            }
        ).flow
    }


    override fun getPhotoById(id: String): Flow<Resource<Unsplash>> = flow {
        emit(Resource.Loading())
        try {
            val image = api.getPhotoById(id = id)
            emit(Resource.Success(image))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }

    fun searchImages(query: String): Flow<PagingData<Unsplash>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ), pagingSourceFactory = {
                SearchPagingSource(api = api, query = query)
            }
        ).flow
    }

}







