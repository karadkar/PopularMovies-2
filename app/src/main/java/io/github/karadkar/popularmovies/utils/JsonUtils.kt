package io.github.karadkar.popularmovies.utils

import okio.Okio
import java.nio.charset.StandardCharsets

object JsonUtils {
    fun readJsonFile(fileName: String): String {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("tmdb-api/$fileName.json")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }
}