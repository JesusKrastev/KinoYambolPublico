package com.kinoyamboladmin.ui.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kinoyamboladmin.ui.features.seemovies.MoviesListScreen
import com.kinoyamboladmin.ui.features.seemovies.MoviesListViewModel

const val MoviesListGraphRoute = "movies_list"

fun NavController.navigateToMoviesList(navOptions: NavOptions? = null) {
    this.navigate(MoviesListGraphRoute, navOptions)
}

fun NavGraphBuilder.moviesListScreen(
    vm : MoviesListViewModel,
    onNavigateCreateMovie: () -> Unit,
    onNavigateMovieSheet: (Int) -> Unit,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    composable(
        route = MoviesListGraphRoute,
        arguments = emptyList()
    ) {
        MoviesListScreen(
            newMovies = vm.newMoviesState,
            movies = vm.moviesState,
            filterUiState = vm.filtersState,
            onListMoviesEvent = vm::onListMoviesEvent,
            onNavigateCreateMovie = onNavigateCreateMovie,
            onNavigateToMovies = onNavigateToMovies,
            onNavigateToScanner = onNavigateToScanner,
            onNavigateToStatistics = onNavigateToStatistics,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateMovieSheet = onNavigateMovieSheet
        )
    }
}