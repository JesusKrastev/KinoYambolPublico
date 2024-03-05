package com.kinoyamboladmin.data.mocks.information

import com.kinoyamboladmin.data.mocks.movie.MovieMock
import javax.inject.Inject

class InformationDaoMock @Inject constructor() {
    private var informations = mutableListOf<InformationMock>()

    fun get(): MutableList<InformationMock> = informations
    fun get(movieId: Int): MutableList<InformationMock> = informations.filter { it.movieId == movieId }.toMutableList()
    fun insert(information: InformationMock) = informations.add(information)
    fun update(newInformation: InformationMock) {
        val index = informations.indexOfFirst { information -> information.id == newInformation.id }
        if (index != -1) informations[index] = newInformation
    }
    fun delete(id: Int) {
        val index = informations.indexOfFirst { information -> information.id == id }
        if (index != -1) informations.removeAt(index)
    }
}