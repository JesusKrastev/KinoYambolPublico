package com.kinoyamboladmin.ui.features.seemovies

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.ui.features.MovieUiState
import com.kinoyamboladmin.ui.features.toMovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    var filtersState: FilterUiState by mutableStateOf(FilterUiState(all = true))
        private set
    var newMoviesState: List<MovieListUiState> by mutableStateOf(listOf<MovieListUiState>())
        private set
    var moviesState: List<MovieListUiState> by mutableStateOf(listOf<MovieListUiState>())
        private set

    private suspend fun getMovies(): List<MovieListUiState> = movieRepository.get()
        .map { it.toMovieListUiState() }
        .toList()

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                moviesState = getMovies()
                newMoviesState = getMovies()
            } catch (e: Exception) {
                Log.d("MoviesListViewModel", "Cargando peliculas: ${e.localizedMessage}")
            }
        }
    }

    init {
        loadMovies()
    }

    fun onListMoviesEvent(event: MoviesListEvent) {
        when(event) {
            is MoviesListEvent.OnClickMovie -> {
                event.onNavigateMovieSheet(event.movieId)
            }
            is MoviesListEvent.OnCreateMovie -> {

            }
            is MoviesListEvent.OnFilterChange -> {
                filtersState = event.filters
            }
        }
    }
}