package com.kinoyamboladmin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kinoyamboladmin.ui.features.movieform.MovieFormViewModel
import com.kinoyamboladmin.ui.features.moviesheet.MovieSheetViewModel
import com.kinoyamboladmin.ui.features.seemovies.MoviesListViewModel
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel

@Composable
fun MovieNavHost() {
    val navController: NavHostController = rememberNavController()
    val vmMoviesList: MoviesListViewModel = hiltViewModel<MoviesListViewModel>()
    val vmMovieSheet: MovieSheetViewModel = hiltViewModel<MovieSheetViewModel>()
    val vmSettings: SettingsViewModel = hiltViewModel<SettingsViewModel>()
    val vmMovieForm: MovieFormViewModel = hiltViewModel<MovieFormViewModel>()

    NavHost(
        navController = navController,
        startDestination = MoviesListGraphRoute
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
            onNavigateCreateMovie = {
                vmMovieForm.clearMovieState()
                navController.navigateToCreateMovie()
            },
            onNavigateMovieSheet = {
                navController.navigateToMovieSheet(it)
            }
        )
        movieSheetScreen(
            vm = vmMovieSheet,
            onNavigateToMovies = {
                navController.navigateUp()
            },
            onNavigateToScanner = {},
            onNavigateToStatistics = {
                navController.navigateToStatistics()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateEditMovie = {
                vmMovieForm.clearMovieState()
                navController.navigateToEditMovie(movieId = it)
            }
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
        movieFormScreen(
            vm = vmMovieForm,
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
            onNavigateBackwards = { navController.navigateUp() }
        )
    }
}