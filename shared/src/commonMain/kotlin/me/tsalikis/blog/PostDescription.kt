package me.tsalikis.blog

import kotlinx.serialization.Serializable

@Serializable
data class PostDescription(
    val title: String,
    private val path: String,
    val description: String,
    val publishDate: String
)

@Serializable
data class PostResponse(
    val data: List<PostDescription>
)
