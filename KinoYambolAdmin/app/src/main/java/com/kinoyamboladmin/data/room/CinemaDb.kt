package com.kinoyamboladmin.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SettingsEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CinemaDb : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            CinemaDb::class.java, "cinema"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}