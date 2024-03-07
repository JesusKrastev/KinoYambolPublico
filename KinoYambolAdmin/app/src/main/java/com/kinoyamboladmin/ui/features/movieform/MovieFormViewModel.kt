package com.kinoyamboladmin.ui.features.movieform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.data.ScheduleRepository
import com.kinoyamboladmin.ui.features.MovieUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFormViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val scheduleRepository: ScheduleRepository
): ViewModel() {
    class MovieViewModelException(message: String) : Exception(message)

    var editingContactExistingState: Boolean = false
        private set
    var movieState by mutableStateOf(MovieUiState())
        private set


}