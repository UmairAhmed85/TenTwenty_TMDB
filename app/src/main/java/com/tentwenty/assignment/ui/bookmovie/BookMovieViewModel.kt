package com.tentwenty.assignment.ui.bookmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tentwenty.assignment.data.bookedmovies.BookedMovies
import com.tentwenty.assignment.data.bookedmovies.BookedMoviesDao
import kotlinx.coroutines.launch

class BookMovieViewModel @ViewModelInject constructor(
    private val taskDao: BookedMoviesDao,
) : ViewModel() {

    fun onSaveClick(location: String, cinema: String, seatNumber: Int, movieImagePath:String): Boolean {
        val newTask = BookedMovies(location = location, cinema = cinema, seat = seatNumber, movieImage = movieImagePath)
        createTask(newTask)
        return true
    }

    private fun createTask(task: BookedMovies) = viewModelScope.launch {
        taskDao.insert(task)
    }
}