package io.github.karadkar.popularmovies.data.mapper

import io.github.karadkar.popularmovies.data.base.Movie
import io.github.karadkar.popularmovies.data.remote.models.Result

class TmdbResultMapper : EntityMapper<Movie, Result> {
    override fun mapFromEntiry(entity: Movie): Result {
        return Result(
                voteCount = entity.voteCount,
                id = entity.id,
                video = entity.video,
                voteAverage = entity.voteAverage,
                title = entity.title,
                popularity = entity.popularity,
                posterPath = entity.posterPath,
                originalLanguage = entity.originalLanguage,
                originalTitle = entity.originalTitle,
                genreIds = entity.genreIds,
                backdropPath = entity.backdropPath,
                adult = entity.adult,
                overview = entity.overview,
                releaseDate = entity.releaseDate)
    }

    override fun mapToEntity(domain: Result): Movie {
        return domain
    }
}