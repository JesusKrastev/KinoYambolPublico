package com.kinoyamboladmin.data.mocks.language

import javax.inject.Inject

class LanguageDaoMock @Inject constructor() {
    private var languages = mutableListOf<LanguageMock>(
        LanguageMock(
            id = 1,
            name = "English"
        ),
        LanguageMock(
            id = 2,
            name = "български"
        )
    )

    fun get(): MutableList<LanguageMock> = languages
    fun get(id: Int): LanguageMock? = languages.find { it.id == id }
}