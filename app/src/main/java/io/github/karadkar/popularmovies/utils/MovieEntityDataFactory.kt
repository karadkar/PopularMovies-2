package io.github.karadkar.popularmovies.utils

import io.github.karadkar.popularmovies.data.local.MovieEntity
import io.github.karadkar.popularmovies.data.remote.models.Result

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
}