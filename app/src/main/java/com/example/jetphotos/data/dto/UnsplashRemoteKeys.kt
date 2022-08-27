package com.example.jetphotos.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetphotos.util.Constants.REMOTE_MEDIATOR_TABLE

@Entity(tableName = REMOTE_MEDIATOR_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?

)
