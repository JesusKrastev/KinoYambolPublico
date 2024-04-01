package com.kinoyamboladmin.ui.features.settings

sealed interface SettingsEvent {
    data class OnChangeLanguage(val language: String): SettingsEvent
    data class OnChangeTheme(val theme: String): SettingsEvent
    data object OnClickPrivacyPolicies : SettingsEvent
    data object OnClickTermsAndConditions : SettingsEvent
}