package com.galia.dev.pizza.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.Pizza

class MenuPagingSource(
    private val apiService: ApiService,
): PagingSource<Int, Pizza>() {
    override fun getRefreshKey(state: PagingState<Int, Pizza>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pizza> {
        try {
            val page = params.key ?: PAGE_INIT
            val response = apiService.getPagingMenu(PAGE_SIZE_DEFAULT, page)
            return LoadResult.Page(
                data = response.menu,
                prevKey = if (page == PAGE_INIT) null else page - 1,
                nextKey = if (response.menu.isEmpty()) null else page + 1
            )
        } catch (ex: java.lang.Exception) {
            return LoadResult.Error(ex)
        }
    }

    companion object {
        const val PAGE_SIZE_DEFAULT = 10
        const val PAGE_INIT = 0
    }
}