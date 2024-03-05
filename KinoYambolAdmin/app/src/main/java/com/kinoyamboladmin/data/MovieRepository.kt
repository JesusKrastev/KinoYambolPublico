package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.movie.MovieDaoMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dao: MovieDaoMock
) {
    suspend fun get(): MutableList<Movie> = withContext(Dispatchers.IO) {
        dao.get().map { it.toMovie() }.toMutableList()
    }
    suspend fun get(id: Int): Movie? = withContext(Dispatchers.IO) {
        dao.get(id)?.toMovie()
    }
    suspend fun insert(movie: Movie) = withContext(Dispatchers.IO) {
        dao.insert(movie.toMovieMock())
    }
    suspend fun update(newMovie: Movie) = withContext(Dispatchers.IO) {
        dao.update(newMovie.toMovieMock())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        dao.delete(id)
    }
}