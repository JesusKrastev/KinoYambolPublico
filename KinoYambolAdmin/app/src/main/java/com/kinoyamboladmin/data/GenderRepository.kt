package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.gender.GenderDaoMock
import com.kinoyamboladmin.data.mocks.gender.GenderMock
import javax.inject.Inject

class GenderRepository @Inject constructor(
    private val dao: GenderDaoMock
) {

}