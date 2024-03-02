package com.kinoyamboladmin.ui.features

import com.kinoyamboladmin.models.Movie
import java.time.LocalDate
import java.time.LocalTime

data class MovieUiState(
    val id: Int = System.currentTimeMillis().hashCode(),
    val image: String,
    val title: String,
    val description: String,
    val genders: List<String>,
    val languages: List<String>,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val duration: Int,
    val trailerLink: String,
    val price: Double
)

fun Movie.toMovieUiState(): MovieUiState =
    MovieUiState(
        id = id,
        image = image,
        title = title,
        description = description,
        genders = genders,
        languages = languages,
        startDate = startDate,
        endDate = endDate,
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )

fun MovieUiState.toMovie(): Movie =
    Movie(
        id = id,
        image = image,
        title = title,
        description = description,
        genders = genders,
        languages = languages,
        startDate = startDate,
        endDate = endDate,
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )