package com.example.fitness_app_as.feature.playlist

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_app_as.data.interactor.PlaylistUseCase
import com.example.fitness_app_as.domain.Playlist
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val playlistUseCase: PlaylistUseCase,
) : ViewModel() {

    private var _playlistState: MutableSharedFlow<PlaylistState> = MutableSharedFlow()
    val playlistState = _playlistState.asSharedFlow()

    fun getPlaylistItems() {
        viewModelScope.launch {
            val playlists = playlistUseCase.getAllPlaylists()
            _playlistState.emit(PlaylistState.LoadPlaylists(playlists))
        }

    }

    fun getExercisesForPlaylist(playlist: Playlist) {
        viewModelScope.launch {
            val exercises = playlistUseCase.getExercisesForPlaylist(playlist)
            _playlistState.emit(PlaylistState.LoadExercises(exercises))
        }
    }
}