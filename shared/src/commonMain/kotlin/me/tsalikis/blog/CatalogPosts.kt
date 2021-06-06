package me.tsalikis.blog

/**
 * A Use Case for listing the catalog of all the available posts
 */
class CatalogPosts(private val repository: BlogRepository) {

    suspend fun byDescendingDate(): List<PostDescription> {
        return repository.catalogByDescendingDate()
    }

}