package me.tsalikis.blog.android

import android.os.Bundle
import android.view.View
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
        val tv = findViewById<TextView>(R.id.text_view)
        withContext(Dispatchers.Main) {
            val posts = catalogPosts.byDescendingDate()
            if (posts.isEmpty()) {
                tv.setText(R.string.no_posts_yet)
            } else {
                tv.visibility = View.GONE
                val postsView = findViewById<RecyclerView>(R.id.posts)
                postsView.adapter = PostsAdapter(posts)

                postsView.visibility = View.VISIBLE
            }
        }
    }
}
