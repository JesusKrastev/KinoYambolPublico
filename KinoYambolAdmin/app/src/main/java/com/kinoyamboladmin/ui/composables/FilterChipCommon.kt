package com.kinoyamboladmin.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipWithIcon(
    modifier: Modifier = Modifier,
    selectedState: Boolean = false,
    textState : String = "Etiqueta",
    iconState : ImageVector? = null,
    onClick : () -> Unit = {}
) {
    FilterChip(
        modifier = modifier.then(Modifier.height(FilterChipDefaults.Height)),
        selected = selectedState,
        onClick = onClick,
        colors = FilterChipDefaults.filterChipColors(
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            selectedContainerColor = MaterialTheme.colorScheme.primary
        ),
        label = { Text(textState) },
        leadingIcon = {
            if (selectedState) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Icono seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            } else {
                iconState?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Icono asociado a la etiqueta",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipWithoutIcon(
    modifier: Modifier = Modifier,
    selectedState: Boolean = false,
    textState : String = "Etiqueta",
    onClick : () -> Unit = {}
) {
    FilterChip(
        modifier = modifier.then(Modifier.height(FilterChipDefaults.Height)),
        selected = selectedState,
        onClick = onClick,
        colors = FilterChipDefaults.filterChipColors(
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            selectedContainerColor = MaterialTheme.colorScheme.primary
        ),
        label = { Text(textState) },
        leadingIcon = {
            if (selectedState) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Icono seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }
    )
}


@Preview(
    showBackground = true,
)
@Composable
fun FilterChipTest() {
    var selected by remember { mutableStateOf(true) }
    FilterChipWithIcon(
        selectedState = selected,
        textState = "Filtro",
        iconState = Icons.Filled.FilterAlt,
        onClick = { selected = !selected }
    )
}