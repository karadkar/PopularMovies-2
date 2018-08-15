package io.github.karadkar.popularmovies.data

class DummyMovieListRepository : MovieListRepository {
    override fun giveHello(): String = "Hello Koin!"
}