package com.example.fitness_app_as.data.mapper

import com.example.fitness_app_as.data.local.entity.PlaylistCache
import com.example.fitness_app_as.domain.Playlist

fun List<Playlist>.mapToLocal(): List<PlaylistCache> {
    return this.map {
        PlaylistCache(
            id = it.id,
            name = it.name,
            exerciseIds = it.exerciseIds
        )
    }
}

fun List<PlaylistCache>.mapToDomain(): List<Playlist> {
    return this.map {
        Playlist(
            id = it.id,
            name = it.name,
            exerciseIds = it.exerciseIds
        )
    }
}