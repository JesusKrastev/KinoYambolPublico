package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.language.LanguageDaoMock
import com.kinoyamboladmin.models.Language

class LanguageRepository(
    private val dao: LanguageDaoMock
) {
    suspend fun get(): MutableList<Language> = dao.get().map { it.toLanguage() }.toMutableList()
}