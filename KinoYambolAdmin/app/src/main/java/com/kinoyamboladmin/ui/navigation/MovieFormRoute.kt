package com.kinoyamboladmin.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kinoyamboladmin.ui.features.movieform.MovieFormScreen
import com.kinoyamboladmin.ui.features.movieform.MovieFormViewModel

private const val MovieFormGraphRoute = "form_contacto"
private const val MovieFormParameterName = "movieId"

fun NavController.navigateToEditMovie(
    movieId: Int,
    navOptions: NavOptions? = null) {
    this.navigate("$MovieFormGraphRoute/$movieId", navOptions)
}

fun NavController.navigateToCreateMovie(
    navOptions: NavOptions? = null) {
    this.navigate("$MovieFormGraphRoute/-1", navOptions)
}

fun NavGraphBuilder.movieFormScreen(
    vm : MovieFormViewModel,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateBackwards: () -> Unit
) {
    composable(
        route = "$MovieFormGraphRoute/{$MovieFormParameterName}",
        arguments = listOf(
            navArgument(MovieFormParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val movieId :Int? = backStackEntry.arguments?.getInt(MovieFormParameterName, -1)
        if (movieId != null
            && movieId != -1) {
            vm.setMovie(movieId)
        }

        MovieFormScreen(
            movieState = vm.movieState,
            onMovieFormEvent = vm::onMovieFormEvent,
            validationMovieState = vm.movieValidationState,
            genders = vm.genreList,
            onNavigateToMovies = onNavigateToMovies,
            onNavigateToScanner = onNavigateToScanner,
            onNavigateToStatistics = onNavigateToStatistics,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateBackwards = onNavigateBackwards,
            showDateDialog = vm.showDatePickerDialogState,
            showTimeDialog = vm.showTimePickerDialogState,
            showErrorMessageSchedule = vm.showInformationErrorScheduleDialogState,
            informationState = vm.informationState
        )
    }
}