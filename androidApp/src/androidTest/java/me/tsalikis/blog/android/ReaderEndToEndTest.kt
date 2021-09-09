package me.tsalikis.blog.android

import androidx.test.ext.junit.runners.AndroidJUnit4
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

        blog.post(title = "Blogging with Hugo", path = "/posts/blogging-with-hugo", description = "How I built and deployed this blog", publishDate = "2018-01-14")

        blogReader.showsAPost(
            title = "Blogging with Hugo",
            description = "How I built and deployed this blog",
            publishDate = "2018-01-14"
        )
    }

    @After
    fun stopBlog() {
        blog.stop()
    }

}