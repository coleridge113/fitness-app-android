package com.example.fitness_app_as.feature.exercise

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness_app_as.databinding.ActivityExerciseDetailsBinding
import com.example.fitness_app_as.domain.Exercise

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
                primaryMuscle.text = it.primaryMuscle.replaceFirstChar { char -> char.uppercase() }
                descriptionText.text = it.description
                equipmentName.text = it.equipment

                val link = "https://www.youtube.com/results?search_query=" + it.name.split(" ").joinToString("+")
                val htmlText = "<a href=\"$link\">${it.name}</a>"
                ytLink.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
                ytLink.movementMethod = LinkMovementMethod.getInstance()

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