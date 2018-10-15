package io.github.karadkar.popularmovies.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.github.karadkar.popularmovies.data.local.models.BookmarkEntity
import io.github.karadkar.popularmovies.data.local.models.MovieEntity

@Database(entities = [MovieEntity::class, BookmarkEntity::class], version = 1)
@TypeConverters(GenereConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun bookmarkDao(): BookmarkDao
}