package me.tsalikis.blog

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal expect val httpClient: HttpClient

class BlogApi(private val hostname: String) : BlogRepository {

    override suspend fun catalogByDescendingDate(): List<PostDescription> {
        val postResponse = httpClient.get<PostResponse>("${hostname}posts/index.json")
        return postResponse.data
    }

}