package com.kinoyamboladmin.ui.features.moviesheet

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.kinoyamboladmin.ui.composables.DefaultText
import com.kinoyamboladmin.ui.composables.FloatingActionButtonWithIcon
import com.kinoyamboladmin.ui.composables.GradientBrush
import com.kinoyamboladmin.ui.composables.TextBody
import com.kinoyamboladmin.ui.composables.TextTile
import com.kinoyamboladmin.ui.features.MovieUiState
import com.kinoyamboladmin.ui.features.components.MoviesNavBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun TextLabel(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun HeaderImage(
    modifier: Modifier = Modifier,
    imageResource: Int
) {
    val gradientColors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colorScheme.background
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = imageResource,
            contentDescription = "front page"
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = GradientBrush(
                        isVerticalGradient = true,
                        colors = gradientColors
                    )
                )
        )
    }
}

@Composable
fun Gender(
    modifier: Modifier = Modifier,
    gender: String
) {
    Box(
        modifier = modifier
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextLabel(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            text = gender
        )
    }
}

@Composable
fun ListGenders(
    modifier: Modifier = Modifier,
    genders: List<String>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genders) { gender ->
            Gender(gender = gender)
        }
    }
}

@Composable
fun FeatureInformation(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    description: String?,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description
        )
        DefaultText(text = text)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Features(
    modifier: Modifier = Modifier,
    selectedMovie: MovieSheetUiState
) {
    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = 2,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        FeatureInformation(
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Filled.CalendarMonth,
            description = "date",
            text = "${selectedMovie.startDate} - ${selectedMovie.endDate}"
        )
        FeatureInformation(
            modifier = Modifier.weight(0.5f),
            icon = Icons.Filled.HourglassEmpty,
            description = "duration",
            text = "${selectedMovie.duration}m"
        )
        FeatureInformation(
            modifier = Modifier.weight(0.5f),
            icon = Icons.Filled.Translate,
            description = "language",
            text = "${selectedMovie.languages.joinToString(", ")}"
        )
    }
}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    text: String,
    wordLimit: Int
) {
    var isSummarized: Boolean by remember { mutableStateOf(true) }
    val numberWords: Int = text.split("\\s+".toRegex()).size

    if (numberWords <= wordLimit) {
        TextBody(
            modifier = modifier,
            text = text
        )
    } else {
        val annotatedString: AnnotatedString = buildAnnotatedString {
            append(if (isSummarized) text.substring(0, 150) else text)
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(if (isSummarized) " Mostrar más" else " Mostrar menos")
            }
        }
        TextBody(
            modifier = modifier
                .animateContentSize()
                .clickable { isSummarized = !isSummarized },
            text = annotatedString
        )
    }
}

@Composable
fun YoutubePlayer(
    modifier: Modifier = Modifier,
    youtubeVideoId: String
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                        youTubePlayer.pause()
                    }
                })
            }
        }
    )
}

@Composable
fun Price(
    price: Double
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TextTile(title = "Precio ticket")
        TextTile(
            title = "${String.format("%.2f", price).replace(".", ",")} €",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    selectedMovie: MovieSheetUiState
) {
    val context = LocalContext.current
    val imageResource: Int = context.resources.getIdentifier(selectedMovie.image, "drawable", context.packageName)

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        HeaderImage(
            modifier = Modifier.heightIn(min = 0.dp, max = 450.dp),
            imageResource = imageResource
        )
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TextTile(title = selectedMovie.name)
            ListGenders(genders = selectedMovie.genders)
            Features(selectedMovie = selectedMovie)
            Description(
                text = selectedMovie.description,
                wordLimit = 50
            )
            YoutubePlayer(youtubeVideoId = selectedMovie.trailerLink)
            Price(price = selectedMovie.price)
        }
    }
}

@Composable
fun MovieSheetScreen(
    modifier: Modifier = Modifier,
    selectedMovie: MovieSheetUiState,
    onSheetEvent: (MovieSheetEvent) -> Unit,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateEditMovie: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        content = { paddingValues ->
            MainContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                selectedMovie = selectedMovie
            )
        },
        floatingActionButton = {
            FloatingActionButtonWithIcon(
                icon = Icons.Filled.Edit,
                contentDescription = "Edit",
                onClick = { onSheetEvent(MovieSheetEvent.OnEditMovie(onNavigateEditMovie))  }
            )
        },
        bottomBar = {
            MoviesNavBar(
                onNavigateToMovies = onNavigateToMovies,
                onNavigateToScanner = onNavigateToScanner,
                onNavigateToStatistics = onNavigateToStatistics,
                onNavigateToSettings = onNavigateToSettings,
                selectedPage = 0
            )
        },
    )
}