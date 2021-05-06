package me.tsalikis.blog.android

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import java.util.concurrent.TimeUnit

class FakeBlogServer {

    private val mockWebServer = MockWebServer()

    fun start() {
        mockWebServer.start(8080)
    }

    fun hasReceivedAllPostsRequest() {
        val request = mockWebServer.takeRequest(2, TimeUnit.SECONDS)
        assertNotNull("Blog timeout after 2 seconds", request)
        requireNotNull(request)
        assertEquals("GET", request.method)
        assertEquals("/posts/index.json", request.path)
    }

    fun noPosts() {
        val response = MockResponse()
        response.setBody("[]")
        response.setHeader("Content-Type", "application/json")
        mockWebServer.enqueue(response)
    }

    fun stop() {
        mockWebServer.shutdown()
    }

}