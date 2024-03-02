package com.kinoyamboladmin.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FloatingActionButtonWithIcon(
    icon: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}