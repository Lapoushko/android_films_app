package com.example.android_films_app.data.storage.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_films_app.data.storage.util.ConstantsPreferences
import com.example.android_films_app.domain.repository.PreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 * Дата стор для сохранения поисковых запросов
 */
class PreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferencesRepository {
    override suspend fun savePreferences(queries: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.QUERIES] =
                preferences[PreferencesKeys.QUERIES] +
                queries.joinToString(separator = ConstantsPreferences.SEPARATOR) { it }
        }
    }

    override fun getPreferences(): Flow<List<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.QUERIES]?.split(ConstantsPreferences.SEPARATOR)
                ?: emptyList()
        }
    }

    /**
     * очистить сохранения
     */
    override suspend fun clearPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(ConstantsPreferences.PREFERENCES_NAME)

private object PreferencesKeys {
    val QUERIES = stringPreferencesKey(ConstantsPreferences.QUERIES)
}