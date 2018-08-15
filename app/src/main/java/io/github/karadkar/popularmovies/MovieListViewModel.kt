package io.github.karadkar.popularmovies

import android.arch.lifecycle.ViewModel
import io.github.karadkar.popularmovies.data.MovieListRepository

class MovieListViewModel(val respository: MovieListRepository) : ViewModel() {
    fun sayHello(): String = respository.giveHello()
}