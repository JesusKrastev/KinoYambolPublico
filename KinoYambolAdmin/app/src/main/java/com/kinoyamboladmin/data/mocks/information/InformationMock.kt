package com.kinoyamboladmin.data.mocks.information

import java.time.LocalDate
import java.time.LocalTime

data class InformationMock(
    val id: Int,
    val movieId: Int,
    val language: Int,
    val date: LocalDate,
    val hours: List<LocalTime>
)