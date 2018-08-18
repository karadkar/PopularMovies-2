package io.github.karadkar.popularmovies

import android.arch.lifecycle.ViewModel
import io.github.karadkar.popularmovies.data.MovieListRepository
import io.github.karadkar.popularmovies.testing.OpenForTesting

@OpenForTesting
class MovieListViewModel(
        val respository: MovieListRepository,
        val genre: String
) : ViewModel() {
    fun sayHello(): String = "${respository.giveHello()} - $genre"
}