package com.example.jetphotos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetphotos.converter.Converters
import com.example.jetphotos.data.dto.Unsplash
import com.example.jetphotos.data.dto.UnsplashRemoteKeys

@Database(entities = [Unsplash::class, UnsplashRemoteKeys::class], version = 1)
@TypeConverters(Converters::class)
abstract class PhotosDatabase : RoomDatabase() {

    abstract val unsplashPhotoDao: UnsplashPhotoDao
    abstract val unsplashRemoteKeysDao: UnsplashRemoteKeysDao
}