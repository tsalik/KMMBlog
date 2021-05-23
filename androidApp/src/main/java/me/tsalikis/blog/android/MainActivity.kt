package me.tsalikis.blog.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tsalikis.blog.BlogApi
import me.tsalikis.blog.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    val blog = BlogApi(BuildConfig.HOSTNAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        MainScope().launch { loadPosts() }
    }

    private suspend fun loadPosts() {
        withContext(Dispatchers.Main) {
            val posts = blog.posts()
            if (posts.data.isEmpty()) {
                val tv: TextView = findViewById(R.id.text_view)
                tv.setText(R.string.no_posts_yet)
            }
        }
    }
}
