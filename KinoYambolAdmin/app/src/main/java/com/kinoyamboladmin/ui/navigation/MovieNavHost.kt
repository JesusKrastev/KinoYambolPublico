package com.kinoyamboladmin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kinoyamboladmin.ui.features.moviesheet.MovieSheetViewModel
import com.kinoyamboladmin.ui.features.seemovies.MoviesListViewModel
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel

@Composable
fun MovieNavHost() {
    val navController: NavHostController = rememberNavController()
    val vmMoviesList: MoviesListViewModel = hiltViewModel<MoviesListViewModel>()
    val vmMovieSheet: MovieSheetViewModel = hiltViewModel<MovieSheetViewModel>()
    val vmSettings: SettingsViewModel = hiltViewModel<SettingsViewModel>()
    val animationDuration = 500

    NavHost(
        navController = navController,
        startDestination = MoviesListGraphRoute,
        enterTransition = { entryFromRight(animationDuration) },
        exitTransition = { exitFromLeft(animationDuration) }
    ) {
        moviesListScreen(
            vm = vmMoviesList,
            onNavigateToMovies = {},
            onNavigateToScanner = {},
            onNavigateToStatistics = {
                navController.navigateToStatistics()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateCreateMovie = {},
            onNavigateMovieSheet = {
                navController.navigateToMovieSheet(it)
            }
        )
        movieSheetScreen(
            vm = vmMovieSheet,
            onNavigateToMovies = {
                navController.navigateToMoviesList()
            },
            onNavigateToScanner = {},
            onNavigateToStatistics = {
                navController.navigateToStatistics()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateEditMovie = {}
        )
        statisticsScreen(
            onNavigateToMovies = {
                navController.navigateToMoviesList()
            },
            onNavigateToScanner = {},
            onNavigateToStatistics = {},
            onNavigateToSettings = {
                navController.navigateToSettings()
            }
        )
        settingsScreen(
            vm = vmSettings,
            onNavigateToMovies = {
                navController.navigateToMoviesList()
            },
            onNavigateToScanner = {},
            onNavigateToStatistics = {
                navController.navigateToStatistics()
            },
            onNavigateToSettings = {}
        )
    }
}