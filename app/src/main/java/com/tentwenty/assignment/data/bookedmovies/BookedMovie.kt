package com.tentwenty.assignment.data.bookedmovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booked_movies")
data class BookedMovie(
    val location: String,
    val cinema: String,
    val seat: Int,
    val movieId : Int, // Taking it along as i am going to show movie detail from booked movies screen, not a part of assignment
    val movieImage: String, // Just using it for better demo understanding when user is at Booked Movie Screen
    @PrimaryKey(autoGenerate = true)  val id: Int = 0
)