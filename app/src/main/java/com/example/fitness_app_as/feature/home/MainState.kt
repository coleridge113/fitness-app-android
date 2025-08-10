package com.example.fitness_app_as.feature.home

import com.example.fitness_app_as.domain.Exercise

sealed class MainState {
    data class LoadExercises(val exercises: List<Exercise>) : MainState()
}