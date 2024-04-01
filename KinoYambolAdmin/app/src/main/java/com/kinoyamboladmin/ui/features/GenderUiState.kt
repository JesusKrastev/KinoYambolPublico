package com.kinoyamboladmin.ui.features

import com.kinoyamboladmin.models.Gender

data class GenderUiState(
    val id: Int,
    val name: String
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