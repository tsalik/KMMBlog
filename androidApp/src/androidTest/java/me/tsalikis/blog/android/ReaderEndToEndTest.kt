package me.tsalikis.blog.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ReaderEndToEndTest {

    @Test
    fun shows_android_specific_greeting() {
        launchActivity<MainActivity>()

        onView(withId(R.id.text_view)).check(matches(allOf(isDisplayed(), withText(containsString("Android")))))
    }

}