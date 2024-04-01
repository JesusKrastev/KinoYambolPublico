package com.kinoyamboladmin.ui.features.seemovies

import com.kinoyamboladmin.models.Movie

data class MovieListUiState(
    val id: Int,
    val image: String,
    val lettersImage: String?
)

fun Movie.toMovieListUiState(): MovieListUiState =
    MovieListUiState(
        id = id,
        image = image,
        lettersImage = lettersImage
    )