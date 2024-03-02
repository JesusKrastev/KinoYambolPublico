package com.kinoyamboladmin.ui.features.seemovies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kinoyamboladmin.ui.composables.CollapsingLayout
import com.kinoyamboladmin.ui.composables.DefaultText
import com.kinoyamboladmin.ui.composables.FilterChipWithIcon
import com.kinoyamboladmin.ui.composables.FloatingActionButtonWithIcon
import com.kinoyamboladmin.ui.composables.GradientBrush
import com.kinoyamboladmin.ui.composables.TextTile
import com.kinoyamboladmin.ui.features.MovieUiState
import com.kinoyamboladmin.ui.features.components.MoviesNavBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNewMovies(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    newMovies: List<MovieUiState>
) {
    val context = LocalContext.current

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState
    ) { page ->
        val imageResource: Int = context.resources.getIdentifier(newMovies[page].image, "drawable", context.packageName)

        HeaderImage(
            imageResource = imageResource
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color: Color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(9.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeaderNews(
    modifier: Modifier = Modifier,
    newMovies: List<MovieUiState>
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { newMovies.size })
    val coroutineScope = rememberCoroutineScope()

    // Automatic scroll HorizontalPager
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            coroutineScope.launch {
                val nextPage: Int = if (pagerState.currentPage + 1 == pagerState.pageCount) 0 else pagerState.currentPage + 1
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ListNewMovies(
            modifier = Modifier.weight(0.9f),
            pagerState = pagerState,
            newMovies = newMovies
        )
        PagerIndicator(
            modifier = Modifier.padding(top = 10.dp),
            pagerState = pagerState
        )
    }
}

@Composable
fun Filters(
    modifier: Modifier = Modifier,
    filterUiState: FilterUiState,
    onFilterChange: (FilterUiState) -> Unit
) {
    @Immutable
    data class FilterChipUiState(
        val label: String = "",
        val selected: Boolean = false,
        val icon: ImageVector? = null,
        val onClick: () -> Unit = {}
    )

    val content = listOf(
        FilterChipUiState(
            label = "Todos",
            selected = filterUiState.all,
            icon = filterUiState.allIcon,
            onClick = { onFilterChange(FilterUiState().copy(all = true)) }
        ),
        FilterChipUiState(
            label = "Hoy",
            selected = filterUiState.today,
            icon = filterUiState.todayIcon,
            onClick = { onFilterChange(FilterUiState().copy(today = true)) }
        ),
        FilterChipUiState(
            label = "Próximamente",
            selected = filterUiState.soon,
            icon = filterUiState.soonIcon,
            onClick = { onFilterChange(FilterUiState().copy(soon = true)) }
        )
    )

    Row(
        modifier = modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        content.forEach { filter ->
            FilterChipWithIcon(
                textState = filter.label,
                selectedState = filter.selected,
                iconState = filter.icon,
                onClick = filter.onClick
            )
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieImage: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clip(CardDefaults.shape)
            .clickable { onClick() }
    ) {
        AsyncImage(
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            model = movieImage,
            contentDescription = "front page"
        )
    }
}

@Composable
fun ListMovies(
    modifier: Modifier = Modifier,
    movies: List<MovieUiState>,
    onClickMovie: (Int) -> Unit
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = modifier.padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(movies) { movie ->
            val imageResource: Int = context.resources.getIdentifier(movie.image, "drawable", context.packageName)

            MovieCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                movieImage = imageResource,
                onClick = { onClickMovie(movie.id) }
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    movies: List<MovieUiState>,
    newMovies: List<MovieUiState>,
    filterUiState: FilterUiState,
    onFilterChange: (FilterUiState) -> Unit,
    onClickMovie: (Int) -> Unit
) {
    CollapsingLayout(
        modifier = modifier.fillMaxWidth(),
        collapsingTop = {
            HeaderNews(
                modifier = Modifier.heightIn(min = 0.dp, max = 400.dp),
                newMovies = newMovies
            )
        },
        bodyContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Filters(
                    filterUiState = filterUiState,
                    onFilterChange = { onFilterChange(it) }
                )
                ListMovies(
                    movies = movies,
                    onClickMovie = { onClickMovie(it) }
                )
            }
        }
    )
}

@Composable
fun EmptyMoviesList(
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
            text = "\uD83C\uDFAC",
            fontSize = 120.sp
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        TextTile(title = "No hay películas")
        DefaultText(text = "Actualmente no hay películas disponibles.")
    }
}

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    newMovies: List<MovieUiState>,
    movies: List<MovieUiState>,
    filterUiState: FilterUiState,
    onListMoviesEvent: (MoviesListEvent) -> Unit,
    onNavigateCreateMovie: () -> Unit,
    onNavigateMovieSheet: (Int) -> Unit,
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
            if(movies.isNotEmpty()) {
                MainContent(
                    modifier = modifier.padding(paddingValues = paddingValues),
                    movies = movies,
                    newMovies = newMovies,
                    filterUiState = filterUiState,
                    onFilterChange = { onListMoviesEvent(MoviesListEvent.OnFilterChange(it)) },
                    onClickMovie = { onListMoviesEvent(MoviesListEvent.OnClickMovie(it, onNavigateMovieSheet)) }
                )
            } else {
                EmptyMoviesList(
                    modifier = modifier.padding(paddingValues = paddingValues)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButtonWithIcon(
                icon = Icons.Filled.Add,
                contentDescription = "Add",
                onClick = { onListMoviesEvent(MoviesListEvent.OnCreateMovie(onNavigateCreateMovie)) }
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