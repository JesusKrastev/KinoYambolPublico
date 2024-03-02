package com.kinoyamboladmin.data.mocks.movie

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import java.time.LocalDate
import java.time.LocalTime

data class MovieMock(
    val id: Int,
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