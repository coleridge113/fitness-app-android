package com.example.fitness_app_as.network.module

import com.example.fitness_app_as.network.exercise.ExerciseRemoteSource
import com.example.fitness_app_as.network.exercise.ExerciseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteSourceModule {

    @Provides
    @Singleton
    fun providesRemoteSource(exerciseService: ExerciseService): ExerciseRemoteSource {
        return ExerciseRemoteSource(exerciseService)
    }
}