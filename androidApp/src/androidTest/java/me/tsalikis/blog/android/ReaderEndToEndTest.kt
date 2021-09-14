package me.tsalikis.blog.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import me.tsalikis.blog.PostDescription
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReaderEndToEndTest {

    private val blog = FakeBlogServer()
    private val blogReader = PostsRobot()

    @Before
    fun startServer() {
        blog.start()
    }

    @Test
    fun shows_no_posts_yet_for_empty_blog() {

        blogReader.browsePosts()

        blog.hasReceivedAllPostsRequest()

        blog.noPosts()

        blogReader.showsNoPostsYet()
    }

    @Test
    fun shows_single_post_for_single_entry() {
        blogReader.browsePosts()

        blog.hasReceivedAllPostsRequest()

        val blogEntries = listOf(
            PostDescription(
                title = "Blogging with Hugo",
                path = "/posts/blogging-with-hugo",
                summary = "How I built and deployed this blog",
                publishDate = "2018-01-14"
            ),
            PostDescription(
                title = "A second post",
                path = "posts/a-second-post",
                summary = "This is the second post",
                publishDate = "2020-01-16"
            )
        )
        blog.post(blogEntries)

        blogReader.showsPostAt(
            position = 0,
            title = "Blogging with Hugo",
            description = "How I built and deployed this blog",
            publishDate = "2018-01-14"
        )
        blogReader.showsPostAt(
            position = 1,
            title = "A second post",
            description = "This is the second post",
            publishDate = "2020-01-16"
        )
    }

    @After
    fun stopBlog() {
        blog.stop()
    }

}