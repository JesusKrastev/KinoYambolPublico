package com.kinoyamboladmin.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kinoyamboladmin.models.Settings
import com.kinoyamboladmin.models.SettingsValues
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object PreferencesKeys {
        val LANGUAGE = stringPreferencesKey("language")
        val THEME = stringPreferencesKey("theme")
    }

    val settingsFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("SettingsRepository", "Error al leer las preferencia.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapSettings(preferences)
        }

    suspend fun saveTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME] = theme
        }
    }

    suspend fun saveLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = language
        }
    }

    suspend fun fetchInitialPreferences() = mapSettings(dataStore.data.first().toPreferences())

    private fun mapSettings(preferences: Preferences): Settings {
        val language: String = preferences[PreferencesKeys.LANGUAGE] ?: SettingsValues.languages.keys.first()
        val theme: String = preferences[PreferencesKeys.THEME] ?: SettingsValues.themes.keys.first()

        return Settings(
            language = language,
            theme = theme
        )
    }
}