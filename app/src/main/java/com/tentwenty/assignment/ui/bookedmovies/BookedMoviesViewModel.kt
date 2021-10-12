package com.tentwenty.assignment.ui.bookedmovies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tentwenty.assignment.data.bookedmovies.BookedMoviesDao
import com.tentwenty.assignment.ui.details.DetailRepository

class BookedMoviesViewModel @ViewModelInject constructor(
    private val repository: BookedMoviesRepository
) : ViewModel() {

    val bookedMovies =  repository.getAllBookedMovies().asLiveData()
}