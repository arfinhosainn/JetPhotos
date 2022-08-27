package com.example.jetphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.jetphotos.MainPresentation.Home.HomeScreen
import com.example.jetphotos.presentation.navigation.SetupNavGraph
import com.example.jetphotos.ui.theme.JetPhotosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPhotosTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}

