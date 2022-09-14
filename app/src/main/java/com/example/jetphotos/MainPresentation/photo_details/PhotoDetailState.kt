package com.example.jetphotos.MainPresentation.photo_details

import com.example.jetphotos.data.dto.Unsplash

data class PhotoDetailState (
    val isLoading: Boolean = false,
    val image : Unsplash?  = null,
    val error: String = "",
        )


