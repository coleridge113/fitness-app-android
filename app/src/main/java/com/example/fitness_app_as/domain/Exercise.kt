package com.example.fitness_app_as.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Exercise(
    val id: Int,
    val name: String,
    val primaryMuscle: String,
    val secondaryMuscle: String,
    val equipment: String,
    val description: String
) : Serializable