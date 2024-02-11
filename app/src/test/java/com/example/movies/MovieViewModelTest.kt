package com.example.movies

import com.example.movies.data.NetworkMovieRepository
import com.example.movies.fake.FakeMovieApiService
import com.example.movies.fake.FakeDataSource
import com.example.movies.rules.TestDispatcherRule
import com.example.movies.ui.screens.MovieUiState
import com.example.movies.ui.screens.MovieViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun movieViewModel_getMovies_verifyMovieUiStateSuccess() =
        runTest {
            val movieViewModel = MovieViewModel(
                movieRepository = NetworkMovieRepository(
                    movieApiService = FakeMovieApiService()
                )
            )
            assertEquals(
                MovieUiState.Success(FakeDataSource.moviesList),
                movieViewModel.movieUiState
            )
        }
}

