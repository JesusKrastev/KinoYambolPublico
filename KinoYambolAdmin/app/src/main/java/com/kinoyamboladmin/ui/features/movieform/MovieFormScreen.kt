package com.kinoyamboladmin.ui.features.movieform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kinoyamboladmin.R
import com.kinoyamboladmin.ui.composables.CoroutineManagementSnackBar
import com.kinoyamboladmin.ui.composables.OutlinedDropdownMenu
import com.kinoyamboladmin.ui.composables.FilterChipWithoutIcon
import com.kinoyamboladmin.ui.composables.FloatingActionButtonWithIcon
import com.kinoyamboladmin.ui.composables.OutlinedTextFieldWithErrorState
import com.kinoyamboladmin.ui.composables.RichTooltipWithoutAction
import com.kinoyamboladmin.ui.composables.SnackbarCommon
import com.kinoyamboladmin.ui.features.GenderUiState
import com.kinoyamboladmin.ui.features.components.MoviesBottomAppBar
import com.kinoyamboladmin.ui.features.components.MoviesNavBar
import com.kinoyamboladmin.ui.features.components.MoviesTopBar
import com.kinoyamboladmin.utilities.device.registerImageSelectorWithGetContent
import com.kinoyamboladmin.utilities.error_handling.InformationStateUiState
import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId

@Composable
fun CameraButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedIconButton(
        modifier = modifier,
        colors = IconButtonDefaults.outlinedIconButtonColors(
            contentColor = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor
        ),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.CameraAlt,
            contentDescription = "camera",
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
    }
}

@Composable
fun ImageSelector(
    modifier: Modifier = Modifier,
    image: String?,
    label: String,
    onChangePhoto: (ImageBitmap) -> Unit
) {
    val selectImage = registerImageSelectorWithGetContent(onChangePhoto)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { selectImage.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if(image != null) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                model = image,
                contentDescription = "front page"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CameraButton(
                onClick = { selectImage.launch("image/*") }
            )
            Text(
                text = label,
                color = OutlinedTextFieldDefaults.colors().unfocusedLabelColor,
                fontSize = MaterialTheme.typography.labelLarge.fontSize
            )
        }
    }
}

@Composable
fun TextFieldName(
    modifier: Modifier = Modifier,
    name: String,
    nameValidation: Validation,
    onNameChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = stringResource(R.string.label_name),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Draw,
                contentDescription = "name"
            )
        },
        textState = name,
        validationState = nameValidation,
        onValueChange = onNameChange
    )
}

@Composable
fun TextFieldDescription(
    modifier: Modifier = Modifier,
    description: String,
    descriptionValidation: Validation,
    onRemoveAllDescription: () -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = stringResource(R.string.label_description),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.FormatAlignCenter,
                contentDescription = "description"
            )
        },
        trailingIcon = {
            IconButton(onClick = onRemoveAllDescription) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "cancel"
                )
            }
        },
        textState = description,
        validationState = descriptionValidation,
        onValueChange = onDescriptionChange
    )
}

