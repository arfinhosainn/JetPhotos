package com.example.jetphotos.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jetphotos.data.database.PhotosDatabase
import com.example.jetphotos.data.dto.Unsplash
import com.example.jetphotos.data.dto.UnsplashRemoteKeys
import com.example.jetphotos.data.network.UnsplashApi
import com.example.jetphotos.util.Constants.ITEM_PER_PAGE
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator @Inject constructor(
    private val api: UnsplashApi,
    private val db: PhotosDatabase
) : RemoteMediator<Int, Unsplash>() {

    private val photosDao = db.unsplashPhotoDao
    private val unsplashRemoteKeysDao = db.unsplashRemoteKeysDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Unsplash>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1

                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }
            val response = api.getAllImages(page = currentPage, per_page = ITEM_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    photosDao.deleteAllImages()
                    unsplashRemoteKeysDao.deleteRemoteKeys()
                }
                photosDao.insertPhotos(response)
                val keys = response.map { unsplash ->
                    UnsplashRemoteKeys(
                        id = unsplash.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                unsplashRemoteKeysDao.addAllRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Unsplash>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { unsplashImage ->
            unsplashRemoteKeysDao.getRemoteKeys(unsplashImage.id)

        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Unsplash>
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKeys(id = id)

            }

        }
    }


    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Unsplash>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeysDao.getRemoteKeys(unsplashImage.id)
            }
    }

}