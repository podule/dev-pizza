package com.galia.dev.pizza.data.source

import androidx.paging.PagingSource
import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.FakeApiService
import com.galia.dev.pizza.api.models.MenuPizza
import com.galia.dev.pizza.data.mockMenu as menu
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MenuPagingSourceTest {

    private val mockMenu = menu
    private lateinit var api: ApiService

    @Before
    fun setUp() {
        api = FakeApiService()
    }

    @Test
    fun loadReturnsLoadResultError() = runTest {
        (api as FakeApiService).failureMsg = "test"

        val pagingSource = MenuPagingSource(api, false)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals(
            "test",
            (result as PagingSource.LoadResult.Error).throwable.message
        )
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        (api as FakeApiService).menuPizza = MenuPizza(mockMenu)
        val pagingSource = MenuPagingSource(api, false)
        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(mockMenu[0], mockMenu[1]),
                prevKey = null,
                nextKey = 1
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
        )
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        (api as FakeApiService).menuPizza = MenuPizza(emptyList())
        val pagingSource = MenuPagingSource(api, false)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertTrue(result is PagingSource.LoadResult.Page)

        assertTrue((result as PagingSource.LoadResult.Page).nextKey == null)
    }
}
