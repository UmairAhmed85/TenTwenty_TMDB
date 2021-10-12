package com.tentwenty.assignment.ui.details

import com.tentwenty.assignment.BuildConfig
import com.tentwenty.assignment.api.TmdbApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val tmdbApi: TmdbApi,
) {
    suspend fun getMovieImagesList(movieId : Int) = tmdbApi.fetchMovieImages(movieId, BuildConfig.API_KEY)
}