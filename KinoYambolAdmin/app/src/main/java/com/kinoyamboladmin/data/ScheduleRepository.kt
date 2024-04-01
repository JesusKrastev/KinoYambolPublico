package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.language.LanguageDaoMock
import com.kinoyamboladmin.data.mocks.schedule.ScheduleDaoMock
import com.kinoyamboladmin.data.mocks.schedule.ScheduleMock
import com.kinoyamboladmin.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDao: ScheduleDaoMock,
    private val languageDao: LanguageDaoMock
) {
    suspend fun get(): MutableList<Schedule> = withContext(Dispatchers.IO) {
        scheduleDao.get().map { it.toSchedule() }.toMutableList()
    }
    suspend fun get(id: Int): Schedule? = withContext(Dispatchers.IO) {
        scheduleDao.get(id)?.toSchedule()
    }
    suspend fun insert(schedule: Schedule) = withContext(Dispatchers.IO) {
        scheduleDao.insert(schedule.toScheduleMock())
    }
    suspend fun update(newSchedule: Schedule) = withContext(Dispatchers.IO) {
        scheduleDao.update(newSchedule.toScheduleMock())
    }

    fun Schedule.toScheduleMock(): ScheduleMock =
        ScheduleMock(
            id = id,
            language = language.id,
            movieSchedule = movieSchedule
        )

    fun ScheduleMock.toSchedule(): Schedule =
        Schedule(
            id = id,
            language = languageDao.get(language)!!.toLanguage(),
            movieSchedule = movieSchedule
        )
}