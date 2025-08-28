package com.example.fitness_app_as.data.interactor

import com.example.fitness_app_as.data.local.repository.Repository
import com.example.fitness_app_as.domain.Playlist
import javax.inject.Inject

class GetAllPlaylistsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Playlist> {
        return repository.getAllPlaylists()
    }
}