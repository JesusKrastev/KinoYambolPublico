package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.information.InformationDaoMock
import com.kinoyamboladmin.models.Information
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InformationRepository @Inject constructor(
    private val dao: InformationDaoMock
) {
    suspend fun get(): MutableList<Information> = withContext(Dispatchers.IO) {
        dao.get().map { it.toInformation() }.toMutableList()
    }
    suspend fun get(movieId: Int): MutableList<Information> = withContext(Dispatchers.IO) {
        dao.get(movieId).map { it.toInformation() }.toMutableList()
    }
    suspend fun insert(information: Information) = withContext(Dispatchers.IO) {
        dao.insert(information.toInformationMock())
    }
    suspend fun update(newInformation: Information) = withContext(Dispatchers.IO) {
        dao.update(newInformation.toInformationMock())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        dao.delete(id)
    }
}