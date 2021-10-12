package com.tentwenty.assignment.ui.bookedmovies

import com.tentwenty.assignment.api.TmdbApi
import com.tentwenty.assignment.data.TenTwentyDatabase
import com.tentwenty.assignment.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BookedMoviesRepository @Inject constructor(
    private val db: TenTwentyDatabase
) {
    private val bookedMovies = db.bookedMoviesDao()

    fun getAllBookedMovies() = bookedMovies.getAllBookedMovies()
}