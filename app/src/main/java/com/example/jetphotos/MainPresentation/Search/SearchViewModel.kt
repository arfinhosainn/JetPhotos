package com.example.jetphotos.MainPresentation.Search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetphotos.data.dto.Unsplash
import com.example.jetphotos.data.repository.UnsplashRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: UnsplashRepositoryImpl
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val search = _searchQuery


    private val _searchImages = MutableStateFlow<PagingData<Unsplash>>(PagingData.empty())
    val searchImages = _searchImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            repository.searchImages(query = query).cachedIn(viewModelScope).collect {
                _searchImages.value = it
            }
        }
    }

}