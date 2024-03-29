package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.gender.GenderMock
import com.kinoyamboladmin.data.mocks.language.LanguageMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.data.mocks.schedule.ScheduleMock
import com.kinoyamboladmin.models.Gender
import com.kinoyamboladmin.models.Schedule
import com.kinoyamboladmin.models.Language
import com.kinoyamboladmin.models.Movie

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

fun GenderMock.toGender(): Gender =
    Gender(
        id = id,
        name = name
    )

fun Gender.toGenderMock(): GenderMock =
    GenderMock(
        id = id,
        name = name
    )