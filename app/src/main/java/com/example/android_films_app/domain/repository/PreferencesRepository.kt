package com.example.android_films_app.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 * Репозиторий для Дата стора
 */
interface PreferencesRepository {
    /**
     * сохранить сохранения
     */
    suspend fun savePreferences(queries: List<String>)

    /**
     * получить сохранения
     */
    fun getPreferences() : Flow<List<String>>

    /**
     * очистить сохранения
     */
    suspend fun clearPreferences()
}