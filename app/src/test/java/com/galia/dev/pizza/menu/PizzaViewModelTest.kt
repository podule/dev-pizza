package com.galia.dev.pizza.menu

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.galia.dev.pizza.data.mockMenu as menu
import com.galia.dev.pizza.data.repositories.FakeMenuRepository
import com.galia.dev.pizza.data.repositories.MenuRepository
import com.galia.dev.pizza.pizza.PizzaViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PizzaViewModelTest {

    private val mockMenu = menu
    private val id = 0
    private lateinit var repository: MenuRepository
    private lateinit var viewModel: PizzaViewModel

    @get:Rule
    val instantExecutorRole = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = FakeMenuRepository()
        val savedStateHandle = SavedStateHandle().apply {
            set(PizzaViewModel.PIZZA_ID_SAVED_STATE_KEY, id)
        }
        viewModel = PizzaViewModel(savedStateHandle, repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPizzaReturnFlowPizza() = runTest {
        (repository as FakeMenuRepository).menuPizza = mockMenu

        assertEquals(
            mockMenu[id],
            viewModel.pizza.first()
        )
    }

    @Test
    fun isLoadInitTrue() {
        assertTrue(viewModel.isLoad.value!!)
    }

    @Test
    fun setLoadValueFalse() {
        viewModel.setLoad(false)
        assertFalse(viewModel.isLoad.value!!)
    }
}