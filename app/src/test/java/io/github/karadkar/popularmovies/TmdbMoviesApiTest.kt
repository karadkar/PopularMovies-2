package io.github.karadkar.popularmovies

import io.github.karadkar.popularmovies.data.remote.TmdbMoviesApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.parameter.parametersOf
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import java.nio.charset.StandardCharsets

@RunWith(RobolectricTestRunner::class)
class TmdbMoviesApiTest : KoinTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var tmdbMoviesApi: TmdbMoviesApi
    @Before
    fun setup() {
        startKoin(testDiModules)
        mockWebServer = get()
        val retrofit: Retrofit = get { parametersOf(mockWebServer.url("/")) }
        tmdbMoviesApi = retrofit.create(TmdbMoviesApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getPopularMovies() {
        mockWebServer.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(getResponseFromJson("popular"))
        })

        tmdbMoviesApi.getPopularMovies("api-key")
                .test()
                .run {
                    assertNoErrors()
                    assertValueCount(1)
                    val result = values()[0]
                    assert(result.page == 1)
                }
    }


    fun getResponseFromJson(fileName: String): String {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("tmdb-api/$fileName.json")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }
}