package com.example.fitness_app_as.network.playlist

import com.example.fitness_app_as.domain.Playlist
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaylistService {

    @GET("/playlist/")
    suspend fun getAllPlaylists(): Response<List<Playlist>>

    @GET("/playlist/{id}")
    suspend fun getPlaylistById(@Path("id") id: Int): Response<Playlist>

    @POST("/playlist/create")
    suspend fun createPlaylist(@Body playlist: Playlist): Response<String>

    @DELETE("/playlist/{id}")
    suspend fun deletePlaylist(@Path("id") id: Int): Response<String>
}