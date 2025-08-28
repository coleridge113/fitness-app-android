package com.example.fitness_app_as.data.mapper

import com.example.fitness_app_as.data.local.entity.ExerciseCache
import com.example.fitness_app_as.domain.Exercise

fun List<Exercise>.mapToLocal(): List<ExerciseCache> {
    return this.map {
        ExerciseCache(
            id = it.id,
            name = it.name,
            primaryMuscle = it.primaryMuscle,
            secondaryMuscle = it.secondaryMuscle,
            equipment = it.equipment,
            description = it.description
        )
    }
}

fun List<ExerciseCache>.mapToDomain(): List<Exercise> {
    return this.map {
        Exercise(
            id = it.id,
            name = it.name,
            primaryMuscle = it.primaryMuscle,
            secondaryMuscle = it.secondaryMuscle ?: "",
            equipment = it.equipment,
            description = it.description
        )
    }
}
