package com.galia.dev.pizza.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.source.MenuPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRemoteRepository @Inject constructor(private val apiService: ApiService): MenuRepository {
    override suspend fun getMenu(): Flow<List<Pizza>> {
        return flow {
            val pizzaMenu = apiService.getMenu()
            emit(pizzaMenu.menu)
        }
    }

    override fun getMenuResultStream(): Flow<PagingData<Pizza>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = MenuPagingSource.PAGE_SIZE_DEFAULT),
            pagingSourceFactory = { MenuPagingSource(apiService) }
        ).flow
    }
}