@Composable
fun Genders(
    modifier: Modifier = Modifier,
    genders: List<GenderUiState>,
    selectedGenders: List<GenderUiState>,
    onChangeGenders: (GenderUiState) -> Unit,
    gendersValidation: Validation
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 9.dp, bottom = 16.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(genders) { gender ->
                FilterChipWithoutIcon(
                    selectedState = selectedGenders.contains(gender),
                    textState = stringResource(id = gender.name),
                    onClick = { onChangeGenders(gender) }
                )
            }
        }
        if(gendersValidation.hasError) {
            Text(
                text = "\t${gendersValidation.errorMessage!!.asString()!!}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun LanguageDropDownMenu(
    modifier: Modifier = Modifier,
    languages: List<String>,
    selectedLanguage: String,
    onChangeLanguage: (String) -> Unit
) {
    OutlinedDropdownMenu(
        modifier = modifier,
        options = languages,
        selectedOption = selectedLanguage,
        label = stringResource(R.string.label_language),
        onChangeValue = onChangeLanguage
    )
}

@Composable
fun translateMonth(nameMonth: String): String {
    val resourceId: Int = when(nameMonth.lowercase()) {
        "january" -> R.string.january_date
        "february" -> R.string.february_date
        "march" -> R.string.march_date
        "april" -> R.string.april_date
        "may" -> R.string.may_date
        "june" -> R.string.june_date
        "july" -> R.string.july_date
        "august" -> R.string.august_date
        "september" -> R.string.september_date
        "october" -> R.string.october_date
        "november" -> R.string.november_date
        "december" -> R.string.december_date
        else -> throw Exception("Month not found")
    }

    return stringResource(id = resourceId)
}

@Composable
fun Date(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(width = 80.dp, height = 100.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
            .border(
                border = BorderStroke(
                    width = 1.5.dp,
                    color = if (isSelected) Color.Transparent else OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor
                ),
                shape = RoundedCornerShape(7.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                text = translateMonth(date.month.name).take(3),
                color = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
            )
            Text(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                text = date.dayOfMonth.toString(),
                color = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun ButtonAddDate(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(width = 80.dp, height = 100.dp)
            .clip(RoundedCornerShape(7.dp))
            .border(
                border = BorderStroke(
                    width = 1.5.dp,
                    color = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor
                ),
                shape = RoundedCornerShape(7.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp),
            tint = MaterialTheme.colorScheme.onBackground,
            imageVector = Icons.Filled.Add,
            contentDescription = "add"
        )
    }
}

@Composable
fun ListDates(
    modifier: Modifier = Modifier,
    dates: List<LocalDate>,
    selectedDate: LocalDate?,
    onClickDate: (LocalDate) -> Unit,
    onClickAddDate: () -> Unit,
    scheduleValidator: Validation
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(dates) { date ->
                Date(
                    date = date,
                    isSelected = selectedDate == date,
                    onClick = { onClickDate(date) }
                )
            }
            item {
                ButtonAddDate(
                    onClick = onClickAddDate
                )
            }
        }
        if(scheduleValidator.hasError) {
            Text(
                text = "\t${scheduleValidator.errorMessage!!.asString()}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun Hour(
    modifier: Modifier = Modifier,
    time: LocalTime,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(7.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
            .border(
                border = BorderStroke(
                    width = 1.5.dp,
                    color = if (isSelected) Color.Transparent else OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor
                ),
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${time.hour}:${time.minute.toString().padStart(2, '0')}",
                color = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun ButtonAddHour(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(7.dp))
            .border(
                border = BorderStroke(
                    width = 1.5.dp,
                    color = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor
                ),
                shape = RoundedCornerShape(7.dp)
            )
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            tint = MaterialTheme.colorScheme.onBackground,
            imageVector = Icons.Filled.Add,
            contentDescription = "add"
        )
    }
}

@Composable
fun ListHours(
    modifier: Modifier = Modifier,
    hours: List<LocalTime>,
    selectedHour: LocalTime?,
    onClickAddHour: () -> Unit
) {
    LazyRow(
        modifier = modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(hours) { hour ->
            Hour(
                time = hour,
                isSelected = selectedHour == hour
            )
        }
        item {
            ButtonAddHour(
                onClick = onClickAddHour
            )
        }
    }
}

@Composable
fun BoldText(text: String) = buildAnnotatedString {
    withStyle(
        style = SpanStyle(
            fontWeight = FontWeight.Bold
        )
    ) {
        append(text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailerLinkIdTooltip() {
    var tooltipState: TooltipState = rememberTooltipState(isPersistent = true)
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val trailerTooltipContent: AnnotatedString = buildAnnotatedString {
        append(stringResource(R.string.label_youtube_id))
        append("\n\nhttps://www.youtube.com/watch?v=")
        append(BoldText("4NCG8bp4BI0"))
        append("?si=3YSby0O8fHSPnvOr\n\nhttps://www.youtube.com/watch?v=")
        append(BoldText("4NCG8bp4BI0"))
        append("&t=1s")
    }

    TooltipBox(
        tooltip = {
            RichTooltipWithoutAction(
                text = trailerTooltipContent,
                title = stringResource(R.string.label_title_youtube_id),
            )
        },
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        state = tooltipState
    ) {
        IconButton(onClick = { coroutineScope.launch { tooltipState.show() } }) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "information"
            )
        }
    }
}

@Composable
fun TextFieldTrailerLinkId(
    modifier: Modifier = Modifier,
    trailerLinkId: String,
    trailerValidation: Validation,
    onTrailerLinkIdChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = stringResource(R.string.label_youtube_link_id),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Link,
                contentDescription = "link"
            )
        },
        trailingIcon = {
            TrailerLinkIdTooltip()
        },
        textState = trailerLinkId,
        validationState = trailerValidation,
        onValueChange = onTrailerLinkIdChange
    )
}

@Composable
fun TextFieldPrice(
    modifier: Modifier = Modifier,
    price: String,
    priceValidation: Validation,
    onChangePrice: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = stringResource(R.string.label_price),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Payments,
                contentDescription = "price"
            )
        },
        textState = price,
        validationState = priceValidation,
        onValueChange = onChangePrice
    )
}

