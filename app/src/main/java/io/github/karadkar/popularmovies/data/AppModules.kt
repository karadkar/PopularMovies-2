package io.github.karadkar.popularmovies.data

import io.github.karadkar.popularmovies.MovieListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Main app module
 */

val MovieListModule: Module = module {

    // provide singleton of list repository
    single {
        DummyMovieListRepository() as MovieListRepository
    }

    // provide new instance of ViewModel
    viewModel {
        MovieListViewModel(respository = get())
    }
}

/**
 * Module list
 */
val appModules = listOf(MovieListModule)