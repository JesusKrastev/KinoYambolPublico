package com.kinoyamboladmin.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kinoyamboladmin.ui.features.moviesheet.MovieSheetScreen
import com.kinoyamboladmin.ui.features.moviesheet.MovieSheetViewModel

private const val MovieSheetGraphRoute = "movie_sheet"
private const val MovieSheetParameterName = "movieId"

fun NavController.navigateToMovieSheet(
    movieId: Int,
    navOptions: NavOptions? = null) {
    this.navigate("$MovieSheetGraphRoute/$movieId", navOptions)
}

fun NavGraphBuilder.movieSheetScreen(
    vm : MovieSheetViewModel,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateEditMovie: (Int) -> Unit
) {
    composable(
        route = "$MovieSheetGraphRoute/{$MovieSheetParameterName}",
        arguments = listOf(
            navArgument(MovieSheetParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val movieId :Int? = backStackEntry.arguments?.getInt(MovieSheetParameterName, -1)
        if (movieId != null
            && movieId != -1) {
            vm.setMovie(movieId!!)
        }

        MovieSheetScreen(
            selectedMovie = vm.selectedMovieState,
            onSheetEvent = vm::onMovieSheetEvent,
            onNavigateToMovies = onNavigateToMovies,
            onNavigateToScanner = onNavigateToScanner,
            onNavigateToStatistics = onNavigateToStatistics,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateEditMovie = onNavigateEditMovie
        )
    }
}