package me.tsalikis.blog.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tsalikis.blog.BlogApi
import me.tsalikis.blog.CatalogPosts

class MainActivity : AppCompatActivity() {

    val catalogPosts = CatalogPosts(BlogApi(BuildConfig.HOSTNAME))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainScope().launch { loadPosts() }
    }

    private suspend fun loadPosts() {
        val noPostsMessage = findViewById<TextView>(R.id.noPostsMessage)
        val progressView = findViewById<ProgressBar>(R.id.progressView)
        progressView.visibility = View.VISIBLE
        withContext(Dispatchers.Main) {
            val posts = catalogPosts.byDescendingDate()
            progressView.visibility = View.GONE
            if (posts.isEmpty()) {
                noPostsMessage.visibility = View.VISIBLE
                noPostsMessage.setText(R.string.no_posts_yet)
            } else {
                noPostsMessage.visibility = View.GONE
                val postsView = findViewById<RecyclerView>(R.id.posts)
                postsView.adapter = PostsAdapter(posts)

                postsView.visibility = View.VISIBLE
            }
        }
    }
}
