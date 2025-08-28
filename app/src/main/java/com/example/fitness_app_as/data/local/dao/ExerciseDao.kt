package com.example.fitness_app_as.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitness_app_as.data.local.entity.ExerciseCache

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): List<ExerciseCache>

    @Insert
    fun insertExercises(exercises: List<ExerciseCache>)
}