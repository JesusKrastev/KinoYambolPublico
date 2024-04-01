package com.kinoyamboladmin.data.mocks.movie

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import java.time.LocalDate
import java.time.LocalTime

data class MovieMock(
    val id: Int,
    val image: String,
    val lettersImage: String?,
    val name: String,
    val description: String,
    val schedules: List<Int>,
    val genders: List<Int>,
    val duration: Int,
    val trailerLink: String,
    val price: Double
)