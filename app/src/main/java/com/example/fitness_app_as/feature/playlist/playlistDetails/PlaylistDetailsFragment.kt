package com.example.fitness_app_as.feature.playlist.playlistDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.databinding.FragmentPlaylistDetailsBinding
import com.example.fitness_app_as.domain.Playlist

class PlaylistDetailsFragment : Fragment() {
    private lateinit var playlistDetailsAdapter: PlaylistDetailsAdapter
    private lateinit var binding: FragmentPlaylistDetailsBinding

    private var playlist: Playlist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playlist = arguments?.getSerializable("playlist") as? Playlist
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
        setupView()
    }

    private fun loadData(){
    }

    private fun setupView() {
        setupToolbar()

        with(binding.itemPlaylistDetails) {
            tvPlaylistName.text = playlist?.name
        }

        setupRecycleView()


    }

    private fun setupToolbar() {
        binding.toolbar.toolbarTitle.text = "Test"
    }

    private fun setupRecycleView() {
        with(binding.itemPlaylistDetails.rvExerciseList){
            playlistDetailsAdapter = PlaylistDetailsAdapter()
            adapter = playlistDetailsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }
}