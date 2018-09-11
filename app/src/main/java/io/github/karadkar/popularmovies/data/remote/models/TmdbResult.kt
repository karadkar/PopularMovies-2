package io.github.karadkar.popularmovies.data.remote.models

import com.google.gson.annotations.SerializedName

data class TmdbResult(
        @SerializedName("page") var page: Int = 0,
        @SerializedName("total_results") var totalResults: Int = 0,
        @SerializedName("total_pages") var totalPages: Int = 0,
        @SerializedName("results") var results: List<Result> = listOf()
)