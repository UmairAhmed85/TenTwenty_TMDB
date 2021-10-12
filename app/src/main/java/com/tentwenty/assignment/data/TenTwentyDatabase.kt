package com.tentwenty.assignment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tentwenty.assignment.data.bookedmovies.BookedMovie
import com.tentwenty.assignment.data.bookedmovies.BookedMoviesDao
import com.tentwenty.assignment.data.genres.Genres
import com.tentwenty.assignment.data.genres.GenresDao

@Database(entities = [Genres::class, BookedMovie::class], version = 1)
abstract class TenTwentyDatabase : RoomDatabase() {
    abstract fun genresDao(): GenresDao
    abstract fun bookedMoviesDao(): BookedMoviesDao
}