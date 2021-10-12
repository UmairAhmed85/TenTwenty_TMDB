package com.tentwenty.assignment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tentwenty.assignment.data.bookedmovies.BookedMovies
import com.tentwenty.assignment.data.bookedmovies.BookedMoviesDao
import com.tentwenty.assignment.data.genres.Genres
import com.tentwenty.assignment.data.genres.GenresDao

@Database(entities = [Genres::class, BookedMovies::class], version = 2)
abstract class TenTwentyDatabase : RoomDatabase() {

    abstract fun genresDao(): GenresDao
    abstract fun bookedMoviesDao(): BookedMoviesDao
}