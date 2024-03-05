package com.kinoyamboladmin.ui.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.kinoyamboladmin.data.SettingsRepository
import com.kinoyamboladmin.ui.features.seemovies.MoviesListViewModel
import com.kinoyamboladmin.ui.navigation.MovieNavHost
import com.kinoyamboladmin.ui.theme.KinoYambolAdminTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var settingsRepository: SettingsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.Main) {
            settingsRepository.settingsFlow.collect { settings ->
                val theme: String = settings.theme
                setContent {
                    KinoYambolAdminTheme(
                        darkTheme = if(theme.contains("Sistema")) isSystemInDarkTheme() else theme.contains("Oscuro")
                    ) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            MovieNavHost()
                        }
                    }
                }
            }
        }
    }
}