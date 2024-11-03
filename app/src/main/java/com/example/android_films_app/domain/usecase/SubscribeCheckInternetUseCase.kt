package com.example.android_films_app.domain.usecase

import com.example.android_films_app.domain.repository.FilmsRepository
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
    suspend fun getStatusInternet(): Boolean
}

class SubscribeCheckInternetUseCaseImpl @Inject constructor(
    val repositoryImpl: FilmsRepository
): SubscribeCheckInternetUseCase{
    /**
     * получить статус подключения к интернету
     * @return статус
     */
    override suspend fun getStatusInternet(): Boolean {
        return repositoryImpl.getStatusInternet()
    }

}