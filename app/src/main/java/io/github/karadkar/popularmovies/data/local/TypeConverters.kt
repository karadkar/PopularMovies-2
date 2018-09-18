package io.github.karadkar.popularmovies.data.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class GenereConverter {

    @TypeConverter
    fun fromString(value: String): List<Int> {
        val typedValues = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, typedValues)
    }

    @TypeConverter
    fun toString(ids: List<Int>): String {
        return Gson().toJson(ids)
    }
}