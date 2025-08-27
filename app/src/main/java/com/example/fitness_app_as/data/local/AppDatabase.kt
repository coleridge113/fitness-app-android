package com.example.fitness_app_as.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import androidx.room.RoomDatabase
import com.example.fitness_app_as.data.local.dao.ExerciseDao
import com.example.fitness_app_as.data.local.dao.PlaylistDao
import com.example.fitness_app_as.data.local.entity.Exercise
import com.example.fitness_app_as.data.local.entity.Playlist
import com.example.fitness_app_as.data.local.converter.IntListConverter

@Database(entities = [Exercise::class, Playlist::class], version = 1)
@TypeConverters(IntListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun exerciseDao(): ExerciseDao
}