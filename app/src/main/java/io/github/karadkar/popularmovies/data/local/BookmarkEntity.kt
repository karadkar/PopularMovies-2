package io.github.karadkar.popularmovies.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "bookmarks",
        indices = [Index("movieId", unique = true)],
        foreignKeys = [ForeignKey(
                entity = MovieEntity::class,
                parentColumns = ["id"],
                childColumns = ["movieId"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )]
)
data class BookmarkEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                          val movieId: Int, val bookmarked: Boolean)