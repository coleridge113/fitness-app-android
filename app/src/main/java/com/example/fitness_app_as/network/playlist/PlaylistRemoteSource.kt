package com.example.fitness_app_as.network.playlist

import com.example.fitness_app_as.domain.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaylistRemoteSource @Inject constructor(
    private val playlistService: PlaylistService
) {

    suspend fun getAllPlaylists(): List<Playlist> {
        return withContext(Dispatchers.IO) {
            val response = playlistService.getAllPlaylists()
            if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
        }
    }

    suspend fun createPlaylist(playlist: Playlist): Boolean {
        return withContext(Dispatchers.IO) {
            val response = playlistService.createPlaylist(playlist)
            response.isSuccessful
        }

    }
}