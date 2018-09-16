package io.github.karadkar.popularmovies

import io.github.karadkar.popularmovies.data.remote.TmdbMoviesApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Assert.assertEquals
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
@Config(manifest = Config.NONE, sdk = [23])
class TmdbMoviesApiTest : KoinTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var tmdbMoviesApi: TmdbMoviesApi
    @Before
    fun setup() {
        startKoin(testApiServiceModules)
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
                    val tmdbResult = values()[0]
                    assertEquals(tmdbResult.page, 1)
                    assertEquals(tmdbResult.results.size, 20)
                    assertEquals(tmdbResult.results[0].title, "The Nun")
                }
    }


    fun getResponseFromJson(fileName: String): String {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("tmdb-api/$fileName.json")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }
}