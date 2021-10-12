package com.tentwenty.assignment.data.genres

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genres(
    @PrimaryKey val id: Int,
    val name: String
)