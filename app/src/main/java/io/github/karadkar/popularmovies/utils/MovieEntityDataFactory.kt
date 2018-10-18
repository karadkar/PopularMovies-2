package io.github.karadkar.popularmovies.utils

import io.github.karadkar.popularmovies.data.local.models.MovieEntity
import io.github.karadkar.popularmovies.data.local.models.MovieListItem
import io.github.karadkar.popularmovies.data.remote.models.Result
import io.github.karadkar.popularmovies.utils.DataFactory.randomBoolean
import io.github.karadkar.popularmovies.utils.DataFactory.randomDouble
import io.github.karadkar.popularmovies.utils.DataFactory.randomInt
import io.github.karadkar.popularmovies.utils.DataFactory.randomString

object MovieEntityDataFactory {
    fun getMovieEntity(movieId: Int = DataFactory.randomInt()): MovieEntity {
        return MovieEntity(
                voteCount = DataFactory.randomInt(),
                id = movieId,
                video = DataFactory.randomBoolean(),
                voteAverage = DataFactory.randomDouble(),
                title = DataFactory.randomString(),
                popularity = DataFactory.randomDouble(),
                posterPath = DataFactory.randomString(),
                originalLanguage = DataFactory.randomString(),
                originalTitle = DataFactory.randomString(),
                genreIds = DataFactory.randomIntList(),
                backdropPath = DataFactory.randomString(),
                adult = DataFactory.randomBoolean(),
                overview = DataFactory.randomString(),
                releaseDate = DataFactory.randomString())
    }

    // sorted by id
    fun getMovieEntities(size: Int = 10): List<MovieEntity> {
        return ArrayList<MovieEntity>().apply {
            for (i in 1..size) {
                add(getMovieEntity(i))
            }
        }
    }

    fun getTmdbResult(movieId: Int = DataFactory.randomInt()): Result {
        return Result(
                voteCount = DataFactory.randomInt(),
                id = movieId,
                video = DataFactory.randomBoolean(),
                voteAverage = DataFactory.randomDouble(),
                title = DataFactory.randomString(),
                popularity = DataFactory.randomDouble(),
                posterPath = DataFactory.randomString(),
                originalLanguage = DataFactory.randomString(),
                originalTitle = DataFactory.randomString(),
                genreIds = DataFactory.randomIntList(),
                backdropPath = DataFactory.randomString(),
                adult = DataFactory.randomBoolean(),
                overview = DataFactory.randomString(),
                releaseDate = DataFactory.randomString())
    }

    fun getTmdbResults(size: Int = 10): List<Result> {
        return ArrayList<Result>().apply {
            for (i in 1..size) {
                add(getTmdbResult(i))
            }
        }
    }

    fun getMovieListItems(size: Int = 10): List<MovieListItem> {
        return ArrayList<MovieListItem>().apply {
            for (i in 1..size) {
                add(getMovieListItem(i))
            }
        }
    }

    fun getMovieListItem(id: Int = randomInt()) = MovieListItem(
            id = id,
            title = randomString(),
            posterPath = randomString(),
            votes = randomDouble(),
            bookmarked = randomBoolean()
    )
}