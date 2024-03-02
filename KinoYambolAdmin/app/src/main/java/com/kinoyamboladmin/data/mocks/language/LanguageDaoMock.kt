package com.kinoyamboladmin.data.mocks.language

import javax.inject.Inject


class LanguageDaoMock @Inject constructor() {
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