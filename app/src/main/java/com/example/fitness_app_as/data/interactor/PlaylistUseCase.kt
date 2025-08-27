package com.example.fitness_app_as.data.interactor

import com.example.fitness_app_as.data.local.repository.Repository
import com.example.fitness_app_as.domain.Exercise
import com.example.fitness_app_as.domain.Playlist
import javax.inject.Inject

class PlaylistUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getAllPlaylists(): List<Playlist> {
        return repository.getAllPlaylists()
    }

    suspend fun getExercisesForPlaylist(playlist: Playlist): List<Exercise> {
        return repository.getExerciseItems().filter { it.id in playlist.exerciseIds }
    }
}