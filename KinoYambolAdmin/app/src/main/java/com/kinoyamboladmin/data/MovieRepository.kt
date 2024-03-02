package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.movie.MovieDaoMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.models.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dao: MovieDaoMock
) {
    suspend fun get(): MutableList<Movie> = dao.get().map { it.toMovie() }.toMutableList()
    suspend fun get(id: Int): Movie? = dao.get(id)?.toMovie()
    suspend fun insert(movie: Movie) = dao.insert(movie.toMovieMock())
    suspend fun update(newMovie: Movie) = dao.update(newMovie.toMovieMock())
    suspend fun delete(id: Int) = dao.delete(id)
}