package com.tentwenty.assignment.ui.bookmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tentwenty.assignment.data.bookedmovies.BookedMovie
import com.tentwenty.assignment.data.bookedmovies.BookedMoviesDao
import com.tentwenty.assignment.data.movieimages.MovieImages
import kotlinx.coroutines.launch

class BookMovieViewModel @ViewModelInject constructor(
    private val taskDao: BookedMoviesDao,
) : ViewModel() {

    fun onSaveClick(location: String, cinema: String, seatNumber: Int, movieId: Int, movieImagePath:String): Boolean {
        val newTask = BookedMovie(location = location, cinema = cinema, seat = seatNumber, movieId = movieId,movieImage = movieImagePath)
        createTask(newTask)
        return true
    }

    private fun createTask(task: BookedMovie) = viewModelScope.launch {
        taskDao.insert(task)
    }
}