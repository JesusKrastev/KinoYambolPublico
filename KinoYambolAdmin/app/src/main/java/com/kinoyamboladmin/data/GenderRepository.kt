package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.gender.GenderDaoMock
import com.kinoyamboladmin.data.mocks.gender.GenderMock
import com.kinoyamboladmin.models.Gender
import javax.inject.Inject

class GenderRepository @Inject constructor(
    private val dao: GenderDaoMock
) {
    suspend fun get(): MutableList<Gender> = dao.get().map { it.toGender() }.toMutableList()
}