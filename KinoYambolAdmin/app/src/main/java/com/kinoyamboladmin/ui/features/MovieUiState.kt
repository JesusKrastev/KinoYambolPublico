package com.kinoyamboladmin.ui.features

import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.models.Schedule

data class MovieUiState(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val genders: List<String> = emptyList(),
    val schedules: List<Schedule> = listOf(
        Schedule(
            id = 0,
            language = 1,
            movieSchedule = hashMapOf()
        ),
        Schedule(
            id = 0,
            language = 2,
            movieSchedule = hashMapOf()
        )
    ),
    val duration: Int = 0,
    val trailerLink: String = "",
    val price: Double = 0.0
)

fun Movie.toMovieUiState(): MovieUiState =
    MovieUiState(
        id = id,
        image = image,
        name = name,
        description = description,
        genders = genders,
        schedules = schedules,
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )

fun MovieUiState.toMovie(): Movie =
    Movie(
        id = id,
        image = image,
        name = name,
        description = description,
        genders = genders,
        schedules = schedules,
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )