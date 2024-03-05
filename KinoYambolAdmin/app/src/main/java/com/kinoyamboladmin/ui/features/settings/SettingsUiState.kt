package com.kinoyamboladmin.ui.features.settings

import com.kinoyamboladmin.models.Language

data class SettingsUiState(
    val language: String = "Español",
    val theme: String = "Oscuro"
)