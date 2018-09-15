package io.github.karadkar.popularmovies

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.karadkar.popularmovies.data.netWorkModule
import io.github.karadkar.popularmovies.data.tmdbApiModule
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module.module
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    // mock web  server
    factory { MockWebServer() }

    // override cache dir with robolectric
    single(override = true) {
        val cacheSize = 10 * 1024 * 1024 // 10MB
        return@single Cache(RuntimeEnvironment.application.cacheDir, cacheSize.toLong())
    }

    // retrofit
    factory<Retrofit>(override = true) { (baseUrl: HttpUrl) ->
        Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(get<GsonConverterFactory>())
                .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
                .client(get())
                .build()
    }
}

val testDiModules = listOf(netWorkModule, tmdbApiModule, retrofitModule)