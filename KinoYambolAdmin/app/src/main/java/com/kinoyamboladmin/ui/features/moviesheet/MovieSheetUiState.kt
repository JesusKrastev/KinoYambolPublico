package com.kinoyamboladmin.ui.features.moviesheet

import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.ui.features.GenderUiState
import com.kinoyamboladmin.ui.features.toGenderUiState
import java.time.LocalDate

data class MovieSheetUiState(
    val id: Int = 0,
    val image: String = "",
    val lettersImage: String? = null,
    val name: String = "",
    val description: String = "",
    val genders: List<GenderUiState> = emptyList(),
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val languages: List<String> = emptyList(),
    val duration: Int = 0,
    val trailerLink: String = "",
    val price: Double = 0.0
)

fun Movie.toMovieSheetUiState(): MovieSheetUiState {
    val allDates: List<LocalDate> = schedules.flatMap { it.movieSchedule.keys }

    return MovieSheetUiState(
        id = id,
        image = image,
        lettersImage = lettersImage,
        name = name,
        description = description,
        genders = genders.map { it.toGenderUiState() },
        startDate = allDates.minOrNull(),
        // if schedules have only one date each one, that means the movie is in release
        endDate = if (allDates.size <= schedules.size) null else allDates.maxOrNull(),
        languages = schedules
            .filter { it.movieSchedule.isNotEmpty() }
            .map { it.language.name },
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )
}