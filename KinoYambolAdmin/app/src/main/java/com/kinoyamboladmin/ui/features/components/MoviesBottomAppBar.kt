package com.kinoyamboladmin.ui.features.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MoviesBottomAppBar(
    onEditDate: () -> Unit,
    onDeleteDate: () -> Unit
) {
    @Immutable
    data class ItemIconButton(
        val icon: ImageVector,
        val description: String? = null,
        val onClick: () -> Unit
    )

    val listItemsIconButtons:List<ItemIconButton> = listOf<ItemIconButton>(
        ItemIconButton(
            icon = Icons.Filled.Edit,
            description = "edit",
            onClick = onEditDate
        ),
        ItemIconButton(
            icon = Icons.Filled.Delete,
            description = "delete",
            onClick = onDeleteDate
        )
    )

    BottomAppBar(
        actions = {
            listItemsIconButtons.forEach { item ->
                IconButton(
                    onClick = item.onClick
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.description
                    )
                }
            }
        }
    )
}