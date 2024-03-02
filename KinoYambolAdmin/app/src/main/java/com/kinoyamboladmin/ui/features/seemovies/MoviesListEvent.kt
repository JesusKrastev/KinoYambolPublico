package com.kinoyamboladmin.ui.features.seemovies

import com.kinoyamboladmin.ui.features.MovieUiState

sealed interface MoviesListEvent {
    data class OnCreateMovie(val onNavigateCreateMovie: () -> Unit): MoviesListEvent
    data class OnClickMovie(
        val movieId: Int,
        val onNavigateMovieSheet: (Int) -> Unit
    ): MoviesListEvent
    data class OnFilterChange(val filters: FilterUiState): MoviesListEvent
}