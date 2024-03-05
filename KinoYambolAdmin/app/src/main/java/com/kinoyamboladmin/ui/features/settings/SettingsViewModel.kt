package com.kinoyamboladmin.ui.features.settings

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.LanguageRepository
import com.kinoyamboladmin.data.SettingsRepository
import com.kinoyamboladmin.data.ThemeRepository
import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Settings
import com.kinoyamboladmin.models.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val languageRepository: LanguageRepository,
    private val themeRepository: ThemeRepository,
    private val settingsRepository: SettingsRepository
): ViewModel() {
    var languages by mutableStateOf(emptyList<Language>())
    var themes by mutableStateOf(emptyList<Theme>())
    var userSettingsState: SettingsUiState? by mutableStateOf(SettingsUiState())

    private suspend fun getLanguages(): List<Language> = languageRepository.get().toList()
    private suspend fun getThemes(): List<Theme> = themeRepository.get().toList()

    private fun loadLanguages() {
        viewModelScope.launch {
            languages = getLanguages()
        }
    }

    private fun loadThemes() {
        viewModelScope.launch {
            themes = getThemes()
        }
    }

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
        loadLanguages()
        loadThemes()
        loadUserSettings()
    }

    fun onSettingsEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.OnChangeLanguage -> {
                viewModelScope.launch {
                    settingsRepository.saveLanguage(event.language)
                }
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