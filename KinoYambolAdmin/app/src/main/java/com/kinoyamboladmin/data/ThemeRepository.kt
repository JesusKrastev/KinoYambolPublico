package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.theme.ThemeDaoMock
import com.kinoyamboladmin.models.Theme
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val dao: ThemeDaoMock
) {
    suspend fun get(): MutableList<Theme> = dao.get().map { it.toTheme() }.toMutableList()
}