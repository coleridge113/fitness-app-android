package com.example.fitness_app_as.feature.playlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness_app_as.databinding.CardItemSmallBinding
import com.example.fitness_app_as.domain.Playlist

class PlaylistAdapter(
    private val context: Context,
    private val onItemClick: (Playlist) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistItemViewHolder>() {

    inner class PlaylistItemViewHolder(val binding: CardItemSmallBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Playlist>() {
        override fun areItemsTheSame(
            oldItem: Playlist,
            newItem: Playlist
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Playlist,
            newItem: Playlist
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var playlists: List<Playlist>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(CardItemSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: PlaylistItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val playlist = playlists[position]
            textView.text = playlist.name
            textView.setOnClickListener {
                onItemClick(playlist)
            }
        }
    }

    override fun getItemCount(): Int { return playlists.size }
}