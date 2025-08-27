package com.example.fitness_app_as.feature.exercise.exerciseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_app_as.data.interactor.GetExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val getExerciseUseCase: GetExerciseUseCase,
) : ViewModel() {

    private val _exerciseListState: MutableSharedFlow<ExerciseListState> = MutableSharedFlow()
    val exerciseListState = _exerciseListState.asSharedFlow()

    fun getExerciseItems() {
        viewModelScope.launch {
            val exercises = getExerciseUseCase()
            _exerciseListState.emit(ExerciseListState.LoadExercises(exercises))
        }
    }

}