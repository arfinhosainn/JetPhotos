package com.example.jetphotos.MainPresentation.photo_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetphotos.data.repository.UnsplashRepositoryImpl
import com.example.jetphotos.util.Constants
import com.example.jetphotos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle,
    private val repositoryImpl: UnsplashRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(PhotoDetailState())
    val state: State<PhotoDetailState> = _state


    init {
        savedStateHandle.get<String>(Constants.ID_PARAMS)?.let { id ->
            getImageById(id)
        }
    }


    private fun getImageById(id: String) {
        viewModelScope.launch {
            repositoryImpl.getPhotoById(id = id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = PhotoDetailState(image = result.data)
                    }
                    is Resource.Loading -> {
                        _state.value = PhotoDetailState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _state.value = PhotoDetailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }

            }
        }
    }


}