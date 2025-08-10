package com.example.fitness_app_as.network.module

import com.example.fitness_app_as.network.exercise.ExerciseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideExerciseService(retrofit: Retrofit): ExerciseService =
        retrofit.create(ExerciseService::class.java)
}