package com.kinoyamboladmin.ui.features.moviesheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSheetViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    class MovieViewModelException(message: String) : Exception(message)

    var selectedMovieState: MovieSheetUiState by mutableStateOf(MovieSheetUiState())
        private set

    fun setMovie(idMovie: Int) {
        viewModelScope.launch {
            val movie: Movie = movieRepository.get(idMovie) ?: throw MovieViewModelException("El id $idMovie no existe en la base de datos")
            selectedMovieState = movie.toMovieSheetUiState()
        }
    }

    fun onMovieSheetEvent(event: MovieSheetEvent) {
        when(event) {
            is MovieSheetEvent.OnEditMovie -> {
                event.onNavigateEditMovie(selectedMovieState!!.id)
            }
        }
    }
}