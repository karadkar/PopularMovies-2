package io.github.karadkar.popularmovies.data

import io.github.karadkar.popularmovies.MovieListViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

/**
 * Main app module
 */

val MovieListModule: Module = applicationContext {

    // provide singleton of list repository
    bean {
        DummyMovieListRepository() as MovieListRepository
    }

    // provide new instance of ViewModel
    viewModel {
        // get() will provide repository
        MovieListViewModel(get())
    }
}

/**
 * Module list
 */
val appModules = listOf(MovieListModule)
