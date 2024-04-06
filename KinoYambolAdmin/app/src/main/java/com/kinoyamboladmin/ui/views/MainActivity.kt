package com.kinoyamboladmin.ui.views

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.kinoyamboladmin.data.SettingsRepository
import com.kinoyamboladmin.models.SettingsValues
import com.kinoyamboladmin.ui.navigation.MovieNavHost
import com.kinoyamboladmin.ui.theme.KinoYambolAdminTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var settingsRepository: SettingsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.Main) {
            settingsRepository.settingsFlow.collect { settings ->
                setContent {
                    val theme: String = SettingsValues.themes[settings.theme]!!.asString()
                    val darkTheme: String = SettingsValues.themes["dark"]!!.asString()
                    val systemTheme: String = SettingsValues.themes["sys"]!!.asString()
                    val idLanguage: String = settings.language

                    changeLanguage(idLanguage)
                    KinoYambolAdminTheme(
                        darkTheme = if(theme.contains(systemTheme)) isSystemInDarkTheme() else theme.contains(darkTheme)
                    ) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            MovieNavHost()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun changeLanguage(idLanguage: String) {
        val language: String = SettingsValues.languages[idLanguage]!!.asString()
        val systemLanguage: String = SettingsValues.languages["sys"]!!.asString()
        val locale: Locale = if(language == systemLanguage) Locale.getDefault() else Locale(idLanguage)
        val resources: Resources = resources
        val configuration: Configuration = resources.configuration

        Locale.setDefault(locale)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}