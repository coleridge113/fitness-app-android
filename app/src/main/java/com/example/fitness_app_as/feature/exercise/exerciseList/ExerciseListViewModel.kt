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

    private val _exerciseListState: MutableSharedFlow<ExerciseListState> = MutableSharedFlow()
    val exerciseListState = _exerciseListState.asSharedFlow()

    fun getExerciseItems() {
        viewModelScope.launch {
            val exercises = exerciseRemoteSource.getExerciseItems()
            _exerciseListState.emit(ExerciseListState.LoadExercises(exercises))
        }
    }

}