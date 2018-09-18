package io.github.karadkar.popularmovies.data.remote.models

import com.google.gson.annotations.SerializedName
import io.github.karadkar.popularmovies.data.base.Movie

data class Result(
        @SerializedName("vote_count") override var voteCount: Int = 0,
        @SerializedName("id") override var id: Int = 0,
        @SerializedName("video") override var video: Boolean = false,
        @SerializedName("vote_average") override var voteAverage: Double = 0.0,
        @SerializedName("title") override var title: String = "",
        @SerializedName("popularity") override var popularity: Double = 0.0,
        @SerializedName("poster_path") override var posterPath: String = "",
        @SerializedName("original_language") override var originalLanguage: String = "",
        @SerializedName("original_title") override var originalTitle: String = "",
        @SerializedName("genre_ids") override var genreIds: List<Int> = listOf(),
        @SerializedName("backdrop_path") override var backdropPath: String = "",
        @SerializedName("adult") override var adult: Boolean = false,
        @SerializedName("overview") override var overview: String = "",
        @SerializedName("release_date") override var releaseDate: String = ""
) : Movie