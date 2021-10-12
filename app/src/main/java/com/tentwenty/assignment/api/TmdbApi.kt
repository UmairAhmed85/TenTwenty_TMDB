package com.tentwenty.assignment.api

import com.tentwenty.assignment.data.genres.GenresResponse
import com.tentwenty.assignment.data.movieimages.MovieImagesResponse
import com.tentwenty.assignment.data.upcomingmovies.UpcomingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UpcomingMoviesResponse

    @GET("movie/{movie_id}/images")
    suspend fun fetchMovieImages(
        @Path("movie_id") movieId: Int, // Should be long for real db
        @Query("api_key") query: String
    ): Response<MovieImagesResponse>

    @GET("genre/movie/list")
    suspend fun fetchGenresList(
        @Query("api_key") query: String
    ): GenresResponse
}