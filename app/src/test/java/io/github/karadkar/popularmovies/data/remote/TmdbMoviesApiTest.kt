package io.github.karadkar.popularmovies.data.remote

import io.github.karadkar.popularmovies.mockResponse
import io.github.karadkar.popularmovies.testApiServiceModules
import io.github.karadkar.popularmovies.utils.JsonUtils
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [23])
class TmdbMoviesApiTest : KoinTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var tmdbMoviesApi: TmdbMoviesApi
    @Before
    fun setup() {
        startKoin(testApiServiceModules + module {
            single<HttpUrl>(name = "base-url", override = true) {
                mockWebServer.url("/")
            }
        })
        mockWebServer = get()
        val retrofit: Retrofit = get()
        tmdbMoviesApi = retrofit.create(TmdbMoviesApi::class.java)
    }

    @After
    fun tearDown() {
        stopKoin()
        mockWebServer.shutdown()
    }

    @Test
    fun getPopularMovies() {
        mockWebServer.mockResponse {
            setResponseCode(200)
            setBody(JsonUtils.readJsonFile("popular"))
        }

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

    @Test
    fun getTopRatedMovies() {
        mockWebServer.mockResponse {
            setResponseCode(200)
            setBody(JsonUtils.readJsonFile("top-rated"))
        }

        tmdbMoviesApi.getTopRated("api-key")
                .test()
                .run {
                    assertNoErrors()
                    assertValueCount(1)
                    val tmdbResult = values()[0]
                    assertEquals(tmdbResult.page, 1)
                    assertEquals(tmdbResult.results.size, 20)
                    assertEquals(tmdbResult.results[0].title, "Dilwale Dulhania Le Jayenge")
                }
    }
}