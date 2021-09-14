package me.tsalikis.blog.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import org.awaitility.Awaitility
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import java.util.concurrent.TimeUnit

class PostsRobot {

    private val posts = onView(withId(R.id.posts))

    fun browsePosts() {
        launchActivity<MainActivity>()
    }

    fun showsNoPostsYet() {
        Awaitility.await().atMost(2, TimeUnit.SECONDS).untilAsserted {
            onView(withId(R.id.text_view)).check(
                matches(
                    allOf(
                        isDisplayed(),
                        withText(R.string.no_posts_yet)
                    )
                )
            )
        }
    }

    fun showsPostAt(position: Int, title: String, description: String, publishDate: String) {
        Awaitility.await().atMost(2, TimeUnit.SECONDS).untilAsserted {
            posts.check(matches(atPosition(position, postWith(title, description, publishDate))))
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