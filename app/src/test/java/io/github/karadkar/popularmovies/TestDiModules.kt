package io.github.karadkar.popularmovies

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.karadkar.popularmovies.data.appModules
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module.module
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val contextModule = module {
    // context
    single(override = true) {
        RuntimeEnvironment.application as Context
    }
}

val retrofitModule = module {
    // mock web  server
    factory { MockWebServer() }

    // retrofit
    factory<Retrofit>(override = true) { (baseUrl: HttpUrl) ->
        Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(get<GsonConverterFactory>())
                .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
                .client(get())
                .build()
    }
}

val testApiServiceModules = appModules + contextModule + retrofitModule
val testModules = appModules + contextModule