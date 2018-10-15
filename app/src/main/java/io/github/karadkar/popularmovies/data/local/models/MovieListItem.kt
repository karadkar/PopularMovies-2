package io.github.karadkar.popularmovies.data.local.models

import io.github.karadkar.popularmovies.data.base.Movie

data class MovieListItem(
        var id: Int,
        var title: String,
        var posterPath: String,
        var votes: Double,
        var bookmarked: Boolean
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Movie) {
            (other.id == this.id && other.title == this.title
                    && other.posterPath == this.posterPath && other.voteAverage == this.votes)
        } else {
            super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}