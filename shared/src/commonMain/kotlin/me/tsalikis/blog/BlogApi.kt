package me.tsalikis.blog

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal expect val httpClient: HttpClient

class BlogApi {

    suspend fun posts(): List<PostDescription> {
        return httpClient.get("http://localhost:8080/posts/index.json")
    }

}