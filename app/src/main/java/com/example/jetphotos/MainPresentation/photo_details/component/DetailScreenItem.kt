package com.example.jetphotos.MainPresentation.photo_details.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.jetphotos.data.dto.Unsplash

@Composable
fun DetailScreenItem(
    unsplash: Unsplash
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AsyncImage(model = unsplash.urls.regular,
            contentScale = ContentScale.FillBounds,
            contentDescription = "Photo Detail")
    }
}