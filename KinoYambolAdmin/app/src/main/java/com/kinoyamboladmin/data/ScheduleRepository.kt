package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.schedule.ScheduleDaoMock
import com.kinoyamboladmin.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val dao: ScheduleDaoMock
) {
    suspend fun get(): MutableList<Schedule> = withContext(Dispatchers.IO) {
        dao.get().map { it.toSchedule() }.toMutableList()
    }
    suspend fun insert(schedule: Schedule) = withContext(Dispatchers.IO) {
        dao.insert(schedule.toScheduleMock())
    }
    suspend fun update(newSchedule: Schedule) = withContext(Dispatchers.IO) {
        dao.update(newSchedule.toScheduleMock())
    }
}