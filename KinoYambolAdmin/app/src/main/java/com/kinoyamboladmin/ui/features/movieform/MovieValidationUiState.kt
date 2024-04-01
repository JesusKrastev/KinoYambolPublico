package com.kinoyamboladmin.ui.features.movieform

import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.CompositeValidation
import com.kinoyamboladmin.utilities.validation.Validation

data class MovieValidationUiState(
    val nameValidation: Validation = object : Validation {},
    val validationDescription: Validation = object : Validation {},
    val gendersValidation: Validation = object : Validation {},
    val trailerValidation: Validation = object : Validation {},
    val priceValidation: Validation = object : Validation {},
    val scheduleValidation: Validation = object : Validation {}
) : Validation {
    private var compositeValidation: CompositeValidation? = null

    private fun buildValidation(): CompositeValidation {
        compositeValidation = CompositeValidation()
            .add(nameValidation)
            .add(validationDescription)
            .add(gendersValidation)
            .add(trailerValidation)
            .add(priceValidation)
            .add(scheduleValidation)
        return compositeValidation!!
    }

    override val hasError: Boolean
        get() = compositeValidation?.hasError ?: buildValidation().hasError
    override val errorMessage: UiText?
        get() = compositeValidation?.errorMessage ?: buildValidation().errorMessage
}