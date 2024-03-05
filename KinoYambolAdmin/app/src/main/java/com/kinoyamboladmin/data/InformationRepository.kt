package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.information.InformationDaoMock
import com.kinoyamboladmin.models.Information

class InformationRepository(
    private val dao: InformationDaoMock
) {
    fun get(): MutableList<Information> = dao.get().map { it.toInformation() }.toMutableList()
    fun get(movieId: Int): MutableList<Information> = dao.get(movieId).map { it.toInformation() }.toMutableList()
    fun insert(information: Information) = dao.insert(information.toInformationMock())
    fun update(newInformation: Information) = dao.update(newInformation.toInformationMock())
    fun delete(id: Int) = dao.delete(id)
}