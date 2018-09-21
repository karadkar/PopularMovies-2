package io.github.karadkar.popularmovies.data

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.karadkar.popularmovies.BuildConfig
import io.github.karadkar.popularmovies.MovieListViewModel
import io.github.karadkar.popularmovies.data.remote.TmdbMoviesApi
import io.github.karadkar.popularmovies.utils.AppConstants
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Main app module
 */

val MovieListModule: Module = module {

    // provide singleton of list repository
    single {
        DummyMovieListRepository() as MovieListRepository
    }

    // provide new instance of ViewModel
    viewModel {
        MovieListViewModel(respository = get())
    }
}

val netWorkModule = module {

    // rx java call for retrofit
    single { RxJava2CallAdapterFactory.create() }

    // converter
    single<GsonConverterFactory> { GsonConverterFactory.create() }

    // gson
    single { Gson() }

    // okhttp cache
    single {
        val cacheSize = 10 * 1024 * 1024 // 10MB
        return@single Cache(get<Context>().cacheDir, cacheSize.toLong())
    }

    // context
    single { androidContext() }

    // Http logging
    single {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return@single interceptor
    }

    // okhttp client
    single<OkHttpClient> {
        val client = OkHttpClient.Builder()
                .cache(get()) // get Cache
                .addInterceptor(get<HttpLoggingInterceptor>())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)

        return@single client.build()
    }

    // thi url can be overriden by mockWebServer url during test
    single<HttpUrl>("base-url") {
        HttpUrl.parse(AppConstants.BASE_URL)!!
    }

    // retrofit
    single<Retrofit> {
        Retrofit.Builder().baseUrl(get<HttpUrl>("base-url"))
                .addConverterFactory(get<GsonConverterFactory>())
                .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
                .client(get())
                .build()
    }
}

val tmdbApiModule = module {
    single<TmdbMoviesApi> {
        val retrofit: Retrofit = get()
        retrofit.create(TmdbMoviesApi::class.java)
    }
}

/**
 * Module list
 */
val appModules = mutableListOf(netWorkModule, tmdbApiModule, MovieListModule)