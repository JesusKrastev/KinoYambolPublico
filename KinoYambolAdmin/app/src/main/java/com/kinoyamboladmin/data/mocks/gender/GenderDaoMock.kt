package com.kinoyamboladmin.data.mocks.gender

import javax.inject.Inject

class GenderDaoMock @Inject constructor() {
    private var genders = mutableListOf<GenderMock>(
        GenderMock(
            id = 1,
            name = "Acción"
        ),
        GenderMock(
            id = 2,
            name = "Aventura"
        ),
        GenderMock(
            id = 3,
            name = "Comedia"
        ),
        GenderMock(
            id = 4,
            name = "Drama"
        ),
        GenderMock(
            id = 5,
            name = "Ciencia Ficción"
        ),
        GenderMock(
            id = 6,
            name = "Terror"
        ),
        GenderMock(
            id = 7,
            name = "Romance"
        ),
        GenderMock(
            id = 8,
            name = "Fantasía"
        ),
        GenderMock(
            id = 9,
            name = "Animación"
        ),
        GenderMock(
            id = 10,
            name = "Documental"
        ),
        GenderMock(
            id = 11,
            name = "Suspense"
        ),
        GenderMock(
            id = 12,
            name = "Musical"
        ),
        GenderMock(
            id = 13,
            name = "Misterio"
        ),
        GenderMock(
            id = 14,
            name = "Western"
        ),
        GenderMock(
            id = 15,
            name = "Guerra"
        ),
        GenderMock(
            id = 16,
            name = "Crimen"
        ),
        GenderMock(
            id = 17,
            name = "Biografía"
        ),
        GenderMock(
            id = 18,
            name = "Deportes"
        ),
        GenderMock(
            id = 19,
            name = "Histórico"
        )
    )

    fun get(): MutableList<GenderMock> = genders
}