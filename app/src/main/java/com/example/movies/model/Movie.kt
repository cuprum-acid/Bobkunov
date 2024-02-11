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
