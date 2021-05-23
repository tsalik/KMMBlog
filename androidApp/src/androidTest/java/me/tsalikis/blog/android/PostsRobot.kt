package me.tsalikis.blog.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.awaitility.Awaitility
import org.hamcrest.core.AllOf
import java.util.concurrent.TimeUnit

class PostsRobot {

    fun requestAllPosts() {
        launchActivity<MainActivity>()
    }

    fun showsNoPostsYet() {
        Awaitility.await().atMost(2, TimeUnit.SECONDS).untilAsserted {
            onView(withId(R.id.text_view)).check(matches(
                AllOf.allOf(
                    isDisplayed(),
                    ViewMatchers.withText(R.string.no_posts_yet)
                )
            ))
        }
    }

}