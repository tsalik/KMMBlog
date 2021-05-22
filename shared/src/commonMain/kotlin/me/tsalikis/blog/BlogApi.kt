package me.tsalikis.blog

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal expect val httpClient: HttpClient

class BlogApi {

    suspend fun posts(): PostResponse {
        return httpClient.get("http://192.168.1.7:1313/posts/index.json")
    }

}