package com.kinoyamboladmin.data.mocks.settings

import javax.inject.Inject

class SettingsDaoMock @Inject constructor() {
    private var settings: MutableList<SettingsMock> = mutableListOf<SettingsMock>(
        SettingsMock(
            id = 1,
            language = "Español",
            theme = "Oscuro"
        )
    )
    fun get(): MutableList<SettingsMock> = settings
    fun update(newSettings: SettingsMock) {
        val index: Int = settings.indexOfFirst { setting -> setting.id == newSettings.id }
        if (index != -1) settings[index] = newSettings
    }
}