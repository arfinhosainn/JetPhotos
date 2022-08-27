package com.example.jetphotos.di

import android.app.Application
import androidx.room.Room
import com.example.jetphotos.data.database.PhotosDatabase
import com.example.jetphotos.data.network.UnsplashApi
import com.example.jetphotos.data.repository.UnsplashRepositoryImpl
import com.example.jetphotos.domain.repository.UnsplashRepository
import com.example.jetphotos.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(
        api: UnsplashApi,
        db: PhotosDatabase
    ): UnsplashRepository {
        return UnsplashRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun providesPhotoDatabase(app: Application): PhotosDatabase {
        return Room.databaseBuilder(
            app, PhotosDatabase::class.java, "photo_database"
        )
            .build()
    }

}