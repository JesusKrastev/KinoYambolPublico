package com.kinoyamboladmin.models

import java.time.LocalDate
import java.time.LocalTime

data class Information(
    val id: Int,
    val movieId: Int,
    val language: Int,
    val date: LocalDate,
    val hours: List<LocalTime>
)