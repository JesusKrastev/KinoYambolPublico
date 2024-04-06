package com.kinoyamboladmin.models

import com.kinoyamboladmin.R
import com.kinoyamboladmin.utilities.texts.UiText

object SettingsValues {
    val languages: Map<String, UiText> = mapOf(
        "sys" to UiText.StringResource(R.string.label_system),
        "es" to UiText.DynamicString("Español"),
        "bg" to UiText.DynamicString("Български"),
        "en" to UiText.DynamicString("English")
    )
    val themes: Map<String, UiText> = mapOf(
        "sys" to UiText.StringResource(R.string.label_system),
        "dark" to UiText.StringResource(R.string.label_dark_theme),
        "light" to UiText.StringResource(R.string.label_light_theme)
    )
}