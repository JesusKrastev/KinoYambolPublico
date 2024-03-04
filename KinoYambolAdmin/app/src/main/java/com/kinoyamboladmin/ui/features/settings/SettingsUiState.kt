package com.kinoyamboladmin.ui.features.settings

import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Settings

data class SettingsUiState(
    val id: Int,
    val language: String = "Español",
    val theme: String = "Oscuro"
)

fun SettingsUiState.toSettings() =
    Settings(
        id = id,
        language = language,
        theme = theme
    )

fun Settings.toSettingsUiState() =
    SettingsUiState(
        id = id,
        language = language,
        theme = theme
    )