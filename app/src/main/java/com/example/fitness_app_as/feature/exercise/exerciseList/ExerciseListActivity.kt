package com.example.fitness_app_as.feature.exercise.exerciseList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.R
import com.example.fitness_app_as.databinding.ActivityExerciseListBinding
import com.example.fitness_app_as.feature.exercise.exerciseDetails.ExerciseDetailsActivity
import com.example.fitness_app_as.feature.playlist.PlaylistActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val TAG = "ExerciseListActivity"

@AndroidEntryPoint
class ExerciseListActivity : AppCompatActivity() {
    private val exerciseListViewModel: ExerciseListViewModel by viewModels()
    private lateinit var binding: ActivityExerciseListBinding
    private lateinit var exerciseListAdapter: ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        loadData()
        setupView()
        setupListeners()
    }


    private fun loadData(){
        exerciseListViewModel.getExerciseItems()
    }

    private fun setupView() {
        setupRecyclerView()
        setupToolbar()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            exerciseListViewModel.exerciseListState.collect { state ->
                binding.swipeRefresh.isRefreshing = false
                when(state) {
                    is ExerciseListState.LoadExercises -> {
                        exerciseListAdapter.exercises = state.exercises
                    }
                }
            }
        }
    }

    private fun setupListeners(){
        with(binding){
            swipeRefresh.setOnRefreshListener {
                exerciseListViewModel.getExerciseItems()
            }

            // for testing purposes
            toolbar.toolbarTitle.setOnClickListener {
                val intent = Intent(this@ExerciseListActivity, PlaylistActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            exerciseListAdapter = ExerciseListAdapter { exercise ->
                val intent = Intent(this@ExerciseListActivity, ExerciseDetailsActivity::class.java)
                intent.putExtra("exercise", exercise)
                startActivity(intent)
            }
            adapter = exerciseListAdapter
            layoutManager = LinearLayoutManager(this@ExerciseListActivity)
        }
    }

    private fun setupToolbar() {
        with(binding.toolbar){
            toolbarTitle.text = getString(R.string.exercise_toolbar_title)
            backButton.visibility = View.GONE
        }

    }
}