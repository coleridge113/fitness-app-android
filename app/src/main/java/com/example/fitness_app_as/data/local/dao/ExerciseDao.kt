package com.example.fitness_app_as.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitness_app_as.data.local.entity.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): List<Exercise>
}