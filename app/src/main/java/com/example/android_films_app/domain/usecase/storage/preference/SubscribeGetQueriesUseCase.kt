package com.example.android_films_app.domain.usecase.storage.preference

import com.example.android_films_app.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * получить все запросы из data store
 */
interface SubscribeGetQueriesUseCase {
    fun getQueries(): Flow<String>
}

class SubscribeGetQueriesUseCaseImpl @Inject constructor(
    private val repository: PreferencesRepository
) : SubscribeGetQueriesUseCase {
    override fun getQueries(): Flow<String> {
        return repository.getPreferences()
    }
}