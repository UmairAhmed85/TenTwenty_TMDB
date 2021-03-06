package com.tentwenty.assignment.ui.gallery

import androidx.paging.PagingSource
import com.tentwenty.assignment.BuildConfig
import com.tentwenty.assignment.api.TmdbApi
import com.tentwenty.assignment.data.upcomingmovies.UpcomingMovie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Singleton

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

@Singleton
class UpcomingMoviesPagingSource(
    private val tmdbApi: TmdbApi,
) : PagingSource<Int, UpcomingMovie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingMovie> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = tmdbApi.fetchUpcomingMovies(BuildConfig.API_KEY, position, params.loadSize)
            val upcomingMovieList = response.results

            LoadResult.Page(
                data = upcomingMovieList,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (upcomingMovieList.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}