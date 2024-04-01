package com.kinoyamboladmin.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kinoyamboladmin.ui.features.settings.SettingsScreen
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel

const val SettingsGraphRoute = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(SettingsGraphRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen(
    vm : SettingsViewModel,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    composable(
        route = SettingsGraphRoute,
        arguments = emptyList()
    ) {
        SettingsScreen(
            languages = vm.languages.keys.toList(),
            themes = vm.themes,
            settingsUiState = vm.userSettingsState!!,
            onSettingsEvent = vm::onSettingsEvent,
            onNavigateToMovies = onNavigateToMovies,
            onNavigateToScanner = onNavigateToScanner,
            onNavigateToStatistics = onNavigateToStatistics,
            onNavigateToSettings = onNavigateToSettings
        )
    }
}