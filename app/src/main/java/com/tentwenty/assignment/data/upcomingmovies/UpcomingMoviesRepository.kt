package com.tentwenty.assignment.data.upcomingmovies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.room.withTransaction
import com.tentwenty.assignment.BuildConfig
import com.tentwenty.assignment.api.TmdbApi
import com.tentwenty.assignment.data.TenTwentyDatabase
import com.tentwenty.assignment.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpcomingMoviesRepository @Inject constructor(
    private val tmdbApi: TmdbApi,
    private val db: TenTwentyDatabase
) {
    private val genresDao = db.genresDao()

    fun getUpcomingMoviesResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UpcomingMoviesPagingSource(tmdbApi) }
        ).liveData

    fun getAllGenres() = networkBoundResource(
        query = {
            genresDao.getAllGenres()
        },
        fetch = {
            tmdbApi.fetchGenresList(BuildConfig.API_KEY) // Remove lang param when done, doesnt makes sense during assignment
        },
        saveFetchResult = { genresResponse ->
            db.withTransaction {
                genresDao.deleteAllGenres()
                genresDao.insertGenres(genresResponse.genres)
            }
        }
    )
}