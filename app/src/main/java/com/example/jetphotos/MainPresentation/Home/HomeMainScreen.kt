package com.example.jetphotos.MainPresentation.Home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetphotos.MainPresentation.Home.component.BottomNavigationBar
import com.example.jetphotos.MainPresentation.Home.component.DefaultAppBar
import com.example.jetphotos.MainPresentation.Home.component.ImageListScreen
import com.example.jetphotos.R
import com.example.jetphotos.presentation.navigation.Screen
import com.example.jetphotos.util.BottomNavItem

@Composable
fun HomeMainScreen(
    imageListViewModel: ImageListViewModel = hiltViewModel(),
    navController: NavHostController
) {
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
                        route = Screen.Detail.route,
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
        ImageListScreen(images = imageListViewModel.getImageList, onImageClick = {

        })


    }
    }
