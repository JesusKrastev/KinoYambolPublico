package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.movie.MovieMock
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