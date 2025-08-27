package com.example.fitness_app_as.data.local.repository

import com.example.fitness_app_as.data.local.entity.ExerciseCache
import com.example.fitness_app_as.domain.Exercise

interface Repository {
    suspend fun getExerciseItems(): List<Exercise>

    suspend fun insertExercises(exercises: List<ExerciseCache>)
}