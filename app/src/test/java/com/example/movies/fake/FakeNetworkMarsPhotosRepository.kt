package com.example.movies.fake

import com.example.movies.data.MovieRepository
import com.example.movies.model.Movie
import com.example.movies.model.MovieDetail

class FakeNetworkMovieRepository : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return FakeDataSource.moviesList
    }

    override suspend fun getMovieDetail(kinopoiskId: Int): MovieDetail {
        TODO("Not yet implemented")
    }
}
