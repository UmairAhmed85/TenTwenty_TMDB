package com.tentwenty.assignment.data.bookedmovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booked_movies")
data class BookedMovies(
    val location: String,
    val cinema: String,
    @PrimaryKey
    val seat: Int
)