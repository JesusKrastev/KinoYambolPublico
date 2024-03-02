package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.language.LanguageDaoMock
import com.kinoyamboladmin.models.Language
import javax.inject.Inject

class LanguageRepository @Inject constructor(
    private val dao: LanguageDaoMock
) {
    private fun get(): MutableList<Language> = dao.get().map { it.toLanguage() }.toMutableList()
}