@Composable
fun SaveMovieButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButtonWithIcon(
        modifier = modifier,
        icon = Icons.Filled.Save,
        contentDescription = "Save",
        onClick = onClick
    )
}

@Composable
fun BodyForm(
    modifier: Modifier = Modifier,
    movieState: MovieUiState,
    genders: List<GenderUiState>,
    onMovieFormEvent: (MovieFormEvent) -> Unit,
    validationMovieState: MovieValidationUiState
) {
    val datesWithTimesOfLanguage: List<Pair<LocalDate, List<LocalTime>>> = movieState.schedules
        .filter { it.language.name == movieState.selectedLanguage }
        .flatMap { schedule ->
            schedule.movieSchedule.entries.map { entry ->
                Pair(entry.key, entry.value)
            }
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ImageSelector(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            image = movieState.image,
            label = stringResource(R.string.label_front_page),
            onChangePhoto = { onMovieFormEvent(MovieFormEvent.OnChangeFrontPage(it)) }
        )
        ImageSelector(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            image = movieState.lettersImage,
            label = stringResource(R.string.label_image_letters),
            onChangePhoto = { onMovieFormEvent(MovieFormEvent.OnChangeLettersImage(it)) }
        )
        TextFieldName(
            modifier = Modifier.fillMaxWidth(),
            name = movieState.name,
            nameValidation = validationMovieState.nameValidation,
            onNameChange = { onMovieFormEvent(MovieFormEvent.OnChangeName(it)) }
        )
        TextFieldDescription(
            modifier = Modifier.fillMaxWidth(),
            description = movieState.description,
            descriptionValidation = validationMovieState.validationDescription,
            onRemoveAllDescription = { onMovieFormEvent(MovieFormEvent.OnChangeDescription("")) },
            onDescriptionChange = { onMovieFormEvent(MovieFormEvent.OnChangeDescription(it)) }
        )
        Genders(
            genders = genders,
            selectedGenders = movieState.genders,
            onChangeGenders = { onMovieFormEvent(MovieFormEvent.OnChangeGenders(it)) },
            gendersValidation = validationMovieState.gendersValidation
        )
        LanguageDropDownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            languages = movieState.schedules.map { it.language.name },
            selectedLanguage = movieState.selectedLanguage,
            onChangeLanguage = { onMovieFormEvent(MovieFormEvent.OnChangeLanguage(it)) }
        )
        ListDates(
            dates = datesWithTimesOfLanguage.map { it.first }.sorted(),
            selectedDate = movieState.selectedDate,
            onClickDate = { onMovieFormEvent(MovieFormEvent.OnChangeDate(it)) },
            onClickAddDate = { onMovieFormEvent(MovieFormEvent.OnShowDialogDate) },
            scheduleValidator = validationMovieState.scheduleValidation
        )
        if(movieState.selectedDate != null) {
            val hoursOfSelectedDate = datesWithTimesOfLanguage
                .find { it.first == movieState.selectedDate }?.second?.sorted() ?: emptyList()

            ListHours(
                hours = hoursOfSelectedDate,
                onClickAddHour = { onMovieFormEvent(MovieFormEvent.OnShowDialogHour) },
                selectedHour = movieState.selectedHour
            )
        }
        TextFieldTrailerLinkId(
            modifier = Modifier.fillMaxWidth(),
            trailerLinkId = movieState.trailerLink,
            trailerValidation = validationMovieState.trailerValidation,
            onTrailerLinkIdChange = { onMovieFormEvent(MovieFormEvent.OnChangeTrailerLink(it)) }
        )
        TextFieldPrice(
            modifier = Modifier.fillMaxWidth(),
            price = movieState.price,
            priceValidation = validationMovieState.priceValidation,
            onChangePrice = { onMovieFormEvent(MovieFormEvent.OnChangePrice(it)) }
        )
        SaveMovieButton(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { onMovieFormEvent(MovieFormEvent.OnSaveMovie) }
        )
    }
}

