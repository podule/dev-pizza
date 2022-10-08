package com.galia.dev.pizza.menu

import com.galia.dev.pizza.data.repositories.FakeMenuRepository
import com.galia.dev.pizza.data.repositories.MenuRepository
import com.galia.dev.pizza.data.mockDiscounts as discounts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class MenuViewModelTest {

    private val mockDiscounts = discounts

    private lateinit var repository: MenuRepository
    private lateinit var viewModel: MenuViewModel

    @Before
    fun setUp() {
        repository = FakeMenuRepository()
        viewModel = MenuViewModel(repository)
    }

    @Test
    fun getDiscounts() = runTest {
        (repository as FakeMenuRepository).discounts = mockDiscounts

        val result = viewModel.discounts.toList()
        assertEquals(listOf(mockDiscounts), result)
    }

}