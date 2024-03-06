package com.kinoyamboladmin.data.mocks.schedule

import com.kinoyamboladmin.data.toScheduleMock
import com.kinoyamboladmin.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class ScheduleDaoMock @Inject constructor() {
    private val schedules: MutableList<ScheduleMock> = mutableListOf(
        ScheduleMock(
            id = 1,
            language = 1,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 2, 26) to listOf<LocalTime>(LocalTime.of(20, 0), LocalTime.of(22, 0)),
                LocalDate.of(2024, 2, 27) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(19, 0))
            )
        ),
        ScheduleMock(
            id = 2,
            language = 2,
            movieSchedule = hashMapOf()
        ),
        ScheduleMock(
            id = 3,
            language = 1,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 3, 4) to listOf<LocalTime>(LocalTime.of(20, 0), LocalTime.of(22, 0)),
                LocalDate.of(2024, 3, 5) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(19, 0))
            )
        ),
        ScheduleMock(
            id = 4,
            language = 2,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 3, 4) to listOf<LocalTime>(LocalTime.of(22, 0)),
                LocalDate.of(2024, 3, 5) to listOf<LocalTime>(LocalTime.of(16, 0), LocalTime.of(21, 0))
            )
        ),
        ScheduleMock(
            id = 5,
            language = 1,
            movieSchedule = hashMapOf()
        ),
        ScheduleMock(
            id = 6,
            language = 2,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 3, 11) to listOf<LocalTime>(LocalTime.of(16, 30), LocalTime.of(20, 45)),
                LocalDate.of(2024, 3, 12) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(19, 0))
            )
        ),
        ScheduleMock(
            id = 7,
            language = 1,
            movieSchedule = hashMapOf()
        ),
        ScheduleMock(
            id = 8,
            language = 2,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 3, 18) to listOf<LocalTime>(LocalTime.of(16, 30), LocalTime.of(20, 45)),
                LocalDate.of(2024, 3, 19) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(19, 0))
            )
        )
    )

    fun get(): MutableList<ScheduleMock> = schedules
    fun get(id: Int): ScheduleMock? = schedules.find { it.id == id }
    fun insert(schedule: ScheduleMock) = schedules.add(schedule)
    fun update(newSchedule: ScheduleMock)  {
        val index = schedules.indexOfFirst { schedule -> schedule.id == schedule.id }
        if (index != -1) schedules[index] = newSchedule
    }
}