package me.tsalikis.blog

import kotlinx.serialization.Serializable

@Serializable
data class PostDescription(
    private val title: String
)

@Serializable
data class PostResponse(
    val data: List<PostDescription>
)
