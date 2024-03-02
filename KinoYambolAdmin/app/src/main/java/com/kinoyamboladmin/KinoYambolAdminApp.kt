package com.kinoyamboladmin

import android.app.Application
import com.kinoyamboladmin.data.mocks.settings.SettingsDaoMock
import com.kinoyamboladmin.data.room.SettingsDao
import com.kinoyamboladmin.data.toSettings
import com.kinoyamboladmin.data.toSettingsEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class KinoYambolAdminApp: Application() {
    @Inject
    lateinit var daoSettingsMock: SettingsDaoMock
    @Inject
    lateinit var daoSettingsEntity: SettingsDao

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if (daoSettingsEntity.count() == 0)
                daoSettingsMock.get().forEach {
                    daoSettingsEntity.insert(it.toSettings().toSettingsEntity())
                }
        }
    }
}