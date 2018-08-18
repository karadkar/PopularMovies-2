package io.github.karadkar.popularmovies.di


import io.github.karadkar.popularmovies.MovieListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.mockito.Mockito.mock


private val testMovieListModule = module {
    viewModel {
        mock(MovieListViewModel::class.java)
    }
}

val testAppModules = listOf(testMovieListModule)