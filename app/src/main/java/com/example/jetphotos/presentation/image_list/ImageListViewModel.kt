package com.example.jetphotos.presentation.image_list

import androidx.lifecycle.ViewModel
import com.example.jetphotos.domain.repository.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val repository: UnsplashRepository
) : ViewModel() {
    val getAllImages = repository.getImages()

}