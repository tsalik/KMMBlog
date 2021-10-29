package me.tsalikis.blog.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import java.util.concurrent.TimeUnit
import org.awaitility.Awaitility.await
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf

private const val timeout = 2L

class PostsRobot {

    private val posts = onView(withId(R.id.posts))
    private val progress = onView(withId(R.id.progressView))
    private val noPostsMessage = onView(withId(R.id.noPostsMessage))

    fun browsePosts() {
        launchActivity<MainActivity>()
        progress.check(isDisplayed)
        posts.check(isGone)
        noPostsMessage.check(isGone)
    }

    fun showsNoPostsYet() {
        await().atMost(timeout, TimeUnit.SECONDS).untilAsserted {
            noPostsMessage.check(isDisplayed)
            progress.check(isGone)
            posts.check(isGone)
        }
    }

    fun showsPostAt(position: Int, title: String, description: String, publishDate: String) {
        await().atMost(timeout, TimeUnit.SECONDS).untilAsserted {
            posts.check(matches(atPosition(position, postWith(title, description, publishDate))))
            progress.check(isGone)
            noPostsMessage.check(isGone)
        }
    }

    private fun postWith(title: String, description: String, publishDate: String): Matcher<View> {
        return allOf(
            text(R.id.title, title),
            text(R.id.publishDate, publishDate),
            text(R.id.description, description)
        )
    }

    private fun text(id: Int, value: String): Matcher<View> {
        return hasDescendant(
            allOf(
                withId(id),
                withText(value)
            )
        )
    }

}

fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

}