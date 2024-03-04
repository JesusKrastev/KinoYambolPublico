package com.kinoyamboladmin.ui.features.settings

import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Theme

sealed interface SettingsEvent {
    data class OnChangeLanguage(val language: String): SettingsEvent
    data class OnChangeTheme(val theme: String): SettingsEvent
    data object OnClickPrivacyPolicies : SettingsEvent
    data object OnClickTermsAndConditions : SettingsEvent
}