package com.example.jetphotos.MainPresentation.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetphotos.domain.repository.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val repository: UnsplashRepository
) : ViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh: StateFlow<Boolean> = _isRefresh


    val getAllImages = repository.getImages()

    fun refreshed(){
        viewModelScope.launch {
            _isRefresh.emit(true)
            getAllImages
            _isRefresh.emit(false)
        }
    }

}