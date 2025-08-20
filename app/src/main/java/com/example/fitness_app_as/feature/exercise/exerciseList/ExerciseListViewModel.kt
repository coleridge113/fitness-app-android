package com.example.fitness_app_as.feature.exercise.exerciseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_app_as.network.exercise.ExerciseRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseRemoteSource: ExerciseRemoteSource
) : ViewModel() {

    private val mutableExerciseListState: MutableSharedFlow<ExerciseListState> = MutableSharedFlow()
    val exerciseListState = mutableExerciseListState.asSharedFlow()

    fun getExerciseItems() {
        viewModelScope.launch {
            val exercises = exerciseRemoteSource.getExerciseItems()
            mutableExerciseListState.emit(ExerciseListState.LoadExercises(exercises))
        }
    }

}