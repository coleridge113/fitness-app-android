package com.example.fitness_app_as.network

import com.example.fitness_app_as.network.exercise.ExerciseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ExerciseService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExerciseService::class.java)
    }
}