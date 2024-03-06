package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.movie.MovieDaoMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.data.mocks.schedule.ScheduleDaoMock
import com.kinoyamboladmin.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDaoMock,
    private val scheduleDaoMock: ScheduleDaoMock
) {
    suspend fun get(): MutableList<Movie> = withContext(Dispatchers.IO) {
        movieDao.get().map { it.toMovie() }.toMutableList()
    }
    suspend fun get(id: Int): Movie? = withContext(Dispatchers.IO) {
        movieDao.get(id)?.toMovie()
    }
    suspend fun insert(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insert(movie.toMovieMock())
    }
    suspend fun update(newMovie: Movie) = withContext(Dispatchers.IO) {
        movieDao.update(newMovie.toMovieMock())
    }

    fun MovieMock.toMovie() : Movie =
        Movie(
            id = id,
            image = image,
            name = name,
            description = description,
            genders = genders,
            schedules = schedules.map { scheduleDaoMock.get(it)!!.toSchedule() },
            duration = duration,
            trailerLink = trailerLink,
            price = price
        )

    fun Movie.toMovieMock() : MovieMock =
        MovieMock(
            id = id,
            image = image,
            name = name,
            description = description,
            genders = genders,
            schedules = schedules.map { it.id },
            duration = duration,
            trailerLink = trailerLink,
            price = price
        )
}