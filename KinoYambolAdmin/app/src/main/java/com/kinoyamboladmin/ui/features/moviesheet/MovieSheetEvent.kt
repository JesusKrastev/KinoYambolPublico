package com.kinoyamboladmin.ui.features.moviesheet

sealed interface MovieSheetEvent {
    data class OnEditMovie(val onNavigateEditMovie: (movieId: Int) -> Unit) : MovieSheetEvent
}