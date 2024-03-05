package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.language.LanguageMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Movie

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

fun LanguageMock.toLanguage(): Language =
    Language(
        id = id,
        name = name
    )

fun Language.toLanguageMock(): LanguageMock =
    LanguageMock(
        id = id,
        name = name
    )