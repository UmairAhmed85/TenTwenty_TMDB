package com.tentwenty.assignment.data.bookedmovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookedMoviesDao {
    @Query("SELECT * FROM booked_movies")
    fun getAllBookedMovies(): Flow<List<BookedMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookedMovies: BookedMovies)

    @Query("DELETE FROM booked_movies")
    suspend fun deleteAllBookedMovies()
}