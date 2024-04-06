package com.kinoyamboladmin.ui.features

import com.kinoyamboladmin.models.Gender
import com.kinoyamboladmin.utilities.texts.UiText

data class GenderUiState(
    val id: Int,
    val name: Int
)

fun Gender.toGenderUiState(): GenderUiState =
    GenderUiState(
        id = id,
        name = name
    )

fun GenderUiState.toGender(): Gender =
    Gender(
        id = id,
        name = name
    )