private fun convertMillisToLocalDate(millis: Long): LocalDate {
    val instant: Instant = Instant.ofEpochMilli(millis)

    return instant.atZone(ZoneId.of("UTC")).toLocalDate()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDatePickerDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickConfirm: (LocalDate) -> Unit,
    dates: List<LocalDate>
) {
    val dateState: DatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        yearRange = IntRange(start = LocalDate.now().year, endInclusive = LocalDate.now().year + 1),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val date: LocalDate = convertMillisToLocalDate(utcTimeMillis)

                return !dates.contains(date) && date >= LocalDate.now()
            }
        }
    )

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = {
                    onClickConfirm(convertMillisToLocalDate(dateState.selectedDateMillis!!))
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(R.string.label_confirm))
            }
        }
    ) {
        DatePicker(state = dateState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionHourPicker(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickConfirm: (LocalTime) -> Unit
) {
    val timeState: TimePickerState = rememberTimePickerState()

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = {
                    onClickConfirm(LocalTime.of(timeState.hour, timeState.minute))
                    onDismissRequest()
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(R.string.label_confirm))
            }
        }
    ) {
        TimePicker(
            modifier = Modifier.padding(16.dp),
            colors = TimePickerDefaults.colors(
                timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary
            ),
            state = timeState
        )
    }
}

@Composable
fun ErrorDialog(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "error",
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.label_confirm))
            }
        }
    )
}

@Composable
fun MovieFormScreen(
    modifier: Modifier = Modifier,
    movieState: MovieUiState,
    genders: List<GenderUiState>,
    informationState: InformationStateUiState,
    onMovieFormEvent: (MovieFormEvent) -> Unit,
    validationMovieState: MovieValidationUiState,
    onNavigateToMovies: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateBackwards: () -> Unit,
    showDateDialog: Boolean,
    showTimeDialog: Boolean,
    showErrorMessageSchedule: Boolean
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    CoroutineManagementSnackBar(
        snackbarHostState = snackbarHostState,
        informationState = informationState
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            MoviesTopBar(
                title = if(movieState.id > 0) stringResource(R.string.label_edit_movie) else stringResource(R.string.label_create_movie),
                onNavigateBackwards = onNavigateBackwards
            )
        },
        content = { paddingValues ->
            if(showDateDialog) {
                SessionDatePickerDialog(
                    onDismissRequest = { onMovieFormEvent(MovieFormEvent.OnShowDialogDate) },
                    onClickConfirm = { onMovieFormEvent(MovieFormEvent.OnConfirmDateDialog(date = it)) },
                    dates = movieState.schedules.flatMap { it.movieSchedule.keys }.toList()
                )
            }
            if(showTimeDialog) {
                SessionHourPicker(
                    onDismissRequest = { onMovieFormEvent(MovieFormEvent.OnShowDialogHour) },
                    onClickConfirm = { onMovieFormEvent(MovieFormEvent.OnConfirmHourDialog(hour = it)) }
                )
            }
            if(showErrorMessageSchedule) {
                ErrorDialog(
                    onConfirmation = { onMovieFormEvent(MovieFormEvent.OnShowErrorDialog) },
                    dialogTitle = stringResource(R.string.title_time_restrictions),
                    dialogText = stringResource(R.string.text_time_restrictions),
                    icon = Icons.Filled.Error,
                    onDismissRequest = { onMovieFormEvent(MovieFormEvent.OnShowErrorDialog) }
                )
            }
            BodyForm(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                movieState = movieState,
                onMovieFormEvent = onMovieFormEvent,
                validationMovieState = validationMovieState,
                genders = genders
            )
        },
        bottomBar = {
            if(movieState.selectedDate == null && movieState.selectedHour == null) {
                MoviesNavBar(
                    onNavigateToMovies = onNavigateToMovies,
                    onNavigateToScanner = onNavigateToScanner,
                    onNavigateToStatistics = onNavigateToStatistics,
                    onNavigateToSettings = onNavigateToSettings,
                    selectedPage = 0
                )
            } else if(movieState.selectedDate != null && movieState.selectedHour == null) {
                MoviesBottomAppBar(
                    onEditDate = { onMovieFormEvent(MovieFormEvent.OnShowDialogDate) },
                    onDeleteDate = { onMovieFormEvent(MovieFormEvent.OnRemoveDate) }
                )
            } else {
                MoviesBottomAppBar(
                    onEditDate = { onMovieFormEvent(MovieFormEvent.OnShowDialogHour) },
                    onDeleteDate = { onMovieFormEvent(MovieFormEvent.OnRemoveHour) }
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                SnackbarCommon(informationState = informationState)
            }
        }
    )
}