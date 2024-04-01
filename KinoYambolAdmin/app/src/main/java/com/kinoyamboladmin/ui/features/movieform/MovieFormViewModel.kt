package com.kinoyamboladmin.ui.features.movieform

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinoyamboladmin.data.GenderRepository
import com.kinoyamboladmin.data.LanguageRepository
import com.kinoyamboladmin.data.MovieRepository
import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.models.Schedule
import com.kinoyamboladmin.ui.features.GenderUiState
import com.kinoyamboladmin.ui.features.toGenderUiState
import com.kinoyamboladmin.utilities.error_handling.InformationStateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class MovieFormViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genderRepository: GenderRepository,
    private val languageRepository: LanguageRepository,
    private val movieValidator: MovieValidator
) : ViewModel() {
    class MovieViewModelException(message: String) : Exception(message)

    var editingContactExistingState: Boolean = false
        private set
    var genreList: List<GenderUiState> by mutableStateOf(emptyList())
    var movieState: MovieUiState by mutableStateOf(MovieUiState())
        private set
    var movieValidationState by mutableStateOf(MovieValidationUiState())
        private set
    var showDatePickerDialogState by mutableStateOf(false)
        private set
    var showTimePickerDialogState by mutableStateOf(false)
        private set
    var showInformationErrorScheduleDialogState by mutableStateOf(false)
        private set
    var informationState: InformationStateUiState by mutableStateOf(InformationStateUiState.Hidden())
        private set

    init {
        loadGenders()
        if (movieState.id > 0) initializeNewMovie() // When you create a new Movie
    }

    private fun initializeNewMovie() {
        viewModelScope.launch {
            val languages: List<Language> = languageRepository.get()
            val schedules: List<Schedule> = languages.map { language ->
                Schedule(
                    id = 0,
                    language = language,
                    movieSchedule = hashMapOf()
                )
            }
            movieState.copy(schedules = schedules)
        }
    }

    fun setMovie(movieId: Int) {
        viewModelScope.launch {
            val movie: Movie = movieRepository.get(movieId)
                ?: throw MovieViewModelException("El id $movieId no existe en la base de datos")
            movieState = movie.toMovieUiState().copy()
            movieValidationState = movieValidator.validate(movieState)
            editingContactExistingState = true
        }
    }

    fun clearMovieState() {
        editingContactExistingState = false
        movieState = MovieUiState()
        movieValidationState = MovieValidationUiState()
    }

    private suspend fun getGenders(): List<GenderUiState> = genderRepository.get().map { it.toGenderUiState() }
    private fun loadGenders() {
        viewModelScope.launch {
            genreList = getGenders()
        }
    }

    private fun canChangeSchedules(): Boolean = (movieState.schedules.all { it.movieSchedule.size <= 1 } && editingContactExistingState) || !editingContactExistingState

    private fun onConfirmDateDialog(date: LocalDate) {
        val newSchedules: MutableList<Schedule> = movieState.schedules.toMutableList()
        val newSchedule: Schedule = newSchedules.find { it.language.name == movieState.selectedLanguage } ?: throw MovieViewModelException("No se encontró el idioma seleccionado")
        val indexSchedule: Int = newSchedules.indexOf(newSchedule)

        if (movieState.selectedDate == null) {
            // Save new date in the schedule
            newSchedule.movieSchedule[date] = emptyList()
        } else {
            // Modified existing date in the schedule
            newSchedule.movieSchedule[date] = newSchedule.movieSchedule[movieState.selectedDate]!!
            newSchedule.movieSchedule.remove(movieState.selectedDate!!)
        }
        newSchedules[indexSchedule] = newSchedule
        movieState = movieState.copy(schedules = newSchedules)
        movieValidationState = movieValidationState.copy(
            scheduleValidation = movieValidator.scheduleValidator.validate(movieState.schedules)
        )
    }

    private fun onRemoveDate() {
        val newSchedules: MutableList<Schedule> = movieState.schedules.toMutableList()
        val newSchedule: Schedule = newSchedules.find { it.language.name == movieState.selectedLanguage } ?: throw MovieViewModelException("No se encontró el idioma seleccionado")
        val index: Int = newSchedules.indexOf(newSchedule)

        movieState.selectedDate?.let {
            newSchedule.movieSchedule.remove(movieState.selectedDate!!)
            newSchedules[index] = newSchedule
            movieState = movieState.copy(schedules = newSchedules)
            movieState = movieState.copy(selectedDate = null)
        }
    }

    private fun onConfirmHourDialog(hour: LocalTime) {
        val newSchedules: MutableList<Schedule> = movieState.schedules.toMutableList()
        val newSchedule: Schedule = newSchedules.find { it.language.name == movieState.selectedLanguage } ?: throw MovieViewModelException("No se encontró el idioma seleccionado")
        val indexSchedule: Int = newSchedules.indexOf(newSchedule)
        val newHours: MutableList<LocalTime> = newSchedule.movieSchedule[movieState.selectedDate!!]?.toMutableList() ?: mutableListOf()

        if(movieState.selectedHour != null) newHours.remove(movieState.selectedHour)
        newHours.add(hour)
        newSchedule.movieSchedule[movieState.selectedDate!!] = newHours
        newSchedules[indexSchedule] = newSchedule
        movieState = movieState.copy(schedules = newSchedules)
    }

    private fun onRemoveHour() {
        val newSchedules: MutableList<Schedule> = movieState.schedules.toMutableList()
        val newSchedule: Schedule = newSchedules.find { it.language.name == movieState.selectedLanguage } ?: throw MovieViewModelException("No se encontró el idioma seleccionado")
        val indexSchedule: Int = newSchedules.indexOf(newSchedule)
        val newHours: MutableList<LocalTime> = newSchedule.movieSchedule[movieState.selectedDate!!]?.toMutableList() ?: mutableListOf()

        newHours.remove(movieState.selectedHour)
        newSchedule.movieSchedule[movieState.selectedDate!!] = newHours
        newSchedules[indexSchedule] = newSchedule
        movieState = movieState.copy(schedules = newSchedules)
    }

    fun onMovieFormEvent(event: MovieFormEvent) {
        when (event) {
            is MovieFormEvent.OnChangeFrontPage -> {

            }
            is MovieFormEvent.OnChangeLettersImage -> {

            }
            is MovieFormEvent.OnChangeName -> {
                movieState = movieState.copy(name = event.name)
                movieValidationState = movieValidationState.copy(
                    nameValidation = movieValidator.nameValidator.validate(event.name)
                )
            }
            is MovieFormEvent.OnChangeDescription -> {
                movieState = movieState.copy(description = event.description)
                movieValidationState = movieValidationState.copy(
                    validationDescription = movieValidator.validatorDescription.validate(event.description)
                )
            }
            is MovieFormEvent.OnChangeGenders -> {
                val newGenders: MutableList<GenderUiState> = movieState.genders.toMutableList()
                if (newGenders.find { it.id == event.gender.id } != null ) newGenders.remove(event.gender) else newGenders.add(event.gender)
                movieState = movieState.copy(genders = newGenders)
                movieValidationState = movieValidationState.copy(
                    gendersValidation = movieValidator.gendersValidator.validate(
                        movieState.genders.joinToString("") { it.name }
                    )
                )
            }
            is MovieFormEvent.OnChangeTrailerLink -> {
                movieState = movieState.copy(trailerLink = event.trailerLink)
                movieValidationState = movieValidationState.copy(
                    trailerValidation = movieValidator.trailerValidator.validate(event.trailerLink)
                )
            }
            is MovieFormEvent.OnChangePrice -> {
                movieState = movieState.copy(price = event.price)
                movieValidationState = movieValidationState.copy(
                    priceValidation = movieValidator.priceValidator.validate(event.price)
                )
            }
            is MovieFormEvent.OnChangeLanguage -> {
                movieState = movieState.copy(selectedLanguage = event.language)
            }
            is MovieFormEvent.OnChangeDate -> {
                movieState = movieState.copy(selectedDate = if (movieState.selectedDate == event.date) null else event.date)
            }
            is MovieFormEvent.OnShowDialogDate -> {
                // Can change schedules if the movie is new or all schedules have only one date and you can add new dates in any moment
                if(canChangeSchedules() || movieState.selectedDate == null) {
                    showDatePickerDialogState = !showDatePickerDialogState
                } else {
                    showInformationErrorScheduleDialogState = true
                }
            }
            is MovieFormEvent.OnConfirmDateDialog -> {
                onConfirmDateDialog(event.date)
            }
            is MovieFormEvent.OnRemoveDate -> {
                if(canChangeSchedules()) {
                    onRemoveDate()
                } else {
                    showInformationErrorScheduleDialogState = true
                }
            }
            is MovieFormEvent.OnChangeHour -> {
                movieState = movieState.copy(selectedHour = if(movieState.selectedHour == event.hour) null else event.hour)
            }
            is MovieFormEvent.OnShowDialogHour -> {
                if(canChangeSchedules() || movieState.selectedHour == null) {
                    showTimePickerDialogState = !showTimePickerDialogState
                } else {
                    showInformationErrorScheduleDialogState = true
                }
            }
            is MovieFormEvent.OnRemoveHour -> {
                if(canChangeSchedules()) {
                    onRemoveHour()
                } else {
                    showInformationErrorScheduleDialogState = true
                }
            }
            is MovieFormEvent.OnConfirmHourDialog -> {
                onConfirmHourDialog(event.hour)
            }
            is MovieFormEvent.OnShowErrorDialog -> {
                showInformationErrorScheduleDialogState = !showInformationErrorScheduleDialogState
            }
            is MovieFormEvent.OnSaveMovie -> {
                movieValidationState = movieValidator.validate(movieState)
                if (!movieValidationState.hasError) {
                    val movie: Movie = movieState.toMovie()

                    viewModelScope.launch {
                        if (editingContactExistingState) {
                            movieRepository.update(movie)
                        } else {
                            movieRepository.insert(movie)
                        }
                    }
                } else {
                    informationState = InformationStateUiState.Error(
                        message = movieValidationState.errorMessage!!,
                        onDismiss = { informationState = InformationStateUiState.Hidden() }
                    )
                    Log.e("MovieFormViewModel", "Error al guardar la película")
                }
            }
        }
    }
}