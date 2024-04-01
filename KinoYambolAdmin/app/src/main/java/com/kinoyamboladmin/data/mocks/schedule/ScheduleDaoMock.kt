package com.kinoyamboladmin.data.mocks.schedule

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
                LocalDate.of(2024, 3, 8) to listOf<LocalTime>(LocalTime.of(20, 0), LocalTime.of(22, 0)),
                LocalDate.of(2024, 3, 9) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(19, 0))
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
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 3, 18) to listOf<LocalTime>(LocalTime.of(16, 30), LocalTime.of(20, 45)),
                LocalDate.of(2024, 3, 19) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(19, 0))
            )
        ),
        ScheduleMock(
            id = 8,
            language = 2,
            movieSchedule = hashMapOf()
        ),
        ScheduleMock(
            id = 9,
            language = 1,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 3, 16) to listOf<LocalTime>(LocalTime.of(9, 0), LocalTime.of(13, 0)),
                LocalDate.of(2024, 3, 17) to listOf<LocalTime>(LocalTime.of(10, 0), LocalTime.of(14, 0)),
                LocalDate.of(2024, 3, 18) to listOf<LocalTime>(LocalTime.of(11, 0), LocalTime.of(15, 0)),
                LocalDate.of(2024, 3, 19) to listOf<LocalTime>(LocalTime.of(12, 0), LocalTime.of(16, 0)),
                LocalDate.of(2024, 3, 20) to listOf<LocalTime>(LocalTime.of(13, 0), LocalTime.of(17, 0)),
                LocalDate.of(2024, 3, 21) to listOf<LocalTime>(LocalTime.of(14, 0), LocalTime.of(18, 0)),
                LocalDate.of(2024, 3, 22) to listOf<LocalTime>(LocalTime.of(15, 0), LocalTime.of(19, 0)),
                LocalDate.of(2024, 3, 23) to listOf<LocalTime>(LocalTime.of(16, 0), LocalTime.of(20, 0)),
                LocalDate.of(2024, 3, 24) to listOf<LocalTime>(LocalTime.of(17, 0), LocalTime.of(21, 0)),
                LocalDate.of(2024, 3, 25) to listOf<LocalTime>(LocalTime.of(18, 0), LocalTime.of(22, 0)),
                LocalDate.of(2024, 3, 26) to listOf<LocalTime>(LocalTime.of(19, 0), LocalTime.of(23, 0)),
                LocalDate.of(2024, 3, 27) to listOf<LocalTime>(LocalTime.of(20, 0), LocalTime.of(0, 0)),
                LocalDate.of(2024, 3, 28) to listOf<LocalTime>(LocalTime.of(21, 0), LocalTime.of(1, 0)),
                LocalDate.of(2024, 3, 29) to listOf<LocalTime>(LocalTime.of(22, 0), LocalTime.of(2, 0)),
                LocalDate.of(2024, 3, 30) to listOf<LocalTime>(LocalTime.of(23, 0), LocalTime.of(3, 0)),
                LocalDate.of(2024, 3, 31) to listOf<LocalTime>(LocalTime.of(0, 0), LocalTime.of(4, 0)),
                LocalDate.of(2024, 4, 1) to listOf<LocalTime>(LocalTime.of(1, 0), LocalTime.of(5, 0)),
                LocalDate.of(2024, 4, 2) to listOf<LocalTime>(LocalTime.of(2, 0), LocalTime.of(6, 0)),
                LocalDate.of(2024, 4, 3) to listOf<LocalTime>(LocalTime.of(3, 0), LocalTime.of(7, 0)),
                LocalDate.of(2024, 4, 4) to listOf<LocalTime>(LocalTime.of(4, 0), LocalTime.of(8, 0)),
                LocalDate.of(2024, 4, 5) to listOf<LocalTime>(LocalTime.of(5, 0), LocalTime.of(9, 0)),
                LocalDate.of(2024, 4, 6) to listOf<LocalTime>(LocalTime.of(6, 0), LocalTime.of(10, 0)),
                LocalDate.of(2024, 4, 7) to listOf<LocalTime>(LocalTime.of(7, 0), LocalTime.of(11, 0))
            )
        ),
        ScheduleMock(
            id = 10,
            language = 2,
            movieSchedule = hashMapOf()
        ),
        ScheduleMock(
            id = 11,
            language = 1,
            movieSchedule = hashMapOf(
                LocalDate.of(2024, 8, 1) to listOf<LocalTime>(LocalTime.of(9, 0), LocalTime.of(13, 0))
            )
        ),
        ScheduleMock(
            id = 12,
            language = 2,
            movieSchedule = hashMapOf()
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