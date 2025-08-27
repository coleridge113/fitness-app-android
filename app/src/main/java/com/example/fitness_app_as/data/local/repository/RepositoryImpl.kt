package com.example.fitness_app_as.data.local.repository

import com.example.fitness_app_as.data.local.AppDatabase
import com.example.fitness_app_as.data.local.entity.ExerciseCache
import com.example.fitness_app_as.data.mapper.mapToDomain
import com.example.fitness_app_as.data.mapper.mapToLocal
import com.example.fitness_app_as.domain.Exercise
import com.example.fitness_app_as.network.exercise.ExerciseRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val exerciseRemoteSource: ExerciseRemoteSource
) : Repository {
    override suspend fun getExerciseItems(): List<Exercise> {
        return withContext(Dispatchers.IO) {
            val cached = db.exerciseDao().getAllExercises()
            if(cached.isNotEmpty()){
                cached.mapToDomain()
            } else {
                val remote = exerciseRemoteSource.getExerciseItems()
                db.exerciseDao().insertExercises(remote.mapToLocal())
                remote
            }
        }
    }

    override suspend fun insertExercises(exercises: List<ExerciseCache>) {
        return db.exerciseDao().insertExercises(exercises)
    }

}