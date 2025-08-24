package com.example.fitness_app_as.network.exercise

import com.example.fitness_app_as.domain.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExerciseRemoteSource @Inject constructor(
    private val exerciseService: ExerciseService
) {

    suspend fun getExerciseItems(): List<Exercise> {
        return withContext(Dispatchers.IO) {
            val response = exerciseService.getExerciseItems()
            if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
        }
    }
}