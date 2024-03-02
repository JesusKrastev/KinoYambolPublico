package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.language.LanguageMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.data.mocks.settings.SettingsMock
import com.kinoyamboladmin.data.mocks.theme.ThemeMock
import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.models.Settings
import com.kinoyamboladmin.models.Theme

fun MovieMock.toMovie(): Movie =
    Movie(
        id = id,
        image = image,
        title = title,
        description = description,
        genders = genders,
        languages = languages,
        startDate = startDate,
        endDate = endDate,
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )

fun Movie.toMovieMock(): MovieMock =
    MovieMock(
        id = id,
        image = image,
        title = title,
        description = description,
        genders = genders,
        languages = languages,
        startDate = startDate,
        endDate = endDate,
        duration = duration,
        trailerLink = trailerLink,
        price = price
    )

fun SettingsMock.toSettings(): Settings =
    Settings(
        id = id,
        language = language,
        theme = theme
    )

fun Settings.toSettingsMock(): SettingsMock =
    SettingsMock(
        id = id,
        language = language,
        theme = theme
    )

fun LanguageMock.toLanguage(): Language =
    Language(
        id = id,
        language = language
    )

fun Language.toLanguageMock(): LanguageMock =
    LanguageMock(
        id = id,
        language = language
    )

fun Theme.toThemeMock(): ThemeMock =
    ThemeMock(
        id = id,
        theme = theme
    )

fun ThemeMock.toTheme(): Theme =
    Theme(
        id = id,
        theme = theme
    )