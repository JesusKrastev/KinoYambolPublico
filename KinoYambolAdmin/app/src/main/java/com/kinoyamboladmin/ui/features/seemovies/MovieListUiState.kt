package com.kinoyamboladmin.ui.features.seemovies

import com.kinoyamboladmin.models.Movie

data class MovieListUiState(
    val id: Int,
    val image: String
)

fun Movie.toMovieListUiState(): MovieListUiState =
    MovieListUiState(
        id = id,
        image = image
    )