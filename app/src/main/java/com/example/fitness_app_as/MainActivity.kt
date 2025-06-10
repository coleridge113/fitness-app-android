package com.example.fitness_app_as

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitness_app_as.adapter.ExerciseAdapter
import com.example.fitness_app_as.databinding.ActivityMainBinding
import com.example.fitness_app_as.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import kotlin.toString

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.getExerciseItems()
            } catch (e: IOException) {
                Log.e(TAG, "IOException: $e")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG,"HttpException: $e")
                return@launch
            }
            if(response.isSuccessful && response.body() != null){
                setupRecyclerView()
                exerciseAdapter.exercises = response.body()!!
            } else {
                Log.e(TAG,"Response not successful: ${response.isSuccessful}, ${response.code()}")
            }

            try{
                val ex = RetrofitInstance.api.getExerciseById(4).body()
                Log.d(TAG, ex.toString())
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        exerciseAdapter = ExerciseAdapter { exercise ->
            val intent = Intent(this@MainActivity, ExerciseActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)
        }
        adapter = exerciseAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}