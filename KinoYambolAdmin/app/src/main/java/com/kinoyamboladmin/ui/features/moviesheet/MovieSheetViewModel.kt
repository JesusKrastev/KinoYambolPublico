package com.kinoyamboladmin.ui.features.moviesheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.ui.features.MovieUiState
import com.kinoyamboladmin.ui.features.toMovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MovieSheetViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    class MovieViewModelException(message: String) : Exception(message)

    var selectedMovieState: MovieUiState by mutableStateOf(
        MovieUiState(
            id = -1,
            image = "",
            title = "",
            description = "",
            genders = emptyList(),
            languages = emptyList(),
            startDate = LocalDate.of(2000, 1, 1),
            endDate = LocalDate.of(2000, 1, 1),
            duration = 0,
            trailerLink = "",
            price = 0.0
        )
    )
        private set

    fun setMovieState(idMovie: Int) {
        viewModelScope.launch {
            val movie: Movie = movieRepository.get(idMovie) ?: throw MovieViewModelException("El id $idMovie no existe en la base de datos")
            selectedMovieState = movie.toMovieUiState()
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