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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kinoyamboladmin.R
import com.kinoyamboladmin.ui.composables.CollapsingLayout
import com.kinoyamboladmin.ui.composables.CoroutineManagementSnackBar
import com.kinoyamboladmin.ui.composables.DefaultText
import com.kinoyamboladmin.ui.composables.FilterChipWithIcon
import com.kinoyamboladmin.ui.composables.FloatingActionButtonWithIcon
import com.kinoyamboladmin.ui.composables.GradientBrush
import com.kinoyamboladmin.ui.composables.SnackbarCommon
import com.kinoyamboladmin.ui.composables.TextTile
import com.kinoyamboladmin.ui.features.components.MoviesNavBar
import com.kinoyamboladmin.utilities.error_handling.InformationStateUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HeaderImage(
    modifier: Modifier = Modifier,
    image: String,
    lettersImage: String?,
    onClick: () -> Unit
) {
    val gradientColors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colorScheme.background
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick() }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            model = image,
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
        lettersImage?.let {
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
                    .size(width = 250.dp, height = 90.dp),
                model = it,
                contentScale = ContentScale.FillBounds,
                contentDescription = "letters"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNewMovies(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    newMovies: List<MovieListUiState>,
    onClickMovie: (Int) -> Unit
) {
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState
    ) { page ->
        HeaderImage(
            image = newMovies[page].image,
            lettersImage = newMovies[page].lettersImage,
            onClick = { onClickMovie(newMovies[page].id) }
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
    newMovies: List<MovieListUiState>,
    onClickMovie: (Int) -> Unit
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
            newMovies = newMovies,
            onClickMovie = onClickMovie
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
            label = stringResource(R.string.label_all),
            selected = filterUiState.all,
            icon = filterUiState.allIcon,
            onClick = { onFilterChange(FilterUiState().copy(all = true)) }
        ),
        FilterChipUiState(
            label = stringResource(R.string.label_today),
            selected = filterUiState.today,
            icon = filterUiState.todayIcon,
            onClick = { onFilterChange(FilterUiState().copy(today = true)) }
        ),
        FilterChipUiState(
            label = stringResource(R.string.label_soon),
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
    image: String,
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
            model = image,
            contentDescription = "front page"
        )
    }
}

@Composable
fun Movies(
    modifier: Modifier = Modifier,
    movies: List<MovieListUiState>,
    onClickMovie: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(movies) { movie ->
            MovieCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                image = movie.image,
                onClick = { onClickMovie(movie.id) }
            )
        }
    }
}

@Composable
fun EmptyMoviesList(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\uD83C\uDFAC",
            fontSize = 120.sp
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        TextTile(title = stringResource(R.string.title_no_movies))
        DefaultText(text = stringResource(R.string.text_no_movies))
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    movies: List<MovieListUiState>,
    newMovies: List<MovieListUiState>,
    filterUiState: FilterUiState,
    onFilterChange: (FilterUiState) -> Unit,
    onClickMovie: (Int) -> Unit
) {
    CollapsingLayout(
        modifier = modifier.fillMaxWidth(),
        collapsingTop = {
            HeaderNews(
                modifier = Modifier.heightIn(min = 0.dp, max = 400.dp),
                newMovies = newMovies,
                onClickMovie = onClickMovie
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
                if(movies.isNotEmpty()) {
                    Movies(
                        movies = movies,
                        onClickMovie = { onClickMovie(it) }
                    )
                } else {
                    EmptyMoviesList()
                }
            }
        }
    )
}

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    newMovies: List<MovieListUiState>,
    movies: List<MovieListUiState>,
    filterUiState: FilterUiState,
    informationState: InformationStateUiState,
    onListMoviesEvent: (MoviesListEvent) -> Unit,
    onNavigateCreateMovie: () -> Unit,
    onNavigateMovieSheet: (Int) -> Unit,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    CoroutineManagementSnackBar(
        snackbarHostState = snackbarHostState,
        informationState = informationState
    )

    Scaffold(
        modifier = modifier,
        topBar = {

        },
        content = { paddingValues ->
            if(movies.isEmpty() && newMovies.isEmpty()) {
                EmptyMoviesList()
            } else {
                MainContent(
                    modifier = Modifier.padding(paddingValues),
                    movies = movies,
                    newMovies = newMovies,
                    filterUiState = filterUiState,
                    onFilterChange = { onListMoviesEvent(MoviesListEvent.OnFilterChange(it)) },
                    onClickMovie = { onListMoviesEvent(MoviesListEvent.OnClickMovie(it, onNavigateMovieSheet)) }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButtonWithIcon(
                icon = Icons.Filled.Add,
                contentDescription = "add",
                onClick = onNavigateCreateMovie
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
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                SnackbarCommon(informationState = informationState)
            }
        }
    )
}