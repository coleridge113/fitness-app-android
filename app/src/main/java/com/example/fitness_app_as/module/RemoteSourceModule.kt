package com.example.fitness_app_as.module

import com.example.fitness_app_as.network.exercise.ExerciseRemoteSource
import com.example.fitness_app_as.network.exercise.ExerciseService
import com.example.fitness_app_as.network.playlist.PlaylistRemoteSource
import com.example.fitness_app_as.network.playlist.PlaylistService
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
    fun provideExerciseRemoteSource(exerciseService: ExerciseService): ExerciseRemoteSource {
        return ExerciseRemoteSource(exerciseService)
    }

    @Provides
    @Singleton
    fun providePlaylistRemoteSource(playlistService: PlaylistService): PlaylistRemoteSource {
        return PlaylistRemoteSource(playlistService)
    }
}