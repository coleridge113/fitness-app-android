package com.example.fitness_app_as.feature.playlist.playlistDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.databinding.FragmentPlaylistDetailsBinding
import com.example.fitness_app_as.domain.Playlist
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitness_app_as.feature.playlist.PlaylistState
import com.example.fitness_app_as.feature.playlist.PlaylistViewModel
import kotlinx.coroutines.launch

class PlaylistDetailsFragment() : Fragment() {
    private val playlistViewModel: PlaylistViewModel by activityViewModels()
    private lateinit var playlistDetailsAdapter: PlaylistDetailsAdapter
    private lateinit var binding: FragmentPlaylistDetailsBinding

    private var playlist: Playlist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playlist = arguments?.getSerializable("playlist", Playlist::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        setupObservers()
        setupView()
    }

    private fun loadData(){
        playlist?.let { playlistViewModel.getExercisesForPlaylist(it) }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            playlistViewModel.playlistState.collect { state ->
                when(state) {
                    is PlaylistState.LoadExercises -> {
                        playlistDetailsAdapter.exercises = state.exercises
                    }

                    is PlaylistState.LoadPlaylists -> TODO()
                }
            }

        }
    }

    private fun setupView() {
        setupToolbar()

        with(binding.itemPlaylistDetails) {
            tvPlaylistName.text = playlist?.name
        }

        setupRecycleView()
    }

    private fun setupToolbar() {
        binding.toolbar.toolbarTitle.text = ""
    }

    private fun setupRecycleView() {
        with(binding.itemPlaylistDetails.rvExerciseList){
            playlistDetailsAdapter = PlaylistDetailsAdapter()
            adapter = playlistDetailsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }
}