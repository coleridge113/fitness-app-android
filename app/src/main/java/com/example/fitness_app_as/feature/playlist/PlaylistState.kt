package com.example.fitness_app_as.feature.playlist

import com.example.fitness_app_as.domain.Playlist

sealed class PlaylistState {
    data class LoadPlaylists(val playlists: List<Playlist>) : PlaylistState()
}