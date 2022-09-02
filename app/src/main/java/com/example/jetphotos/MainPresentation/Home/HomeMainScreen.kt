package com.example.jetphotos.MainPresentation.Home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetphotos.MainPresentation.Home.component.BottomNavigationBar
import com.example.jetphotos.MainPresentation.Home.component.DefaultAppBar
import com.example.jetphotos.MainPresentation.Home.component.ImageListScreen
import com.example.jetphotos.R
import com.example.jetphotos.presentation.navigation.Screen
import com.example.jetphotos.ui.theme.DarkGray
import com.example.jetphotos.util.BottomNavItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeMainScreen(
    imageListViewModel: ImageListViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val isRefreshing by imageListViewModel.isRefresh.collectAsState()


    Scaffold(
        topBar = {
            DefaultAppBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Screen.Home.route,
                        icon = painterResource(id = R.drawable.ic_home)
                    ),
                    BottomNavItem(
                        name = "Categories",
                        route = Screen.Search.route,
                        icon = painterResource(id = R.drawable.ic_home)
                    ),
                    BottomNavItem(
                        name = "Saved",
                        route = Screen.Saved.route,
                        icon = painterResource(id = R.drawable.ic_home)
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = Screen.Categories.route,
                        icon = painterResource(id = R.drawable.ic_home)
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGray)
        ) {
            Column {
                SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = {
                        imageListViewModel.refreshed()
                    }) {
                    ImageListScreen(images = imageListViewModel.getAllImages)
                }
            }

        }
    }

}