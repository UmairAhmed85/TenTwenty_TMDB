package com.tentwenty.assignment.data.upcomingmovies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UpcomingMovie(
    val id: Int,
    val title: String,
    val overview: String?,
    val adult: Boolean,
    val video: Boolean,
    val release_date: String,
    val poster_path: String,
    val vote_average: Double,
    val genre_ids: List<Int>
) : Parcelable