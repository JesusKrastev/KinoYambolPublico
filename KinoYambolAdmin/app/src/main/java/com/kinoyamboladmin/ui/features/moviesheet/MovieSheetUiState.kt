package com.kinoyamboladmin.ui.features.moviesheet

import com.kinoyamboladmin.models.Movie
import java.time.LocalDate

data class MovieSheetUiState(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val genders: List<String> = emptyList(),
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val languages: List<String> = emptyList(),
    val duration: Int = 0,
    val trailerLink: String = "",
    val price: Double = 0.0
)

fun Movie.toMovieSheetUiState(): MovieSheetUiState =
    MovieSheetUiState(
        id = id,
        image = image,
        name = name,
        description = description,
        genders = genders,
        startDate = schedules
            .flatMap { it.movieSchedule.keys }
            .minOrNull(),
        endDate = schedules
            .flatMap { it.movieSchedule.keys }
            .maxOrNull(),
        languages = schedules
            .filter { it.movieSchedule.isNotEmpty() }
            .map { it.language.name },
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )