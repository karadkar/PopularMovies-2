package io.github.karadkar.popularmovies.data.mapper

import io.github.karadkar.popularmovies.data.base.Movie
import io.github.karadkar.popularmovies.data.local.models.MovieEntity

class MovieEntityMapper : EntityMapper<Movie, MovieEntity> {

    override fun mapFromEntiry(entity: Movie): MovieEntity {
        return MovieEntity(
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

    override fun mapToEntity(domain: MovieEntity): Movie {
        return domain
    }
}