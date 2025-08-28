package com.example.fitness_app_as.data.local.repository

import com.example.fitness_app_as.data.local.entity.ExerciseCache
import com.example.fitness_app_as.data.local.entity.PlaylistCache
import com.example.fitness_app_as.domain.Exercise
import com.example.fitness_app_as.domain.Playlist

interface Repository {
    suspend fun getExerciseItems(): List<Exercise>

    suspend fun insertExercises(exercises: List<ExerciseCache>)

    suspend fun getAllPlaylists(): List<Playlist>

    suspend fun insertAllPlaylists(playlists: List<PlaylistCache>)

}