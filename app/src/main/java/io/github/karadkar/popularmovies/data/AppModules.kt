package io.github.karadkar.popularmovies.data

import io.github.karadkar.popularmovies.MovieListViewModel
import io.github.karadkar.popularmovies.utils.AppConstants.KEY_MOVIE_GENRE
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.context.ParameterProvider
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
    viewModel { params: ParameterProvider ->
        // get() will provide repository
        // param genre will be provided from Activity
        MovieListViewModel(respository = get(), genre = params[KEY_MOVIE_GENRE])
    }
}

/**
 * Module list
 */
val appModules = listOf(MovieListModule)