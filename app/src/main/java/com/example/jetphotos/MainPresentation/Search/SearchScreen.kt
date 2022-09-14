package com.example.jetphotos.MainPresentation.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetphotos.MainPresentation.Home.component.ImageListScreen
import com.example.jetphotos.MainPresentation.Home.component.SearchBar
import com.example.jetphotos.presentation.navigation.Screen
import com.example.jetphotos.ui.theme.DarkGray

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val searchQuery by searchViewModel.search


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
    ) {
        Column {
            SearchBar(
                hint = "Search...",
                modifier = Modifier.fillMaxWidth(),
                state = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                }
            )
            ImageListScreen(images = searchViewModel.searchImages, onImageClick = {
                navController.navigate(Screen.Detail.route + "/${it.id}")
            })
        }


    }
}
