package com.example.fitness_app_as.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitness_app_as.data.local.entity.PlaylistCache

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlist")
    fun getAllPlaylists(): List<PlaylistCache>

    @Insert
    fun insertAllPlaylists(playlists: List<PlaylistCache>)
}