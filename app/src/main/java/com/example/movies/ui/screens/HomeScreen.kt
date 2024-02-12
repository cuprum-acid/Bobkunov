package com.example.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.model.Movie
import com.example.movies.ui.theme.MoviesTheme
import androidx.compose.ui.text.font.Font
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.R
import java.util.Locale


// Load the Roboto Medium font from resources
val robotoMedium = FontFamily(
    Font(R.font.robotomedium)
)

@Composable
fun HomeScreen(
    movieUiState: MovieUiState,
    onItemClick: (Movie) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (movieUiState) {
        is MovieUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MovieUiState.Success -> PhotosListScreen(
            movieUiState.photos, onItemClick,
            contentPadding = contentPadding, modifier = modifier.fillMaxWidth()
        )

        is MovieUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(40.dp),
            style = TextStyle(
                fontFamily = robotoMedium,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF0094FF),
            ),

            )
        Button(
            onClick = retryAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0094FF)
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.retry),
                color = Color.White
            )
        }
    }
}

/**
 * The home screen displaying a popular movies list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosListScreen(
    photos: List<Movie>,
    onItemClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.popular),
                    style = TextStyle(
                        fontFamily = robotoMedium,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                    ),
                )
            },
            actions = {
                IconButton(onClick = { /* Handle search icon click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search",
                        tint = Color(0xFF0094FF),
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = contentPadding,
        ) {
            items(items = photos, key = { photo -> photo.kinopoiskId }) { photo ->
                MovieCard(
                    photo,
                    modifier = modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    onClick = { onItemClick(photo) }
                )
                Spacer(modifier = Modifier.height(0.dp)) // Add space between items
            }
        }
    }
}


@Composable
fun MovieCard(film: Movie, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .size(width = 328.dp, height = 93.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {

        Box(
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(film.posterUrl)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.movie_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 40.dp, height = 63.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = film.nameRu,
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 16.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Left,
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${
                    film.genres[0].genre.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                } (${film.year})",
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 16.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Gray,
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


@Composable
fun MovieDetailScreen(movie: Movie, modifier: Modifier = Modifier, onBack: () -> Unit) {
    val viewModel = viewModel<MovieViewModel>()
    val movieDetail = viewModel.selectedMovieDetail.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {

        Box {

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(movie.posterUrl)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.movie_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(582.dp)
            )


            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 80.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = Color(0xFF0094FF),
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White)
                )
            }
        }


        Text(
            text = movie.nameRu,
            style = TextStyle(
                fontFamily = robotoMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Left,
                color = Color(0xFF000000),
            ),
            modifier = Modifier
                .padding(horizontal = 31.dp, vertical = 15.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left,

            )

        movieDetail?.let { detail ->
            Text(
                text = detail.description,
                modifier = Modifier.padding(horizontal = 31.dp),
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 16.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Gray,
                ),
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 31.dp)
        ) {

            Text(
                text = "Жанры: ",
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            )


            Text(
                text = movie.genres.map { it.genre }.joinToString(", "),
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 31.dp)
        ) {

            Text(
                text = "Страны: ",
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            )


            Text(
                text = movie.countries.map { it.country }.joinToString(", "),
                style = TextStyle(
                    fontFamily = robotoMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MoviesTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MoviesTheme {
        ErrorScreen({})
    }
}
