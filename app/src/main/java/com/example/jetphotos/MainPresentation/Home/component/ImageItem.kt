package com.example.jetphotos.MainPresentation.Home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetphotos.R
import com.example.jetphotos.data.dto.Unsplash

@Composable
fun ImageItem(
    unsplash: Unsplash,
    onImageClick: (Unsplash) -> Unit
) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(200.dp)
            .padding(5.dp),
        backgroundColor = Color.Green,
        shape = RoundedCornerShape(10.dp)
    ) {

        AsyncImage(
            model = unsplash.urls.regular,
            placeholder = painterResource(R.drawable.ic_baseline_image_24),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onImageClick(unsplash)
                },
            contentScale = ContentScale.FillBounds
        )
    }

}