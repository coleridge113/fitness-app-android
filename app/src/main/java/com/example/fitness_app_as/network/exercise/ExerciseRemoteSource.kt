package com.example.fitness_app_as.network.exercise

import com.example.fitness_app_as.domain.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ExerciseRemoteSource @Inject constructor(
    private val exerciseService: ExerciseService
) {

    suspend fun getExerciseItems(): Response<List<Exercise>> =
        withContext(Dispatchers.IO) {
            exerciseService.getExerciseItems()
        }


}