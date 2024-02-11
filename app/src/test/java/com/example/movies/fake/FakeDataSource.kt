package com.example.movies.fake

import com.example.movies.model.Country
import com.example.movies.model.Genre
import com.example.movies.model.Movie

object FakeDataSource {

    private const val kinopoiskId1 = 123456
    private const val kinopoiskId2 = 789012
    private const val imdbId1 = "tt123456"
    private const val imdbId2 = "tt789012"
    private const val nameRu1 = "Фильм 1"
    private const val nameRu2 = "Фильм 2"
    private const val nameEn1 = "Movie 1"
    private const val nameEn2 = "Movie 2"
    private const val nameOriginal1 = "Original Name 1"
    private const val nameOriginal2 = "Original Name 2"
    private val countries1 = listOf(Country("Russia"), Country("USA"))
    private val countries2 = listOf(Country("France"), Country("Germany"))
    private val genres1 = listOf(Genre("Action"), Genre("Sci-Fi"))
    private val genres2 = listOf(Genre("Comedy"), Genre("Drama"))
    private const val ratingKinopoisk1 = 7.8
    private const val ratingKinopoisk2 = 8.2
    private const val ratingImdb1 = 7.5
    private const val ratingImdb2 = 8.0
    private const val year1 = 2020
    private const val year2 = 2019
    private const val type1 = "movie"
    private const val type2 = "series"
    private const val posterUrl1 = "https://example.com/poster1.jpg"
    private const val posterUrl2 = "https://example.com/poster2.jpg"

    val moviesList = listOf(
        Movie(
            kinopoiskId = kinopoiskId1,
            imdbId = imdbId1,
            nameRu = nameRu1,
            nameEn = nameEn1,
            nameOriginal = nameOriginal1,
            countries = countries1,
            genres = genres1,
            ratingKinopoisk = ratingKinopoisk1,
            ratingImdb = ratingImdb1,
            year = year1,
            type = type1,
            posterUrl = posterUrl1
        ),
        Movie(
            kinopoiskId = kinopoiskId2,
            imdbId = imdbId2,
            nameRu = nameRu2,
            nameEn = nameEn2,
            nameOriginal = nameOriginal2,
            countries = countries2,
            genres = genres2,
            ratingKinopoisk = ratingKinopoisk2,
            ratingImdb = ratingImdb2,
            year = year2,
            type = type2,
            posterUrl = posterUrl2
        )
    )
}
