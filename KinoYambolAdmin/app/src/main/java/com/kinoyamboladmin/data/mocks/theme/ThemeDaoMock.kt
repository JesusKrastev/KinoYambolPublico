package com.kinoyamboladmin.data.mocks.theme

import javax.inject.Inject

class ThemeDaoMock @Inject constructor() {
    private var themes = mutableListOf<ThemeMock>(
        ThemeMock(
            id = 1,
            theme = "Sistema"
        ),
        ThemeMock(
            id = 2,
            theme = "Oscuro"
        ),
        ThemeMock(
            id = 1,
            theme = "Claro"
        )
    )

    fun get(): MutableList<ThemeMock> = themes
}