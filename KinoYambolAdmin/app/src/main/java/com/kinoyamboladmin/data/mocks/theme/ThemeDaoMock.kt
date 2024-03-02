package com.kinoyamboladmin.data.mocks.theme

class ThemeDaoMock {
    private var themes = mutableListOf<ThemeMock>(
        ThemeMock(
            id = 1,
            theme = "System"
        ),
        ThemeMock(
            id = 2,
            theme = "Dark"
        ),
        ThemeMock(
            id = 1,
            theme = "Light"
        )
    )

    fun get(): MutableList<ThemeMock> = themes
}