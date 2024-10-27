package com.example.android_films_app.domain.usecase

import com.example.android_films_app.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * проверка наличия интернета
 */
interface SubscribeCheckInternetUseCase {
    /**
     * получить статус подключения к интернету
     * @return статус
     */
    suspend fun getStatusInternet(): Flow<Boolean>
}

class SubscribeCheckInternetUseCaseImpl @Inject constructor(
    val repositoryImpl: FilmsRepository
): SubscribeCheckInternetUseCase{
    /**
     * получить статус подключения к интернету
     * @return статус
     */
    override suspend fun getStatusInternet(): Flow<Boolean> {
        return repositoryImpl.getStatusInternet()
    }

}