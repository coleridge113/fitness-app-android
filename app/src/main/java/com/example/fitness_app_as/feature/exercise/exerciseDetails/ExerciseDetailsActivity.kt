package com.example.fitness_app_as.feature.exercise.exerciseDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fitness_app_as.R
import com.example.fitness_app_as.databinding.ActivityExerciseDetailsBinding
import com.example.fitness_app_as.domain.Exercise
import com.example.fitness_app_as.utilities.Utilities

class ExerciseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        setupToolbar()

        val exercise = intent.getSerializableExtra("exercise", Exercise::class.java)

        exercise?.let {
            with(binding.itemExerciseDetails){
                    exerciseName.text = it.name
                    primaryMuscle.text = it.primaryMuscle.replaceFirstChar { char -> char.uppercase() }
                    descriptionText.text = it.description
                    equipmentName.text = it.equipment

                    Utilities.embedYTLink(ytLink, it.name)
            }
        }
    }

    private fun setupToolbar() {
        with(binding){
            toolbar.toolbarTitle.text = ""
            toolbar.backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}