package com.example.fitness_app_as.data.interactor

import com.example.fitness_app_as.data.local.repository.Repository
import com.example.fitness_app_as.domain.Exercise
import javax.inject.Inject

class GetExerciseUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(): List<Exercise> {
        return repository.getExerciseItems()
    }
}