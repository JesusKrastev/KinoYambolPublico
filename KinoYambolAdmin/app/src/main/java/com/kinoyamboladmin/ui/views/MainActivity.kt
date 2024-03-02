package com.kinoyamboladmin.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kinoyamboladmin.ui.features.seemovies.MoviesListViewModel
import com.kinoyamboladmin.ui.navigation.MovieNavHost
import com.kinoyamboladmin.ui.theme.KinoYambolAdminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinoYambolAdminTheme(
                darkTheme = true
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MovieNavHost()
                }
            }
        }
    }
}