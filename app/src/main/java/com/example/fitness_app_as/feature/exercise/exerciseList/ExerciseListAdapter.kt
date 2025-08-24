package com.example.fitness_app_as.feature.exercise.exerciseList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness_app_as.databinding.CardItemSmallBinding
import com.example.fitness_app_as.domain.Exercise

class ExerciseListAdapter(
    private val onItemClick:(Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseItemViewHolder>() {

    inner class ExerciseItemViewHolder(val binding: CardItemSmallBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(
            oldItem: Exercise,
            newItem: Exercise
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Exercise,
            newItem: Exercise
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var exercises: List<Exercise>
        get() = differ.currentList
        set(value) { differ.submitList(value) }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseItemViewHolder {
        val binding = CardItemSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExerciseItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val exercise = exercises[position]
            textView.text = exercise.name
            textView.setOnClickListener {
                onItemClick(exercise)
            }
        }
    }

    override fun getItemCount(): Int = exercises.size

}