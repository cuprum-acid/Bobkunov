@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movies.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.ui.screens.HomeScreen
import com.example.movies.ui.screens.MovieDetailScreen
import com.example.movies.ui.screens.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp() {
    Scaffold() {
        Surface(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            val movieViewModel: MovieViewModel =
                viewModel(factory = MovieViewModel.Factory)

            if (movieViewModel.selectedMovie.value != null) {
                val selectedMovie = movieViewModel.selectedMovie.value!!
                MovieDetailScreen(
                    movie = selectedMovie,
                    onBack = { movieViewModel.clearSelectedMovie() }
                )
            } else {
                HomeScreen(
                    movieUiState = movieViewModel.movieUiState,
                    onItemClick = { movie -> movieViewModel.setSelectedMovie(movie) },
                    retryAction = movieViewModel::getMovies,
                    contentPadding = it,
                )
            }
        }
    }
}