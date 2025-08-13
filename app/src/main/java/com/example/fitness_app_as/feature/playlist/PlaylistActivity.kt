package com.example.fitness_app_as.feature.playlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.R
import com.example.fitness_app_as.databinding.ActivityPlaylistBinding
import com.example.fitness_app_as.feature.playlist.playlistDetails.PlaylistDetailsFragment
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

                    is PlaylistState.LoadExercises -> {}
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            playlistAdapter = PlaylistAdapter(this@PlaylistActivity) { playlist ->
                playlistViewModel.getExercisesForPlaylist(playlist)

                val fragment = PlaylistDetailsFragment()
                val args = Bundle()
                args.putSerializable("playlist", playlist)
                fragment.arguments = args

                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                    )
                    .replace(R.id.playlist_details_container, fragment)
                    .addToBackStack(null)
                    .commit()
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