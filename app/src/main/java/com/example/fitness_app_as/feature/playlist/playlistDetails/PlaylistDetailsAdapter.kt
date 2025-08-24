package com.example.fitness_app_as.feature.playlist.playlistDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness_app_as.databinding.PlaylistDetailsExerciseItemBinding
import com.example.fitness_app_as.domain.Exercise

class PlaylistDetailsAdapter : RecyclerView.Adapter<PlaylistDetailsAdapter.PlaylistDetailsExerciseItemViewHolder>(){

    inner class PlaylistDetailsExerciseItemViewHolder(val binding: PlaylistDetailsExerciseItemBinding) : RecyclerView.ViewHolder(binding.root)

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
    ): PlaylistDetailsExerciseItemViewHolder {
        val binding = PlaylistDetailsExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistDetailsExerciseItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PlaylistDetailsExerciseItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val exercise = exercises[position]
            tvExercise.text = exercise.name
        }
    }

    override fun getItemCount(): Int { return exercises.size }




}