package com.example.fitness_app_as.feature.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.feature.exercise.ExerciseDetailsActivity
import com.example.fitness_app_as.databinding.ActivityMainBinding
import com.example.fitness_app_as.domain.Exercise
import com.example.fitness_app_as.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private var exercises: List<Exercise> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupView()
    }


    private fun loadData(){
        lifecycleScope.launch {
            exercises = getExerciseData()
            mainAdapter.exercises = exercises
        }
    }

    private fun setupView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        mainAdapter = MainAdapter { exercise ->
            val intent = Intent(this@MainActivity, ExerciseDetailsActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)
        }
        adapter = mainAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private suspend fun getExerciseData(): List<Exercise> {
        val response = try {
            RetrofitInstance.api.getExerciseItems()
        } catch (e: IOException) {
            Log.e(TAG, "IOException: $e")
            return emptyList()
        } catch (e: HttpException) {
            Log.e(TAG,"HttpException: $e")
            return emptyList()
        }

        return response.body() as List<Exercise>
    }
}