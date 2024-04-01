package com.kinoyamboladmin.ui.features.movieform

import androidx.compose.ui.graphics.ImageBitmap
import com.kinoyamboladmin.ui.features.GenderUiState
import java.time.LocalDate
import java.time.LocalTime

sealed interface MovieFormEvent {
    data class OnChangeFrontPage(val image: ImageBitmap): MovieFormEvent
    data class OnChangeLettersImage(val image: ImageBitmap): MovieFormEvent
    data class OnChangeName(val name: String): MovieFormEvent
    data class OnChangeDescription(val description: String): MovieFormEvent
    data class OnChangeGenders(val gender: GenderUiState): MovieFormEvent
    data class OnChangeLanguage(val language: String): MovieFormEvent
    data class OnChangeDate(val date: LocalDate): MovieFormEvent
    data class OnChangeHour(val hour: LocalTime): MovieFormEvent
    data class OnChangeTrailerLink(val trailerLink: String): MovieFormEvent
    data class OnChangePrice(val price: String): MovieFormEvent
    data object OnShowDialogDate: MovieFormEvent
    data class OnConfirmDateDialog(val date: LocalDate): MovieFormEvent
    data object OnRemoveDate: MovieFormEvent
    data object OnShowDialogHour: MovieFormEvent
    data class OnConfirmHourDialog(val hour: LocalTime): MovieFormEvent
    data object OnRemoveHour: MovieFormEvent
    data object OnSaveMovie: MovieFormEvent
    data object OnShowErrorDialog: MovieFormEvent
}