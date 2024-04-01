package com.kinoyamboladmin.ui.features.movieform

import com.kinoyamboladmin.R
import com.kinoyamboladmin.models.Schedule
import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.CompositeValidator
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator
import com.kinoyamboladmin.utilities.validation.validators.EmptyScheduleValidator
import com.kinoyamboladmin.utilities.validation.validators.RealNumberValidator
import com.kinoyamboladmin.utilities.validation.validators.ValidatorNonEmptyText
import javax.inject.Inject

class MovieValidator @Inject constructor(): Validator<MovieUiState> {
    val nameValidator = CompositeValidator<String>()
        .add(ValidatorNonEmptyText(UiText.StringResource(R.string.label_empty_name)))
    val validatorDescription = CompositeValidator<String>()
        .add(ValidatorNonEmptyText(UiText.StringResource(R.string.label_empty_description)))
    val gendersValidator = CompositeValidator<String>()
        .add(ValidatorNonEmptyText(UiText.StringResource(R.string.label_select_genre)))
    val trailerValidator = CompositeValidator<String>()
        .add(ValidatorNonEmptyText(UiText.StringResource(R.string.label_empty_trailer)))
    val priceValidator = CompositeValidator<String>()
        .add(RealNumberValidator(error = UiText.StringResource(R.string.label_price_format)))
        .add(ValidatorNonEmptyText(UiText.StringResource(R.string.label_empty_price)))
    val scheduleValidator = CompositeValidator<List<Schedule>>()
        .add(EmptyScheduleValidator(UiText.StringResource(R.string.label_schedules)))

    override fun validate(movieState: MovieUiState): MovieValidationUiState {
        val nameValidation: Validation = nameValidator.validate(movieState.name)
        val validationDescription: Validation = validatorDescription.validate(movieState.description)
        val gendersValidation: Validation = gendersValidator.validate(movieState.genders.joinToString(""))
        val trailerValidation: Validation = trailerValidator.validate(movieState.trailerLink)
        val priceValidation: Validation = priceValidator.validate(movieState.price)
        val scheduleValidation: Validation = scheduleValidator.validate(movieState.schedules)

        return MovieValidationUiState(
            nameValidation = nameValidation,
            validationDescription = validationDescription,
            gendersValidation = gendersValidation,
            trailerValidation = trailerValidation,
            priceValidation = priceValidation,
            scheduleValidation = scheduleValidation
        )
    }
}