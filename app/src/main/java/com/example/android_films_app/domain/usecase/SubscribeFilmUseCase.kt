package com.example.android_films_app.domain.usecase

///**
// * @author Lapoushko
// * Юзкейс фильма
// */
//interface SubscribeFilmUseCase{
//    suspend fun getFilm(id: Long) : Flow<Film>
//}
//
///**
// * Реализация одноименного юзкейса
// * @param filmsDataRepository репозиторий
// */
//class SubscribeFilmUseCaseImpl @Inject constructor(
//    val filmsDataRepository: FilmsDataRepository
//): SubscribeFilmUseCase {
//    override suspend fun getFilm(id: Long): Flow<Film> {
//        return filmsDataRepository.getFilm(id = id)
//    }
//}