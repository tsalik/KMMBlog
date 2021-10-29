package me.tsalikis.blog.android

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers

/**
 * Checks whether the visibility of the View is GONE on the code
 *
 * The View exists on the layout hierarchy but has visibility GONE.
 *
 * This is less strict than `doesNotExist` and more strict than `not(isDisplayed())`
 *
 */
val isGone: ViewAssertion = matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))

/**
 * Checks that the view exists and is displayed on the screen.
 *
 * This could fail for example if another View is drawing completely over the View or the View is
 * in a scrollable container with effective visibility VISIBLE but is the last item on the scroll list
 * and is not visible on the screen.
 *
 * This is more strict than using `withEffectiveVisibility(Visibility.VISIBLE))`
 */
val isDisplayed: ViewAssertion = matches(ViewMatchers.isDisplayed())