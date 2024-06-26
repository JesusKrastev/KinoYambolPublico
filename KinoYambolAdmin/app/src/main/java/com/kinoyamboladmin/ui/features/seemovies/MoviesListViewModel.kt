package com.kinoyamboladmin.ui.features.seemovies

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.R
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.error_handling.InformationStateUiState
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
    var informationState: InformationStateUiState by mutableStateOf(InformationStateUiState.Hidden())
        private set

    private suspend fun getMovies(): List<MovieListUiState> =
        if(filtersState.all)
            movieRepository.get().map { it.toMovieListUiState() }.toList()
        else if(filtersState.today)
            movieRepository.getTodaysMovies().map { it.toMovieListUiState() }.toList()
        else
            movieRepository.getUpcomingMovies().map { it.toMovieListUiState() }.toList()

    private suspend fun getMovieReleases(): List<MovieListUiState> = movieRepository.getMovieReleases().map { it.toMovieListUiState() }.toList()

    private fun loadMovies() {
        informationState = InformationStateUiState.Information(
            message = UiText.StringResource(R.string.label_loading_movies),
            showProgress = true
        )
        viewModelScope.launch {
            try {
                moviesState = getMovies()
                informationState = InformationStateUiState.Hidden()
            } catch (e: Exception) {
                informationState = InformationStateUiState.Error(
                    message = UiText.StringResource(R.string.label_error_loading_movies),
                    onDismiss = { informationState = InformationStateUiState.Hidden() }
                )
                Log.d("MoviesListViewModel", "Cargando peliculas: ${e.localizedMessage}")
            }
        }
    }

    private fun loadNewMovies() {
        viewModelScope.launch {
            try {
                newMoviesState = getMovieReleases()
            } catch (e: Exception) {
                Log.d("MoviesListViewModel", "Cargando peliculas: ${e.localizedMessage}")
            }
        }
    }

    init {
        loadMovies()
        loadNewMovies()
    }

    fun onListMoviesEvent(event: MoviesListEvent) {
        when(event) {
            is MoviesListEvent.OnClickMovie -> {
                event.onNavigateMovieSheet(event.movieId)
            }
            is MoviesListEvent.OnFilterChange -> {
                filtersState = event.filters
                loadMovies()
            }
        }
    }
}