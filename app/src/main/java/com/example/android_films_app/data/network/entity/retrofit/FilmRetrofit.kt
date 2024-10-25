package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmRetrofit(
    @SerialName("ageRating")
    val ageRating: Int? = 0,
    @SerialName("alternativeName")
    val alternativeName: String? = "",
    @SerialName("audience")
    val audience: List<Audience>? = listOf(),
    @SerialName("backdrop")
    val backdrop: Backdrop? = Backdrop(),
    @SerialName("budget")
    val budget: Budget? = Budget(),
    @SerialName("countries")
    val countries: List<Country>? = listOf(),
    @SerialName("createdAt")
    val createdAt: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("enName")
    val enName: String? = "",
    @SerialName("externalId")
    val externalId: ExternalId? = ExternalId(),
    @SerialName("facts")
    val facts: List<Fact>? = listOf(),
    @SerialName("fees")
    val fees: Fees? = Fees(),
    @SerialName("genres")
    val genres: List<Genre>? = listOf(),
    @SerialName("id")
    val id: Long? = 0,
    @SerialName("isSeries")
    val isSeries: Boolean? = false,
    @SerialName("lists")
    val lists: List<String>? = listOf(),
    @SerialName("logo")
    val logo: Logo? = Logo(),
    @SerialName("movieLength")
    val movieLength: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("names")
    val names: List<Name>? = listOf(),
    @SerialName("networks")
    val networks: Networks? = Networks(),
    @SerialName("persons")
    val persons: List<Person>? = listOf(),
    @SerialName("poster")
    val poster: Poster? = Poster(),
    @SerialName("premiere")
    val premiere: Premiere? = Premiere(),
    @SerialName("rating")
    val rating: Rating? = Rating(),
    @SerialName("ratingMpaa")
    val ratingMpaa: String? = "",
    @SerialName("releaseYears")
    val releaseYears: List<ReleaseYear>? = listOf(),
    @SerialName("reviewInfo")
    val reviewInfo: ReviewInfo? = ReviewInfo(),
    @SerialName("seasonsInfo")
    val seasonsInfo: List<SeasonsInfo>? = listOf(),
    @SerialName("sequelsAndPrequels")
    val sequelsAndPrequels: List<SequelsAndPrequel>? = listOf(),
    @SerialName("seriesLength")
    val seriesLength: Int? = 0,
    @SerialName("shortDescription")
    val shortDescription: String? = "",
    @SerialName("similarMovies")
    val similarMovies: List<SimilarMovy>? = listOf(),
    @SerialName("slogan")
    val slogan: String? = "",
    @SerialName("status")
    val status: String? = "",
    @SerialName("ticketsOnSale")
    val ticketsOnSale: Boolean? = false,
    @SerialName("top10")
    val top10: Int? = 0,
    @SerialName("top250")
    val top250: Int? = 0,
    @SerialName("totalSeriesLength")
    val totalSeriesLength: Int? = 0,
    @SerialName("type")
    val type: String? = "",
    @SerialName("typeNumber")
    val typeNumber: Int? = 0,
    @SerialName("updatedAt")
    val updatedAt: String? = "",
    @SerialName("videos")
    val videos: Videos? = Videos(),
    @SerialName("votes")
    val votes: Votes? = Votes(),
    @SerialName("watchability")
    val watchability: Watchability? = Watchability(),
    @SerialName("year")
    val year: Int? = 0
)