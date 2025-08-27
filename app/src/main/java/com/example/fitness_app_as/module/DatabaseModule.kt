package com.example.fitness_app_as.module

import android.content.Context
import androidx.room.Room
import com.example.fitness_app_as.data.local.AppDatabase
import com.example.fitness_app_as.data.local.dao.ExerciseDao
import com.example.fitness_app_as.data.local.dao.PlaylistDao
import com.example.fitness_app_as.data.local.repository.Repository
import com.example.fitness_app_as.data.local.repository.RepositoryImpl
import com.example.fitness_app_as.network.exercise.ExerciseRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "fitness_db").build()
    }

    @Provides
    fun provideExerciseDao(db: AppDatabase): ExerciseDao {
        return db.exerciseDao()
    }

    @Provides
    fun providePlaylistDao(db: AppDatabase): PlaylistDao {
        return db.playlistDao()
    }

    @Provides
    @Singleton
    fun provideRepository(db: AppDatabase, exerciseRemoteSource: ExerciseRemoteSource): Repository {
        return RepositoryImpl(db, exerciseRemoteSource)
    }
}