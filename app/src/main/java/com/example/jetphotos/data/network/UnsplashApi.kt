package com.example.jetphotos.data.network

import com.example.jetphotos.BuildConfig
import com.example.jetphotos.data.dto.SearchResult
import com.example.jetphotos.data.dto.Unsplash
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): List<Unsplash>


    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("per_page") per_page: Int,
    ): SearchResult


    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: String
    ): Unsplash

}