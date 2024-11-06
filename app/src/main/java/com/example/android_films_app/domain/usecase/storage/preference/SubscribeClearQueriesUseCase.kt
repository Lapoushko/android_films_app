package com.example.android_films_app.domain.usecase.storage.preference

import com.example.android_films_app.domain.repository.PreferencesRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 * очистить запросы
 */
interface SubscribeClearQueriesUseCase {
    /**
     * очистка
     */
    suspend fun clear()
}

class SubscribeClearQueriesUseCaseImpl @Inject constructor(
    private val repository: PreferencesRepository
) : SubscribeClearQueriesUseCase{
    /**
     * очистка
     */
    override suspend fun clear() {
        repository.clearPreferences()
    }
}