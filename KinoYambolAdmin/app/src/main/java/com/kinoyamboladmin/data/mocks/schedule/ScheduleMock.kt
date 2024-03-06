package com.kinoyamboladmin.data.mocks.schedule

import java.time.LocalDate
import java.time.LocalTime

data class ScheduleMock(
    val id: Int,
    val language: Int,
    val movieSchedule: HashMap<LocalDate, List<LocalTime>>
)