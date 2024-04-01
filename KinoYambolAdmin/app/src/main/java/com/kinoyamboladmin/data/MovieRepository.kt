package com.kinoyamboladmin.data

import com.kinoyamboladmin.data.mocks.gender.GenderDaoMock
import com.kinoyamboladmin.data.mocks.language.LanguageDaoMock
import com.kinoyamboladmin.data.mocks.movie.MovieDaoMock
import com.kinoyamboladmin.data.mocks.movie.MovieMock
import com.kinoyamboladmin.data.mocks.schedule.ScheduleDaoMock
import com.kinoyamboladmin.data.mocks.schedule.ScheduleMock
import com.kinoyamboladmin.models.Movie
import com.kinoyamboladmin.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDaoMock,
    private val scheduleDao: ScheduleDaoMock,
    private val languageDao: LanguageDaoMock,
    private val genderDao: GenderDaoMock
) {
    suspend fun get(): MutableList<Movie> = withContext(Dispatchers.IO) {
        movieDao.get().map { it.toMovie() }.toMutableList()
    }
    suspend fun getTodaysMovies(): MutableList<Movie> = withContext(Dispatchers.IO) {
        movieDao.get().map { it.toMovie() }.filter { movie -> movie.schedules.any { it.movieSchedule.containsKey(LocalDate.now()) } }.toMutableList()
    }
    suspend fun getUpcomingMovies(): MutableList<Movie> = withContext(Dispatchers.IO) {
        movieDao.get().map { it.toMovie() }.filter { movie -> movie.schedules.all { it.movieSchedule.size == 1 || it.movieSchedule.isEmpty() } }.toMutableList()
    }
    suspend fun getMovieReleases(): MutableList<Movie> = withContext(Dispatchers.IO) {
        movieDao.get().map { it.toMovie() }.sortedByDescending { movie -> movie.schedules.flatMap { it.movieSchedule.keys }.maxOrNull() }.take(3).toMutableList()
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
            lettersImage = lettersImage,
            name = name,
            description = description,
            genders = genders.map { genderDao.get(it)!!.toGender() },
            schedules = schedules.map { scheduleDao.get(it)!!.toSchedule() },
            duration = duration,
            trailerLink = trailerLink,
            price = price
        )

    fun Movie.toMovieMock() : MovieMock =
        MovieMock(
            id = id,
            image = image,
            lettersImage = lettersImage,
            name = name,
            description = description,
            genders = genders.map { it.id },
            schedules = schedules.map { it.id },
            duration = duration,
            trailerLink = trailerLink,
            price = price
        )

    fun ScheduleMock.toSchedule(): Schedule =
        Schedule(
            id = id,
            language = languageDao.get(language)!!.toLanguage(),
            movieSchedule = movieSchedule
        )
}