package com.example.fitness_app_as.network

import com.example.fitness_app_as.domain.Exercise
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseService {
    @GET("all")
    suspend fun getExerciseItems(): Response<List<Exercise>>

    @GET("id/{id}")
    suspend fun getExerciseById(@Path("id") id: Int): Response<List<Exercise>>

    @GET("muscle/{primaryMuscle}")
    suspend fun getExerciseByPrimaryMuscle(@Path("primaryMuscle") primaryMuscle: String): Response<List<Exercise>>


}