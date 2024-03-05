package com.kinoyamboladmin.ui.features.settings

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.SettingsRepository
import com.kinoyamboladmin.models.SettingsValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    var languages by mutableStateOf(SettingsValues.languages)
    var themes by mutableStateOf(SettingsValues.themes)
    var userSettingsState: SettingsUiState? by mutableStateOf(SettingsUiState())

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