package io.github.karadkar.popularmovies.data.local

import io.github.karadkar.popularmovies.utils.MovieEntityDataFactory
import org.junit.Test

class BookmarkDatabaseTest : BaseDatabaseTest<MovieDatabase>(MovieDatabase::class) {

    @Test
    fun updateBookmark() {
        val movies = MovieEntityDataFactory.getMovieEntities(50)
        database.movieDao().saveOrUpdate(movies)
        val movie = movies[10]

        // add as bookmarked
        database.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movie.id, bookmarked = true))
        database.bookmarkDao().getBookmark(movieId = movie.id)
                .test().assertValue { it.movieId == movie.id && it.bookmarked }

        // remove as bookmarked
        database.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movie.id, bookmarked = false))
        database.bookmarkDao().getBookmark(movieId = movie.id)
                .test().assertValue { it.movieId == movie.id && !it.bookmarked }
    }

    // deleting movie should delete bookmark entry
    @Test
    fun cascadeDelete() {
        val movie = MovieEntityDataFactory.getMovieEntity(100)
        database.movieDao().saveOrUpdate(listOf(movie))
        database.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movie.id, bookmarked = true))

        // todo: write delete
    }
}