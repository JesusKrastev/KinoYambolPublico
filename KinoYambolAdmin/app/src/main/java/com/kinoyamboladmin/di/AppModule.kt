package com.kinoyamboladmin.di

import android.content.Context
import com.kinoyamboladmin.data.room.CinemaDb
import com.kinoyamboladmin.data.room.SettingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCinemaDatabase(
        @ApplicationContext context: Context
    ): CinemaDb = CinemaDb.getDatabase(context)

    @Provides
    @Singleton
    fun provideSettingsDao(
        cinemaDb: CinemaDb
    ): SettingsDao = cinemaDb.settingsDao()
}