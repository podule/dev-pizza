package com.galia.dev.pizza.menu

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.galia.dev.pizza.R
import com.galia.dev.pizza.data.repositories.FakeMenuRepository
import com.galia.dev.pizza.data.repositories.MenuRepository
import com.galia.dev.pizza.di.MenuModule
import com.galia.dev.pizza.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(MenuModule::class)
@HiltAndroidTest
class MenuFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val repository: MenuRepository = FakeMenuRepository()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun orderPizzaByPrice() {
        launchFragmentInHiltContainer<MenuFragment>(Bundle())

        onView(
            allOf(
                withParent(
                    allOf(
                        withChild(withId(R.id.menu_item)),
                        withId(R.id.recycler_view_category),
                    )
                ),
                withParentIndex(1)
            )
        ).check(
            matches(withChild(withText("Pepperoni")))
        )
    }

    @Test
    fun orderSortedPizzaByPrice() {
        launchFragmentInHiltContainer<MenuFragment>(Bundle())


        onView(withId(R.id.sorted)).perform(click())

        onView(
            allOf(
                withParent(
                    allOf(
                        withChild(withId(R.id.menu_item)),
                        withId(R.id.recycler_view_category),
                    )
                ),
                withParentIndex(0)
            )
        ).check(
            matches(withChild(withText("Pepperoni")))
        )
    }

    @Test
    fun testNavigationToPizzaModalSheet() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<MenuFragment>(Bundle()) {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(allOf(withId(R.id.pizza_title), withText("Pepperoni"))).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.pizzaModalSheet)
    }

}