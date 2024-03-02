package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.settings.SettingsDaoMock
import com.kinoyamboladmin.models.Settings
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val dao: SettingsDaoMock
) {
    suspend fun get(): MutableList<Settings> = dao.get().map { it.toSettings() }.toMutableList()
    suspend fun update(newSettings: Settings) = dao.update(newSettings.toSettingsMock())
}