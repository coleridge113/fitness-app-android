package com.example.fitness_app_as.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitness_app_as.data.local.entity.Playlist

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlist")
    fun getAllPlaylists(): List<Playlist>
}