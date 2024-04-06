package com.kinoyamboladmin.ui.features.settings

import com.kinoyamboladmin.models.SettingsValues
import com.kinoyamboladmin.utilities.texts.UiText

data class SettingsUiState(
    val language: String = SettingsValues.languages.keys.first(),
    val theme: String = SettingsValues.themes.keys.first()
)