package com.example.fitness_app_as.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.fitness_app_as.data.local.converter.IntListConverter

@Entity(tableName = "playlist")
@TypeConverters(IntListConverter::class)
data class Playlist (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "exercise_ids") val exercises: List<Int>
)