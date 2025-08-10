package com.example.fitness_app_as.feature.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.feature.exercise.ExerciseDetailsActivity
import com.example.fitness_app_as.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        loadData()
        setupView()
    }


    private fun loadData(){
        mainViewModel.getExerciseItems()
    }

    private fun setupView() {
        setupRecyclerView()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            mainViewModel.mainState.collect { state ->
                when(state) {
                    is MainState.LoadExercises ->
                        mainAdapter.exercises = state.exercises
                    else -> Log.d(TAG, "setupObservers: Something went wrong")
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            mainAdapter = MainAdapter { exercise ->
                val intent = Intent(this@MainActivity, ExerciseDetailsActivity::class.java)
                intent.putExtra("exercise", exercise)
                startActivity(intent)
            }
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}