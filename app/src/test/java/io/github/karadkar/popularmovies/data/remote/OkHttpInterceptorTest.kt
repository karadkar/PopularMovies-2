package io.github.karadkar.popularmovies.data.remote

import io.github.karadkar.popularmovies.mockResponse
import io.github.karadkar.popularmovies.testApiServiceModules
import io.github.karadkar.popularmovies.utils.JsonUtils
import okhttp3.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [23])
class OkHttpInterceptorTest : KoinTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var mockStatusWebServer: MockWebServer
    lateinit var tmdbMoviesApi: TmdbMoviesApi

    @Before
    fun setup() {
        StandAloneContext.startKoin(testApiServiceModules + module {
            // override provided okhttp client
            single<OkHttpClient>(override = true) {
                OkHttpClient.Builder()
                        .addInterceptor(statusInterceptor(mockStatusWebServer.url("/")))
                        .build()
            }
        })

        mockWebServer = get()
        mockStatusWebServer = get()
        val retrofit: Retrofit = get { parametersOf(mockWebServer.url("/")) }
        tmdbMoviesApi = retrofit.create(TmdbMoviesApi::class.java)

        mockWebServer.mockResponse {
            setResponseCode(200)
            setBody(JsonUtils.readJsonFile("popular"))
        }
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
        mockWebServer.shutdown()
        mockStatusWebServer.shutdown()
    }

    /**
     * test should pass when status api call gives http code 200
     */
    @Test
    fun testStatusCallSuccess() {
        mockStatusWebServer.mockResponse {
            setResponseCode(200)
        }

        tmdbMoviesApi.getPopularMovies("api-key")
                .test()
                .assertNoErrors()
    }

    /**
     * test should fail when status api call doesn't provide http code 200
     */
    @Test
    fun testStatusCallFail() {
        mockStatusWebServer.mockResponse {
            setResponseCode(404)
        }

        tmdbMoviesApi.getPopularMovies("api-key")
                .test()
                .assertError { it.message == "status call failed" }
    }

    private fun statusInterceptor(statusUrl: HttpUrl): Interceptor {
        return Interceptor {
            val mainRequest = it.request()
            if (mainRequest.method() == "GET") {
                val client = OkHttpClient.Builder().build()
                val statusRequest = Request.Builder()
                        .url(statusUrl)
                        .build()

                val statusResponse: Response = client.newCall(statusRequest).execute()
                statusResponse.use { response ->
                    if (response.code() != 200) {
                        throw Exception("status call failed")
                    }
                    return@Interceptor it.proceed(mainRequest)
                }
            } else {
                return@Interceptor it.proceed(mainRequest)
            }
        }
    }

}
