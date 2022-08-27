package com.example.jetphotos.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetphotos.data.dto.Unsplash
import kotlinx.coroutines.flow.Flow


@Dao
interface UnsplashPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photo: List<Unsplash>)

    @Query("SELECT * FROM photo_entity")
    fun getAllImages(): PagingSource<Int, Unsplash>

    @Query("DELETE FROM photo_entity")
    suspend fun deleteAllImages()

}