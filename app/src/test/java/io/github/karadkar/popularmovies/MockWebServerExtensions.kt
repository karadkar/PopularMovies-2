package io.github.karadkar.popularmovies

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

/**
 * mockWebServer.enqueue(MockResponse().apply {
 *      setResponseCode(200)
 *      setBody("response-body")
 * })
 */
fun MockWebServer.mockResponse(block: MockResponse.() -> Unit) {
    this.enqueue(MockResponse().apply(block))
}