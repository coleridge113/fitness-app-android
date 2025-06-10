package com.example.fitness_app_as

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness_app_as.databinding.ActivityExerciseBinding
import com.example.fitness_app_as.domain.Exercise

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exercise = intent.getSerializableExtra("exercise", Exercise::class.java)

        exercise?.let {
            with(binding){
                exerciseName.text = it.name
                primaryMuscle.text = it.primaryMuscle.replaceFirstChar { char -> char.uppercase() }
                descriptionText.text = it.description
                equipmentName.text = it.equipment

                val link = "https://www.youtube.com/results?search_query=" + it.name.split(" ").joinToString("+")
                val htmlText = "<a href=\"$link\">${it.name}</a>"
                Log.d("ExerciseActivity", htmlText)
                ytLink.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
                ytLink.movementMethod = LinkMovementMethod.getInstance()
                
            }
        }
    }
}