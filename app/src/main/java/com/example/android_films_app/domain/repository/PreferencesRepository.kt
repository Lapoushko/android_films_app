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
    suspend fun savePreferences(query: String)

    /**
     * получить сохранения
     */
    fun getPreferences() : Flow<String>
}