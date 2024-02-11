package com.example.movies.data

import com.example.movies.model.Movie
import com.example.movies.network.MovieApiService

interface MovieRepository {
    /** Fetches list of Movies from Kinopoisk API */
    suspend fun getMovies(): List<Movie>
}

/**
 * Network Implementation of Repository that fetch Movies list Kinopoisk API.
 */
class NetworkMovieRepository(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> = movieApiService.getMovies().items
}
