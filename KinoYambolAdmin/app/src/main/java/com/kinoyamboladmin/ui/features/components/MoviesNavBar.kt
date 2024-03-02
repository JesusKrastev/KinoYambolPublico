package com.kinoyamboladmin.ui.features.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MoviesNavBar(
    selectedPage: Int,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    @Immutable
    data class ItemIconButton(
        val icon: ImageVector,
        val description: String? = null,
        val title: String,
        val onClick: () -> Unit
    )

    val listItemsIconButtons:List<ItemIconButton> = listOf<ItemIconButton>(
        ItemIconButton(
            icon = Icons.Filled.Theaters,
            description = "movies",
            title = "Películas",
            onClick = onNavigateToMovies
        ),
        ItemIconButton(
            icon = Icons.Filled.QrCodeScanner,
            description = "barcodescanner",
            title = "Escáner",
            onClick = onNavigateToScanner
        ),
        ItemIconButton(
            icon = Icons.Filled.Analytics,
            description = "analytics",
            title = "Estadísticas",
            onClick = onNavigateToStatistics
        ),
        ItemIconButton(
            icon = Icons.Filled.Settings,
            description = "settings",
            title = "Ajustes",
            onClick = onNavigateToSettings
        )
    )
    var selectedItem: Int by remember { mutableIntStateOf(selectedPage) }

    NavigationBar {
        listItemsIconButtons.forEachIndexed { index, button ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = button.icon,
                        contentDescription = button.description
                    )
                },
                label = {
                    Text(
                        text = button.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                ),
                selected = selectedItem == index,
                onClick = {
                    button.onClick()
                    selectedItem = index
                }
            )
        }
    }
}