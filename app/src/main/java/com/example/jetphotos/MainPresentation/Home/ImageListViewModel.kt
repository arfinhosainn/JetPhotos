package com.example.jetphotos.MainPresentation.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jetphotos.domain.repository.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val repository: UnsplashRepository
) : ViewModel() {
    val getImageList = repository.getListOfImages().cachedIn(viewModelScope)
}