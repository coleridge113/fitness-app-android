package com.example.fitness_app_as.feature.playlist

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_app_as.network.playlist.PlaylistRemoteSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val playlistRemoteSource: PlaylistRemoteSource
) : ViewModel() {

    private var mutablePlaylistState: MutableSharedFlow<PlaylistState> = MutableSharedFlow()
    val playlistState = mutablePlaylistState.asSharedFlow()

    fun getPlaylistItems() {
        viewModelScope.launch {
            val playlists = playlistRemoteSource.getAllPlaylists()
            mutablePlaylistState.emit(PlaylistState.LoadPlaylists(playlists))
        }

    }
}