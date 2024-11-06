package com.example.android_films_app.domain.usecase.storage.preference

import com.example.android_films_app.domain.repository.PreferencesRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 * Установить в data store запросы
 */
interface SubscribeSetQueriesUseCase {
    /**
     * Сохранить параметры в data store
     * @param query запросы
     */
    suspend fun saveQueries(query: String)
}

class SubscribeSetQueriesUseCaseImpl @Inject constructor(
    private val repository: PreferencesRepository
): SubscribeSetQueriesUseCase{
    override suspend fun saveQueries(query: String) {
        repository.savePreferences(query = query)
    }


}