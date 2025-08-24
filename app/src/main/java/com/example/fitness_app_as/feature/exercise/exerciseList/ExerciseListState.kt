package com.example.fitness_app_as.feature.exercise.exerciseList

import com.example.fitness_app_as.domain.Exercise

sealed class ExerciseListState {
    data class LoadExercises(val exercises: List<Exercise>) : ExerciseListState()
}