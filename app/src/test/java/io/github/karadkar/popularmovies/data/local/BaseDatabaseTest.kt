package io.github.karadkar.popularmovies.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.reflect.KClass

@RunWith(RobolectricTestRunner::class)
abstract class BaseDatabaseTest<T : RoomDatabase>(private val klass: KClass<T>) {
    @Rule
    @JvmField
    var executor = InstantTaskExecutorRule()

    protected lateinit var database: T

    @Before
    fun setupDb() {
        database = getInMemoryDatabase(RuntimeEnvironment.application.applicationContext)
    }

    private fun getInMemoryDatabase(context: Context): T {
        return Room.inMemoryDatabaseBuilder(context, klass.java)
                .allowMainThreadQueries().build()
    }

    @After
    fun close() {
        database.close()
    }
}