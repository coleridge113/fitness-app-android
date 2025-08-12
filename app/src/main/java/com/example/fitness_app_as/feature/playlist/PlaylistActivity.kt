package com.example.fitness_app_as.feature.playlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.R
import com.example.fitness_app_as.databinding.ActivityPlaylistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaylistActivity : AppCompatActivity() {
    private val playlistViewModel: PlaylistViewModel by viewModels()
    private lateinit var binding: ActivityPlaylistBinding
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        loadData()
        setupView()
        setupListeners()
    }

    private fun loadData() {
        playlistViewModel.getPlaylistItems()
    }

    private fun setupView() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupListeners(){
        with(binding){
            swipeRefresh.setOnRefreshListener {
                playlistViewModel.getPlaylistItems()
            }
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            playlistViewModel.playlistState.collect { state ->
                binding.swipeRefresh.isRefreshing = false
                when(state) {
                    is PlaylistState.LoadPlaylists -> {
                        playlistAdapter.playlists = state.playlists
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            playlistAdapter = PlaylistAdapter {
                TODO()
            }
            adapter = playlistAdapter
            layoutManager = LinearLayoutManager(this@PlaylistActivity)
        }
    }

    private fun setupToolbar() {
        with(binding.toolbar){
            toolbarTitle.text = getString(R.string.playlist_toolbar_title)
            backButton.setOnClickListener { finish() }
        }
    }
}