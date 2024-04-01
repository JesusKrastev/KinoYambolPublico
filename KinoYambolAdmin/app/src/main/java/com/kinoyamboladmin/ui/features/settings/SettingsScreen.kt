package com.kinoyamboladmin.ui.features.settings

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kinoyamboladmin.R
import com.kinoyamboladmin.ui.composables.OutlinedDropdownMenu
import com.kinoyamboladmin.ui.composables.TextBody
import com.kinoyamboladmin.ui.features.components.MoviesNavBar
import java.util.Locale

@Composable
fun TextProperty(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    description: String?,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onBackground,
            imageVector = icon,
            contentDescription = description
        )
        TextBody(text = text)
    }
}

@Composable
fun DropdownMenuProperty(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    label: String,
    onChangeValue: (String) -> Unit,
    icon: ImageVector,
    description: String?
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onBackground,
            imageVector = icon,
            contentDescription = description
        )
        OutlinedDropdownMenu(
            options = options,
            selectedOption = selectedOption,
            label = label,
            onChangeValue = onChangeValue
        )
    }
}

@Composable
fun Properties(
    modifier: Modifier = Modifier,
    languages: List<String>,
    themes: List<String>,
    settingsUiState: SettingsUiState,
    onChangeLanguage: (String) -> Unit,
    onChangeTheme: (String) -> Unit,
    onClickPrivacyPolicies: () -> Unit,
    onClickTermsAndConditions: () -> Unit
) {
    val context: Context = LocalContext.current
    @Immutable
    data class DropdownMenuPropertyUiState(
        val label: String = "",
        val icon: ImageVector = Icons.Filled.Settings,
        val selectedOption: String = "",
        val description: String? = null,
        val options: List<String> = emptyList(),
        val onChangeValue: (String) -> Unit
    )
    @Immutable
    data class TextPropertyUiState(
        val icon: ImageVector = Icons.Filled.Settings,
        val text: String = "",
        val description: String? = null,
        val onClick: () -> Unit
    )

    val contentDropdownsMenu: List<DropdownMenuPropertyUiState> = listOf(
        DropdownMenuPropertyUiState(
            label = stringResource(R.string.label_language),
            icon = Icons.Filled.Language,
            description = "language",
            selectedOption = settingsUiState.language,
            options = languages,
            onChangeValue = { onChangeLanguage(it) }
        ),
        DropdownMenuPropertyUiState(
            label = stringResource(R.string.label_theme),
            description = "theme",
            options = themes,
            selectedOption = settingsUiState.theme,
            icon = Icons.Filled.Contrast,
            onChangeValue = onChangeTheme
        )
    )
    val contentText: List<TextPropertyUiState> = listOf(
        TextPropertyUiState(
            text = stringResource(R.string.label_privacy_policies),
            icon = Icons.Filled.Lock,
            description = "privacy",
            onClick = onClickPrivacyPolicies
        ),
        TextPropertyUiState(
            text = stringResource(R.string.label_terms_and_conditions),
            icon = Icons.Filled.Info,
            description = "terms",
            onClick = onClickTermsAndConditions
        )
    )

    Column(
        modifier = modifier
    ) {
        contentDropdownsMenu.forEach { dropdownMenu ->
            DropdownMenuProperty(
                modifier = Modifier.padding(bottom = 16.dp),
                options = dropdownMenu.options,
                selectedOption = dropdownMenu.selectedOption,
                label = dropdownMenu.label,
                onChangeValue = dropdownMenu.onChangeValue,
                icon = dropdownMenu.icon,
                description = dropdownMenu.description
            )
        }
        contentText.forEach { iconWithText ->
            TextProperty(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(),
                icon = iconWithText.icon,
                description = iconWithText.description,
                text = iconWithText.text,
                onClick = iconWithText.onClick
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    languages: List<String>,
    themes: List<String>,
    settingsUiState: SettingsUiState,
    onChangeLanguage: (String) -> Unit,
    onChangeTheme: (String) -> Unit,
    onClickPrivacyPolicies: () -> Unit,
    onClickTermsAndConditions: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Properties(
            languages = languages,
            themes = themes,
            settingsUiState = settingsUiState,
            onChangeLanguage = onChangeLanguage,
            onChangeTheme = onChangeTheme,
            onClickPrivacyPolicies = onClickPrivacyPolicies,
            onClickTermsAndConditions = onClickTermsAndConditions
        )
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    languages: List<String>,
    themes: List<String>,
    settingsUiState: SettingsUiState,
    onSettingsEvent: (SettingsEvent) -> Unit,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {

        },
        content = { paddingValues ->
            MainContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                languages = languages,
                themes = themes,
                settingsUiState = settingsUiState,
                onChangeLanguage = { onSettingsEvent(SettingsEvent.OnChangeLanguage(it)) },
                onChangeTheme = { onSettingsEvent(SettingsEvent.OnChangeTheme(it)) },
                onClickPrivacyPolicies = { onSettingsEvent(SettingsEvent.OnClickPrivacyPolicies) },
                onClickTermsAndConditions = { onSettingsEvent(SettingsEvent.OnClickTermsAndConditions) }
            )
        },
        bottomBar = {
            MoviesNavBar(
                onNavigateToMovies = onNavigateToMovies,
                onNavigateToScanner = onNavigateToScanner,
                onNavigateToStatistics = onNavigateToStatistics,
                onNavigateToSettings = onNavigateToSettings,
                selectedPage = 3
            )
        },
    )
}