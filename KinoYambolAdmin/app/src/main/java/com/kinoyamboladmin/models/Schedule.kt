package com.kinoyamboladmin.models

import java.time.LocalDate
import java.time.LocalTime

data class Schedule(
    val id: Int,
    val language: Language,
    val movieSchedule: HashMap<LocalDate, List<LocalTime>>
)