package com.kinoyamboladmin.data.mocks.schedule

import javax.inject.Inject

class ScheduleDaoMock @Inject constructor() {
    private var schedules = mutableListOf<ScheduleMock>()

    fun get(): MutableList<ScheduleMock> = schedules
    fun get(movieId: Int): MutableList<ScheduleMock> = schedules.filter { it.movieId == movieId }.toMutableList()
    fun insert(schedule: ScheduleMock) = schedules.add(schedule)
    fun update(newSchedule: ScheduleMock) {
        val index = schedules.indexOfFirst { information -> information.id == newSchedule.id }
        if (index != -1) schedules[index] = newSchedule
    }
    fun delete(id: Int) {
        val index = schedules.indexOfFirst { information -> information.id == id }
        if (index != -1) schedules.removeAt(index)
    }
}