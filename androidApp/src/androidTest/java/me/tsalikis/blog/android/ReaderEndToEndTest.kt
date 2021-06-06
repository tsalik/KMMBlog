package me.tsalikis.blog.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ReaderEndToEndTest {

    private val blog = FakeBlogServer()
    private val blogReader = PostsRobot()

    @Test
    fun shows_no_posts_yet_for_empty_blog() {
        blog.start()

        blogReader.browsePosts()

        blog.hasReceivedAllPostsRequest()

        blog.noPosts()

        blogReader.showsNoPostsYet()
    }

    @After
    fun stopBlog() {
        blog.stop()
    }

}