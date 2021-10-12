package com.tentwenty.assignment.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tentwenty.assignment.data.movieimages.MovieImages
import kotlinx.coroutines.*

class DetailViewModel @ViewModelInject constructor(
    private val repository: DetailRepository
) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val imageList = MutableLiveData<List<MovieImages>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMovies(movieId: Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            println("movieId ::: ${movieId}")
            val response = repository.getMovieImagesList(movieId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    println("response.isSuccessful ::: ${movieId}")
                    println("response.body() ${response.body()}")
                    imageList.value =response.body()!!.backdrops
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        println("onError ::: ${message}")

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        println("job cancelled ::: ")
    }
}