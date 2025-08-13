package com.example.fitness_app_as.feature.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        setupListeners()
    }

    private fun setupView() {
        val exercise = intent.getSerializableExtra("exercise", Exercise::class.java)

        exercise?.let {
            with(binding){
                toolbar.toolbarTitle.text = it.name

                with(itemExerciseDetails){
                    primaryMuscle.text = it.primaryMuscle.replaceFirstChar { char -> char.uppercase() }
                    descriptionText.text = it.description
                    equipmentName.text = it.equipment

                    Utilities.embedYTLink(itemExerciseDetails.ytLink, exercise.name)
                }

            }
        }
    }

    private fun setupListeners() {
        with(binding){
            toolbar.backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}