package com.kinoyamboladmin.ui.features.settings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.LanguageRepository
import com.kinoyamboladmin.data.SettingsRepository
import com.kinoyamboladmin.data.ThemeRepository
import com.kinoyamboladmin.models.Language
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
    var userSettingsState: SettingsUiState? by mutableStateOf(null)

    private suspend fun getLanguages(): List<Language> = languageRepository.get().toList()
    private suspend fun getThemes(): List<Theme> = themeRepository.get().toList()
    private suspend fun getUserSettings(): SettingsUiState = settingsRepository.get().map { it.toSettingsUiState() }.firstOrNull() ?: SettingsUiState(id = 1)

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
            userSettingsState = getUserSettings()
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
                userSettingsState = userSettingsState!!.copy(language = event.language)
                viewModelScope.launch {
                    settingsRepository.update(userSettingsState!!.toSettings())
                    loadUserSettings()
                }
            }
            is SettingsEvent.OnChangeTheme -> {
                userSettingsState = userSettingsState!!.copy(theme = event.theme)
                viewModelScope.launch {
                    settingsRepository.update(userSettingsState!!.toSettings())
                    loadUserSettings()
                }
            }
            is SettingsEvent.OnClickPrivacyPolicies -> {

            }
            is SettingsEvent.OnClickTermsAndConditions -> {

            }
            else -> {

            }
        }
    }
}