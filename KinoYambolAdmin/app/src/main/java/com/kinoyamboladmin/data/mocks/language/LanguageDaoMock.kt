package com.kinoyamboladmin.data.mocks.language

class LanguageDaoMock {
    private var languages = mutableListOf<LanguageMock>(
        LanguageMock(
            id = 1,
            language = "Español"
        ),
        LanguageMock(
            id = 2,
            language = "български"
        ),
        LanguageMock(
            id = 3,
            language = "English"
        )
    )

    fun get(): MutableList<LanguageMock> = languages
}