package com.kinoyamboladmin.ui.features.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinoyamboladmin.ui.composables.DefaultText
import com.kinoyamboladmin.ui.composables.TextTile
import com.kinoyamboladmin.ui.features.components.MoviesNavBar
import com.kinoyamboladmin.ui.features.seemovies.EmptyMoviesList
import com.kinoyamboladmin.ui.features.seemovies.MainContent
import com.kinoyamboladmin.ui.features.seemovies.MoviesListEvent

@Composable
fun EmptyStatistics(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\uD83D\uDCCA",
            fontSize = 120.sp
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        TextTile(title = "Sin estadísticas")
        DefaultText(
            modifier = Modifier.width(240.dp),
            text = "No hay datos suficientes para generar estadísticas."
        )
    }
}

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
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
            EmptyStatistics(
                modifier = Modifier.padding(paddingValues = paddingValues)
            )
        },
        bottomBar = {
            MoviesNavBar(
                onNavigateToMovies = onNavigateToMovies,
                onNavigateToScanner = onNavigateToScanner,
                onNavigateToStatistics = onNavigateToStatistics,
                onNavigateToSettings = onNavigateToSettings,
                selectedPage = 2
            )
        },
    )
}