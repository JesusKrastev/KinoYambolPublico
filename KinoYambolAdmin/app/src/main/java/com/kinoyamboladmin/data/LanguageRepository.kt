package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.language.LanguageDaoMock
import com.kinoyamboladmin.models.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LanguageRepository @Inject constructor(
    private val dao: LanguageDaoMock
) {
    suspend fun get(): MutableList<Language> = withContext(Dispatchers.IO) {
        dao.get().map { it.toLanguage() }.toMutableList()
    }
}