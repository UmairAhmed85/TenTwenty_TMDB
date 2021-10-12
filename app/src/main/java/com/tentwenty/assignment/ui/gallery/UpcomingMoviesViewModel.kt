package com.tentwenty.assignment.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tentwenty.assignment.data.TenTwentyDatabase
import com.tentwenty.assignment.data.movieimages.MovieImages

class UpcomingMoviesViewModel @ViewModelInject constructor(
    private val repository: UpcomingMoviesRepository,
) : ViewModel() {
    val upcomingMovies = repository.getUpcomingMoviesResults().cachedIn(viewModelScope)
    val genresList = repository.getAllGenres().asLiveData()
}