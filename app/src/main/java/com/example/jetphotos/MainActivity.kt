package com.example.jetphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.jetphotos.MainPresentation.Home.ImageListViewModel
import com.example.jetphotos.MainPresentation.Home.component.NavGraph
import com.example.jetphotos.ui.theme.JetPhotosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPhotosTheme {
                val navController = rememberNavController()

                    NavGraph(navHostController = navController)


                }


            }
        }
    }

