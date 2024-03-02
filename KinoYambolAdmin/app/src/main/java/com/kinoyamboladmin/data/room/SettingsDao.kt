package com.kinoyamboladmin.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Insert
    suspend fun insert(settings : SettingsEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(settings : SettingsEntity)
    @Query("SELECT COUNT(*) FROM settings")
    suspend fun count(): Int
}