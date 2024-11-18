package com.example.android_films_app.data.network.service

import com.example.android_films_app.data.network.entity.retrofit.ListFilmRetrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Lapoushko
 */
interface FilmService {
//    @GET("/v1.4/movie/random?")
//    suspend fun getRandomTitle(
//        @Query("token") token: String = ""
//    ): FilmRetrofit
    @GET("/v1.4/movie/search?")
    suspend fun getFilmsByName(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 5,
        @Query("query") nameSearch: String,
        @Query("token") token: String = "YG8DG9Y-R4H49YY-JYFGKNE-0V4JJZ9"
    ): ListFilmRetrofit

//    @GET("/v1.4/movie/{id}")
//    suspend fun getFilmById(
//        @Path("id") id: Long,
//        @Query("token") token: String = ""
//        ) : FilmRetrofit
}