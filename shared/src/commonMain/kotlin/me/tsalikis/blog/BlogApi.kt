package me.tsalikis.blog

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal expect val httpClient: HttpClient

class BlogApi(private val hostname: String) {

    suspend fun posts(): PostResponse {
        return httpClient.get("${hostname}posts/index.json")
    }

}