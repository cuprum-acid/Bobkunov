package com.example.movies

import com.example.movies.data.NetworkMovieRepository
import com.example.movies.fake.FakeDataSource
import com.example.movies.fake.FakeMovieApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMovieRepositoryTest {

    @Test
    fun networkMovieRepository_getMovies_verifyMovieList() =
        runTest {
            val repository = NetworkMovieRepository(
                movieApiService = FakeMovieApiService()
            )
            assertEquals(FakeDataSource.moviesList, repository.getMovies())
        }
}
