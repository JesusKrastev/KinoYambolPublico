package com.kinoyamboladmin.ui.features.movieform

import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.models.Schedule
import com.kinoyamboladmin.ui.features.GenderUiState
import com.kinoyamboladmin.ui.features.toGender
import com.kinoyamboladmin.ui.features.toGenderUiState
import java.time.LocalDate
import java.time.LocalTime

data class MovieUiState(
    val id: Int = 0,
    val image: String? = null,
    val lettersImage: String? = null,
    val name: String = "",
    val description: String = "",
    val genders: List<GenderUiState> = emptyList(),
    val schedules: List<Schedule> = emptyList(),
    val selectedLanguage: String = schedules
        .map { it.language.name }
        .firstOrNull() ?: "",
    val selectedDate: LocalDate? = null,
    val selectedHour: LocalTime? = null,
    val duration: Int = 0,
    val trailerLink: String = "",
    val price: String = ""
)

fun Movie.toMovieUiState(): MovieUiState =
    MovieUiState(
        id = id,
        image = image,
        lettersImage = lettersImage,
        name = name,
        description = description,
        genders = genders.map { it.toGenderUiState() },
        schedules = schedules,
        selectedLanguage = schedules
            .filter { it.movieSchedule.isNotEmpty() }
            .map { it.language.name }
            .first(),
        duration = duration,
        trailerLink = trailerLink,
        price = String.format("%.2f", price).replace(",", ".")
    )

fun MovieUiState.toMovie(): Movie =
    Movie(
        id = id,
        image = image!!,
        lettersImage = lettersImage,
        name = name,
        description = description,
        genders = genders.map { it.toGender() },
        schedules = schedules,
        duration = duration,
        trailerLink = trailerLink,
        price = price.toDouble()
    )