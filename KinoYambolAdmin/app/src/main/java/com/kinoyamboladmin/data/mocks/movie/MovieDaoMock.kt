package com.kinoyamboladmin.data.mocks.movie

import javax.inject.Inject

class MovieDaoMock @Inject constructor() {
    private var movies = mutableListOf<MovieMock>(
        MovieMock(
            id = 1,
            image = "https://cdn.vivlio.com/product/v1/7cd77cbd-9f5d-4dbf-8cdb-2ba6606852eb/front-cover?tag=AQBKWwIk6682VnBbVkHEqbBAZ01qMVY4NTg%3D&size=450x675",
            lettersImage = "https://upload.wikimedia.org/wikipedia/en/7/79/Pacific_Rim_franchise_logo.webp",
            name = "Pacific Rim",
            description = "La película está ambientada en el año 2025, cuando la Tierra es atacada por kaijus, monstruos colosales que han surgido a partir de un portal interdimensional en el fondo del Océano Pacífico, llamado \"El Abismo\". Para luchar contra los monstruos, la humanidad se une para crear a los Jaegers: gigantescas máquinas humanoides, cada una controlada por dos pilotos cuyas mentes están unidas por un puente neuronal (similares a los personajes llamados Headmasters de Transformers y a los EVA de la serie de manga y anime Neon Genesis Evangelion). Centrándose en los días posteriores de la guerra, la historia sigue a Raleigh Becket, un piloto jaeger llamado de su retiro, que se asociará con la piloto novata Mako Mori en un último esfuerzo para derrotar a los kaijus.",
            genders = listOf(6, 1, 2),
            duration = 131,
            schedules = listOf(1, 2),
            trailerLink = "4NCG8bp4BI0",
            price = 8.50
        ),
        MovieMock(
            id = 2,
            image = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQrGZ9MLLOUkK5Fa-5-zxfyqNdE15-p52rm3ahwac1PSNdfqnxm",
            lettersImage = "https://www.gamespot.com/a/uploads/original/1573/15735876/3581213-joker-logo.png",
            name = "Joker",
            description = "Es una película de suspense psicológico estadounidense de 2019 dirigida y producida por Todd Phillips, quien coescribió el guion con Scott Silver. La película, basada en personajes de DC Comics, está protagonizada por Joaquin Phoenix como El Joker y proporciona una historia de su origen alternativa para el personaje. Ambientada en 1981, sigue a Arthur Fleck, un payaso fracasado y comediante cuyo descenso a la locura y el nihilismo inspira una violenta revolución contracultural contra los ricos en una Gotham City en decadencia. Robert De Niro, Zazie Beetz, Frances Conroy, Brett Cullen, Glenn Fleshler, Bill Camp, Shea Whigham y Marc Maron aparecen en papeles secundarios. Joker fue producida por Warner Bros. Pictures, DC Films y Joint Effort, en asociación con Bron Creative y Village Roadshow Pictures, y distribuida por Warner Bros. Joker es la primera película de acción en vivo de Batman en recibir una calificación R.",
            genders = listOf(4, 12, 17, 5),
            duration = 122,
            schedules = listOf(3, 4),
            trailerLink = "ygUHhImN98w",
            price = 8.50
        ),
        MovieMock(
            id = 3,
            image = "https://i.ebayimg.com/images/g/TpsAAOSw5VZXAxOK/s-l1600.jpg",
            lettersImage = "https://static.wikia.nocookie.net/logopedia/images/c/c7/Transformers-movie-logo.png/revision/latest?cb=20170127105743",
            name = "Transformers",
            description = "Dos razas de robots extraterrestres transformables llegan a la tierra en busca de una misteriosa fuente de poder. En la guerra que estalla entre las dos razas sam witwicky se convierte en la clave de una guerra que puede destruir a la humanidad.",
            genders = listOf(1, 6),
            duration = 144,
            schedules = listOf(5, 6),
            trailerLink = "IBCLUy7pB5w",
            price = 8.50
        ),
        MovieMock(
            id = 4,
            image = "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2023/11/poster-napoleon-3222538.jpg?tf=3840x",
            lettersImage = "https://vectorseek.com/wp-content/uploads/2023/09/Napoleon-Logo-Vector.svg-.png",
            name = "Napoleón",
            description = "Napoleón es una película de drama histórico épico dirigida y producida por Ridley Scott y escrita por David Scarpa. Basada en la historia de Napoleón Bonaparte, la película relata principalmente su ascenso al poder y su relación con la emperatriz Josefina. La película está protagonizada por Joaquin Phoenix como Napoleón y Vanessa Kirby como Josefina.",
            genders = listOf(4, 20),
            duration = 114,
            schedules = listOf(7, 8),
            trailerLink = "mh-zT7iSQro",
            price = 8.50
        ),
        MovieMock(
            id = 5,
            image = "https://www.aceprensa.com/wp-content/uploads/2023/07/oppenheimer.jpg",
            lettersImage = "https://upload.wikimedia.org/wikipedia/commons/7/7c/Oppenheimer_Movie_Logo_2023.png",
            name = "Oppenheimer",
            description = "Oppenheimer es una película biográfica de suspenso de 2023, ganadora del Oscar a Mejor Película. Escrita y dirigida por Christopher Nolan. Fue producida por Nolan junto a Charles Roven y Emma Thomas. Basada en American Prometheus, una biografía de 2005 escrita por Kai Bird y Martin J. Sherwin, la cinta narra la vida de J. Robert Oppenheimer, un físico teórico que fue fundamental en el desarrollo de las primeras armas nucleares como parte del Proyecto Manhattan y, por lo tanto, marcó el comienzo de la era atómica durante la Segunda Guerra Mundial. Cillian Murphy interpreta a Oppenheimer, con Emily Blunt como Katherine «Kitty» Oppenheimer, esposa de Robert, Matt Damon como el general Leslie Groves, el controlador militar de Oppenheimer, y Robert Downey Jr. como Lewis Strauss, un alto miembro de la Comisión de Energía Atómica de los Estados Unidos. El elenco del conjunto incluye a Florence Pugh, Josh Hartnett, Casey Affleck, Rami Malek y Kenneth Branagh.",
            genders = listOf(12),
            duration = 180,
            schedules = listOf(9, 10),
            trailerLink = "JpUd4BS7yI0",
            price = 9.0
        ),
        MovieMock(
            id = 6,
            image = "https://static.wikia.nocookie.net/cine/images/c/ce/El_corredor_del_laberinto.jpg/revision/latest/thumbnail/width/360/height/360?cb=20140921182838",
            lettersImage = "https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/149C490A623F5CE45B32B8B739D701ECA577D26341C4E0AECA5C7F07FD7CC7D9/scale?width=600&aspectRatio=1.78&format=webp",
            name = "El corredor del laberinto",
            description = "The Maze Runner es una película estadounidense de ciencia ficción, suspenso y acción dirigida por Wes Ball y basada en la novela homónima de 2009 escrita por James Dashner. Protagonizada por Dylan O'Brien, Kaya Scodelario, Thomas Brodie-Sangster, Will Poulter y Ki Hong Lee, la película estrenó el 19 de septiembre de 2014 en los Estados Unidos bajo la distribución de 20th Century Fox. Su DVD y Blu-ray fueron lanzados el 16 de diciembre de 2014.",
            genders = listOf(6, 12, 1),
            duration = 113,
            schedules = listOf(11, 12),
            trailerLink = "ZqlvJSNGfYA",
            price = 8.50
        )
    )

    suspend fun get(): MutableList<MovieMock> = movies
    suspend fun get(id: Int): MovieMock? = movies.find { movie -> movie.id == id }
    suspend fun insert(movie: MovieMock) = movies.add(movie)
    suspend fun update(newMovie: MovieMock) {
        val index = movies.indexOfFirst { movie -> movie.id == newMovie.id }
        if (index != -1) movies[index] = newMovie
    }
}