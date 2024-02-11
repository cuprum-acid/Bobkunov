package com.example.movies.network

import com.example.movies.model.Movie
import com.example.movies.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers

private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

/**
 * A public interface that exposes the [getMovies] method
 */
interface MovieApiService {
    /**
     * Returns a [MovieResponse] of [Movie] and this method can be called from a Coroutine.
     */
    @Headers("X-API-KEY: $API_KEY")
    @GET("films/collections?type=TOP_POPULAR_MOVIES&page=1")
    suspend fun getMovies(): MovieResponse
}
