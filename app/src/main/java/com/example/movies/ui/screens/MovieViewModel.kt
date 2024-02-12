package com.example.movies.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movies.MoviesApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.example.movies.data.MovieRepository
import com.example.movies.model.Movie
import com.example.movies.model.MovieDetail


/**
 * UI state for the Home screen
 */
sealed interface MovieUiState {
    data class Success(val photos: List<Movie>) : MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    /** The mutable State that stores the status of the most recent request */
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    /**
     * Call getMovies() on init so we can display status immediately.
     */
    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            movieUiState = MovieUiState.Loading
            movieUiState = try {
                MovieUiState.Success(movieRepository.getMovies())
            } catch (e: IOException) {
                MovieUiState.Error
            } catch (e: HttpException) {
                MovieUiState.Error
            }

        }
    }

    fun getMovieDetail(kinopoiskId: Int) {
        viewModelScope.launch {
            try {
                val movieDetail = movieRepository.getMovieDetail(kinopoiskId)
                setSelectedMovieDetail(movieDetail)
            } catch (e: IOException) {
                // Handle network error
            } catch (e: HttpException) {
                // Handle HTTP error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MoviesApplication)
                val movieRepository = application.container.movieRepository
                MovieViewModel(movieRepository = movieRepository)
            }
        }
    }

    val selectedMovie = mutableStateOf<Movie?>(null)
    val selectedMovieDetail = mutableStateOf<MovieDetail?>(null)

    fun setSelectedMovie(movie: Movie) {
        selectedMovie.value = movie
        // When selecting a new movie, clear previous movie detail
        selectedMovieDetail.value = null
        getMovieDetail(movie.kinopoiskId)
    }

    private fun setSelectedMovieDetail(movieDetail: MovieDetail) {
        selectedMovieDetail.value = movieDetail
    }

    fun clearSelectedMovie() {
        selectedMovie.value = null
        selectedMovieDetail.value = null
    }

}
