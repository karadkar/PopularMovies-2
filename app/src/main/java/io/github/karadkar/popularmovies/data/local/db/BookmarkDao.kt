package io.github.karadkar.popularmovies.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.github.karadkar.popularmovies.data.local.models.BookmarkEntity
import io.reactivex.Flowable

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmarks WHERE movieId=:movieId LIMIT 1")
    fun getBookmark(movieId: Int): Flowable<BookmarkEntity>

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Flowable<List<BookmarkEntity>>
}