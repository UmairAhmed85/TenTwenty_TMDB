package com.tentwenty.assignment.data.genres

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {

    @Query("SELECT * FROM genres")
    fun getAllGenres(): Flow<List<Genres>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genres>)

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()
}