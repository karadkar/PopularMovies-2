package io.github.karadkar.popularmovies.utils

import io.github.karadkar.popularmovies.data.local.MovieEntity

object MovieEntityDataFactory {
    fun getMovieEntity(): MovieEntity {
        return MovieEntity(
                voteCount = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
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
                add(getMovieEntity())
            }
            sortBy { it.id }
        }
    }
}