package com.kinoyamboladmin.ui.features.movieform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.data.ScheduleRepository
import com.kinoyamboladmin.ui.features.MovieUiState
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

    fun setMovieState(idContacto: Int) {
        viewModelScope.launch {
            editandoContactoExistenteState = true
            val c: Contacto = contactoRepository.get(idContacto)
                ?: throw ContactoViewModelException("El id $idContacto no existe en la base de datos")
            contactoState = c.toContactoUiState()
            validacionContactoState = validadorContacto.valida(contactoState)
        }
    }
}