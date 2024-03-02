package com.kinoyamboladmin.ui.features.seemovies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentLate
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material.icons.filled.Today
import androidx.compose.ui.graphics.vector.ImageVector

data class FilterUiState(
    val all: Boolean = false,
    val allIcon: ImageVector = Icons.Filled.Movie,
    val today: Boolean = false,
    val todayIcon: ImageVector = Icons.Filled.Today,
    val soon: Boolean = false,
    val soonIcon: ImageVector = Icons.Filled.AssignmentLate
)