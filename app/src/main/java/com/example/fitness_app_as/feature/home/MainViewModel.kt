package com.example.fitness_app_as.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_app_as.network.exercise.ExerciseRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exerciseRemoteSource: ExerciseRemoteSource
) : ViewModel() {

    private val mutableMainState: MutableSharedFlow<MainState> = MutableSharedFlow()
    val mainState = mutableMainState.asSharedFlow()

    fun getExerciseItems() {
        viewModelScope.launch {
            val exercises = exerciseRemoteSource.getExerciseItems()
            mutableMainState.emit(MainState.LoadExercises(exercises))
        }
    }

}