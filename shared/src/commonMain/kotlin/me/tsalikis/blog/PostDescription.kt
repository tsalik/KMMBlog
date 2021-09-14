package me.tsalikis.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDescription(
    val title: String,
    val path: String,
    @SerialName("description")
    val summary: String,
    val publishDate: String
)

@Serializable
data class PostResponse(
    val data: List<PostDescription>
)
