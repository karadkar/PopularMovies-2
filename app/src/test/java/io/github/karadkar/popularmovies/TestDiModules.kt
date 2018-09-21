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

private val contextModule = module {
    // context
    single(override = true) {
        RuntimeEnvironment.application as Context
    }
}

private val mockWebModule = module {
    // mock web  server
    factory { MockWebServer() }

}

val testApiServiceModules = appModules + contextModule + mockWebModule
val testModules = appModules + contextModule