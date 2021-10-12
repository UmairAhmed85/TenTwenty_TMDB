package com.tentwenty.assignment.data.bookedmovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booked_movies")
data class BookedMovies(
    val location: String,
    val cinema: String,
    val seat: Int,
    val movieImage: String, // Just using it for better demo understanding when user is at Booked Movie Screen
    @PrimaryKey(autoGenerate = true)  val id: Int = 0
)