package com.tentwenty.assignment.data.upcomingmovies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "upcoming_movies")
data class UpcomingMovie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String?,
    val adult: Boolean,
    val video: Boolean,
    val release_date: String,
    val poster_path: String,
    val vote_average: Double,
    val genre_ids: List<Int>
) : Parcelable