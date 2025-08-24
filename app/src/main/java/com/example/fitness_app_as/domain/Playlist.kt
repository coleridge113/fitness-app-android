package com.example.fitness_app_as.domain

import java.io.Serializable

data class Playlist(
    val id: Int,
    val name: String,
    val exerciseIds: List<Int>
) : Serializable
