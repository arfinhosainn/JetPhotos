package com.example.jetphotos.MainPresentation.photo_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetphotos.MainPresentation.photo_details.component.DetailScreenItem

@Composable
fun PhotoDetailScreen(
    photoDetailViewModel: PhotoDetailViewModel = hiltViewModel()
) {

    val state = photoDetailViewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        state.image?.let { image ->
            DetailScreenItem(unsplash = image)
        }
    }
}