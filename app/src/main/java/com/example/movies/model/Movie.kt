package com.example.movies.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class defines a Movie entity which includes a kinopoiskId, name and the poster Url.
 */
@Serializable
data class Movie(
    val kinopoiskId: Int,
    val imdbId: String?,
    val nameRu: String,
    val nameEn: String?,
    val nameOriginal: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val year: Int,
    val type: String,
    @SerialName(value = "posterUrlPreview") val posterUrl: String
)

@Serializable
data class Country(val country: String)

@Serializable
data class Genre(val genre: String)

@Serializable
data class MovieResponse(
    val total: Int, val totalPages: Int, val items: List<Movie>
)

@Serializable
data class MovieDetail(
    val kinopoiskId: Int,
    val kinopoiskHDId: String?,
    val imdbId: String?,
    val nameRu: String,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val coverUrl: String?,
    val logoUrl: String?,
    val reviewsCount: Int?,
    val ratingGoodReview: Int?,
    val ratingGoodReviewVoteCount: Int?,
    val ratingKinopoisk: Double?,
    val ratingKinopoiskVoteCount: Int?,
    val ratingImdb: Double?,
    val ratingImdbVoteCount: Int?,
    val ratingFilmCritics: Double?,
    val ratingFilmCriticsVoteCount: Int?,
    val ratingAwait: Int?,
    val ratingAwaitCount: Int,
    val ratingRfCritics: Int?,
    val ratingRfCriticsVoteCount: Int?,
    val webUrl: String?,
    val year: Int,
    val filmLength: Int?,
    val slogan: String?,
    val description: String,
    val shortDescription: String?,
    val editorAnnotation: String?,
    val isTicketsAvailable: Boolean?,
    val productionStatus: String?,
    val type: String?,
    val ratingMpaa: String?,
    val ratingAgeLimits: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val startYear: Int?,
    val endYear: Int?,
    val serial: Boolean?,
    val shortFilm: Boolean?,
    val completed: Boolean?,
    val hasImax: Boolean?,
    val has3D: Boolean?,
    val lastSync: String?
)


