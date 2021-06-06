package me.tsalikis.blog

interface BlogRepository {

    suspend fun catalogByDescendingDate(): List<PostDescription>

}