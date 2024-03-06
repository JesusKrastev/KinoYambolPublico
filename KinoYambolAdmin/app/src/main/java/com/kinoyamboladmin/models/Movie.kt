package com.kinoyamboladmin.models

import androidx.compose.ui.graphics.ImageBitmap
import com.kinoyamboladmin.data.mocks.movie.ScheduleMock
import java.time.LocalDate
import java.time.LocalTime

data class Movie(
    val id: Int,
    val image: String,
    val name: String,
    val description: String,
    val genders: List<String>,
    val schedules: List<Int>,
    val duration: Int,
    val trailerLink: String,
    val price: Double
)