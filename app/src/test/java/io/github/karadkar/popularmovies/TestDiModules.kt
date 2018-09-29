package io.github.karadkar.popularmovies

import android.arch.persistence.room.Room
import android.content.Context
import io.github.karadkar.popularmovies.data.appModules
import io.github.karadkar.popularmovies.data.local.MovieDatabase
import io.github.karadkar.popularmovies.utils.RxScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module.module
import org.robolectric.RuntimeEnvironment

private val localDataModule = module {
    // context
    factory(override = true) {
        RuntimeEnvironment.application as Context
    }

    // movie database
    single(override = true) {
        Room.inMemoryDatabaseBuilder(get(), MovieDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    // scheduler that schedules all tasks in shared FIFO manner
    single<RxScheduler>(override = true) {
        object : RxScheduler {
            override fun mainThread(): Scheduler = Schedulers.trampoline()
            override fun workerThread(): Scheduler = Schedulers.trampoline()
        }
    }
}

private val mockWebModule = module {
    // mock web  server
    factory { MockWebServer() }

}

val robolectricModules = appModules + localDataModule + mockWebModule
val testApiServiceModules = robolectricModules
