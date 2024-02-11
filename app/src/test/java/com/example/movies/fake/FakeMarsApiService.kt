package com.example.movies.fake

import com.example.movies.model.Movie
import com.example.movies.model.MovieResponse
import com.example.movies.network.MovieApiService

class FakeMovieApiService : MovieApiService {
    override suspend fun getMovies(): MovieResponse {
        return MovieResponse(
            total = 1,
            totalPages = 1,
            items = FakeDataSource.moviesList
        )
    }
}