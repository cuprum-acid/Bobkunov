package com.example.movies.fake

import com.example.movies.data.MovieRepository
import com.example.movies.model.Movie

class FakeNetworkMovieRepository : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return FakeDataSource.moviesList
    }
}
