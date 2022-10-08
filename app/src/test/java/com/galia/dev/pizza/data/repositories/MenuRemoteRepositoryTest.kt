package com.galia.dev.pizza.data.repositories

import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.FakeApiService
import com.galia.dev.pizza.api.models.MenuPizza
import com.galia.dev.pizza.data.mockMenu as menu
import com.galia.dev.pizza.data.mockDiscounts as discounts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MenuRemoteRepositoryTest {

    private lateinit var api: ApiService
    private lateinit var repository: MenuRemoteRepository
    private val mockMenu = menu
    private val mockDiscounts = discounts

    @Before
    fun setUp() {
        api = FakeApiService()
        repository = MenuRemoteRepository(api)
    }

    @Test
    fun getDiscountsReturnDiscountsList() = runTest {
        (api as FakeApiService).discounts = mockDiscounts
        val result = repository.getDiscounts().toList()
        assertEquals(listOf(mockDiscounts), result)
    }

    @Test
    fun getDiscountsThrowException()  {
        (api as FakeApiService).failureMsg = "test"
        assertThrows("test", Exception::class.java){
            runTest(UnconfinedTestDispatcher()) {  repository.getDiscounts().toList() }
        }
    }

    @Test
    fun getPizzaReturnPizzaById() = runTest {
        (api as FakeApiService).menuPizza = MenuPizza(mockMenu)
        val result = repository.getPizza(0).first()
        assertEquals(mockMenu[0], result)
    }

    @Test
    fun getPizzaTrowException()  {
        (api as FakeApiService).failureMsg = "test"
        assertThrows("test", Exception::class.java){
            runTest(UnconfinedTestDispatcher()) {  repository.getPizza(0).first() }
        }
    }

}