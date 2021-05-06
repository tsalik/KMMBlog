package me.tsalikis.blog.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.AllOf

class PostsRobot {

    fun requestAllPosts() {
        launchActivity<MainActivity>()
    }

    fun showsNoPostsYet() {
        Thread.sleep(2000) //todo: This is a quick workaround for the test to stabilize, investigate using IdlingResources or something
        onView(withId(R.id.text_view)).check(matches(
            AllOf.allOf(
                isDisplayed(),
                ViewMatchers.withText(R.string.no_posts_yet)
            )
        ))
    }

}