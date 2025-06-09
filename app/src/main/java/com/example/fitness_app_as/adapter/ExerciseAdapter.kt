package com.example.fitness_app_as.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness_app_as.databinding.ExerciseItemBinding
import com.example.fitness_app_as.domain.Exercise

class ExerciseAdapter() : RecyclerView.Adapter<ExerciseAdapter.ExerciseItemViewHolder>() {

    inner class ExerciseItemViewHolder(val binding: ExerciseItemBinding): RecyclerView.ViewHolder(binding.root){
        val textView = binding.textView
    }

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
        val binding = ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExerciseItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val exercise = exercises[position]
            textView.text = exercise.name
        }
    }

    override fun getItemCount(): Int = exercises.size

}