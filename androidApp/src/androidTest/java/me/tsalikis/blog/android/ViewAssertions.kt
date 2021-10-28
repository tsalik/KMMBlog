package me.tsalikis.blog.android

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers

val isGone: ViewAssertion = matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))

val isDisplayed: ViewAssertion = matches(ViewMatchers.isDisplayed())