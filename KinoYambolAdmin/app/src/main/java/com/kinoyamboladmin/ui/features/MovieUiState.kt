package com.kinoyamboladmin.ui.features

data class MovieUiState(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val genders: List<String> = emptyList(),
    val schedules: List<Int> = emptyList(),
    val duration: Int = 0,
    val trailerLink: String = "",
    val price: Double = 0.0
)