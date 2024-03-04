package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.settings.SettingsDaoMock
import com.kinoyamboladmin.data.room.SettingsDao
import com.kinoyamboladmin.models.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val dao: SettingsDao
) {
    suspend fun get(): MutableList<Settings> = withContext(Dispatchers.IO) {
        dao.get().map { it.toSettings() }.toMutableList()
    }
    suspend fun update(newSettings: Settings) = withContext(Dispatchers.IO) {
        dao.update(newSettings.toSettingsEntity())
    }
}