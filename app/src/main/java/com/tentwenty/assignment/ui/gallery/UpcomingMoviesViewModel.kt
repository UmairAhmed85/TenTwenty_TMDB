package com.tentwenty.assignment.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class UpcomingMoviesViewModel @ViewModelInject constructor(
    private val repository: UpcomingMoviesRepository
) : ViewModel() {
    val upcomingMovies =  repository.getUpcomingMoviesResults().cachedIn(viewModelScope)
    val genresList =  repository.getAllGenres().asLiveData()
    /*private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val upcomingMovies = currentQuery.switchMap { queryString ->
        repository.getUpcomingMoviesResults(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = "upcoming"
        private const val DEFAULT_QUERY = "upcoming" // Using static endpoint for this assignment without filtering at the moment, will be put used under filter method once search query comes in ...
    }*/
}