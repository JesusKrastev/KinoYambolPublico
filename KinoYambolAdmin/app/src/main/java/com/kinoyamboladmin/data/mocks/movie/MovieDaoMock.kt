package com.kinoyamboladmin.data.mocks.movie

import android.graphics.BitmapFactory
import com.kinoyamboladmin.R
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class MovieDaoMock @Inject constructor() {
    private var movies = mutableListOf<MovieMock>(
        MovieMock(
            id = 1,
            image = "pacificrim",
            title = "Pacific Rim",
            description = "La película está ambientada en el año 2025, cuando la Tierra es atacada por kaijus, monstruos colosales que han surgido a partir de un portal interdimensional en el fondo del Océano Pacífico, llamado \"El Abismo\". Para luchar contra los monstruos, la humanidad se une para crear a los Jaegers: gigantescas máquinas humanoides, cada una controlada por dos pilotos cuyas mentes están unidas por un puente neuronal (similares a los personajes llamados Headmasters de Transformers y a los EVA de la serie de manga y anime Neon Genesis Evangelion). Centrándose en los días posteriores de la guerra, la historia sigue a Raleigh Becket, un piloto jaeger llamado de su retiro, que se asociará con la piloto novata Mako Mori en un último esfuerzo para derrotar a los kaijus.",
            genders = listOf("Ciencia ficción", "Acción", "Aventuras"),
            languages = listOf("Español", "Inglés"),
            startDate = LocalDate.of(2024, 2, 26),
            endDate = LocalDate.of(2024, 3, 3),
            duration = 131,
            trailerLink = "4NCG8bp4BI0",
            price = 8.50
        ),
        MovieMock(
            id = 2,
            image = "joker",
            title = "Joker",
            description = "es una película de suspense psicológico estadounidense de 2019 dirigida y producida por Todd Phillips, quien coescribió el guion con Scott Silver. La película, basada en personajes de DC Comics, está protagonizada por Joaquin Phoenix como El Joker y proporciona una historia de su origen alternativa para el personaje. Ambientada en 1981, sigue a Arthur Fleck, un payaso fracasado y comediante cuyo descenso a la locura y el nihilismo inspira una violenta revolución contracultural contra los ricos en una Gotham City en decadencia. Robert De Niro, Zazie Beetz, Frances Conroy, Brett Cullen, Glenn Fleshler, Bill Camp, Shea Whigham y Marc Maron aparecen en papeles secundarios. Joker fue producida por Warner Bros. Pictures, DC Films y Joint Effort, en asociación con Bron Creative y Village Roadshow Pictures, y distribuida por Warner Bros. Joker es la primera película de acción en vivo de Batman en recibir una calificación R.",
            genders = listOf("Drama", "Suspense", "Crimen", "Thriller"),
            languages = listOf("Español"),
            startDate = LocalDate.of(2024, 3, 4),
            endDate = LocalDate.of(2024, 3, 10),
            duration = 122,
            trailerLink = "ygUHhImN98w",
            price = 8.50
        ),
        MovieMock(
            id = 3,
            image = "transformers",
            title = "Transformers",
            description = "Dos razas de robots extraterrestres transformables llegan a la tierra en busca de una misteriosa fuente de poder. En la guerra que estalla entre las dos razas sam witwicky se convierte en la clave de una guerra que puede destruir a la humanidad.",
            genders = listOf("Acción", "Ciencia ficción"),
            languages = listOf("Inglés", "Español"),
            startDate = LocalDate.of(2024, 3, 5),
            endDate = LocalDate.of(2024, 3, 11),
            duration = 144,
            trailerLink = "IBCLUy7pB5w",
            price = 8.50
        ),
        MovieMock(
            id = 4,
            image = "operacionfortune",
            title = "Operación Fortune",
            description = "El espía de élite Orson Fortune debe localizar y detener la venta de una nueva y mortífera tecnología armamentística que maneja un multimillonario traficante de armas. Fortune y su equipo reclutan a la mayor estrella de Hollywood para que les ayude.",
            genders = listOf("Acción", "Comedia"),
            languages = listOf("Inglés", "Español"),
            startDate = LocalDate.of(2024, 3, 15),
            endDate = LocalDate.of(2024, 3, 21),
            duration = 114,
            trailerLink = "xSo-W-0kgYQ",
            price = 8.50
        )
    )

    fun get(): MutableList<MovieMock> = movies
    fun get(id: Int): MovieMock? = movies.find { movie -> movie.id == id }
    fun insert(movie: MovieMock) = movies.add(movie)
    fun update(newMovie: MovieMock) {
        val index = movies.indexOfFirst { movie -> movie.id == newMovie.id }
        if (index != -1) movies[index] = newMovie
    }
    fun delete(id: Int) {
        val index = movies.indexOfFirst { movie -> movie.id == id }
        if (index != -1) movies.removeAt(index)
    }
}