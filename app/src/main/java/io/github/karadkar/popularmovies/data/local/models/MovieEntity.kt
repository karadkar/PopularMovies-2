package io.github.karadkar.popularmovies.data.local.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.github.karadkar.popularmovies.data.base.Movie

@Entity(tableName = "Movie")
data class MovieEntity(
        override val voteCount: Int,
        @PrimaryKey override val id: Int,
        override val video: Boolean,
        override val voteAverage: Double,
        override val title: String,
        override val popularity: Double,
        override val posterPath: String,
        override val originalLanguage: String,
        override val originalTitle: String,
        override val genreIds: List<Int>,
        override val backdropPath: String,
        override val adult: Boolean,
        override val overview: String,
        override val releaseDate: String
) : Movie