package com.example.fitness_app_as.network.exercise

import com.example.fitness_app_as.domain.Exercise
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExerciseService {
    @GET("exercise/all")
    suspend fun getExerciseItems(): Response<List<Exercise>>

    @GET("exercise")
    suspend fun getExerciseById(@Query("id") id: Int): Response<Exercise>

    @GET("exercise/muscle/{primaryMuscle}")
    suspend fun getExerciseByPrimaryMuscle(@Path("primaryMuscle") primaryMuscle: String): Response<List<Exercise>>


}