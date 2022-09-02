package com.example.jetphotos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetphotos.data.dto.SearchResult
import com.example.jetphotos.data.dto.Unsplash
import com.example.jetphotos.data.network.UnsplashApi
import com.example.jetphotos.util.Constants.ITEM_PER_PAGE

class SearchPagingSource(
    private val api: UnsplashApi,
    private val query: String

) : PagingSource<Int, Unsplash>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Unsplash> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.searchImages(query = query, per_page = 1000  )
            val endOfPaginationReached = response.results.isEmpty()
            if (response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
    override fun getRefreshKey(state: PagingState<Int, Unsplash>): Int? {
        return state.anchorPosition
    }
}