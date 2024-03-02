package com.kinoyamboladmin.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kinoyamboladmin.ui.features.statistics.StatisticsScreen


const val StatisticsGraphRoute = "statistics"

fun NavController.navigateToStatistics(navOptions: NavOptions? = null) {
    this.navigate(StatisticsGraphRoute, navOptions)
}

fun NavGraphBuilder.statisticsScreen(
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    composable(
        route = StatisticsGraphRoute,
        arguments = emptyList()
    ) {
        StatisticsScreen(
            onNavigateToMovies = onNavigateToMovies,
            onNavigateToScanner = onNavigateToScanner,
            onNavigateToStatistics = onNavigateToStatistics,
            onNavigateToSettings = onNavigateToSettings
        )
    }
}