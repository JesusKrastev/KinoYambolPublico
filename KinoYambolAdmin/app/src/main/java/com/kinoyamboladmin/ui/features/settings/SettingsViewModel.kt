package com.kinoyamboladmin.ui.features.settings

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.SettingsRepository
import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.SettingsValues
import com.kinoyamboladmin.utilities.texts.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    application: Application,
    private val settingsRepository: SettingsRepository
): AndroidViewModel(application) {
    var languages by mutableStateOf(SettingsValues.languages)
    var themes by mutableStateOf(SettingsValues.themes)
    var userSettingsState: SettingsUiState? by mutableStateOf(SettingsUiState())
    private val context: Context = getApplication<Application>().applicationContext

    private fun loadUserSettings() {
        viewModelScope.launch {
            settingsRepository.settingsFlow.collect { settings ->
                userSettingsState = SettingsUiState(
                    language = settings.language,
                    theme = settings.theme
                )
            }
        }
    }

    init {
        loadUserSettings()
    }

    private fun changeLanguage(language: String) {
        viewModelScope.launch {
            settingsRepository.saveLanguage(language)
            // Restart the app to apply the new language
            context.packageManager.getLaunchIntentForPackage(context.packageName)?.let { intent ->
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
            }
        }
    }

    fun onSettingsEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.OnChangeLanguage -> {
                if(userSettingsState?.language != event.language) changeLanguage(event.language)
            }
            is SettingsEvent.OnChangeTheme -> {
                viewModelScope.launch {
                    settingsRepository.saveTheme(event.theme)
                }
            }
            is SettingsEvent.OnClickPrivacyPolicies -> {

            }
            is SettingsEvent.OnClickTermsAndConditions -> {

            }
        }
    }
}