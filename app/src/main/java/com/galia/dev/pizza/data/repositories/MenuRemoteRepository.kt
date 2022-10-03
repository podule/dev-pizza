package com.galia.dev.pizza.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.source.MenuPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRemoteRepository @Inject constructor(private val apiService: ApiService): MenuRepository {

    override fun getMenuResultStream(sortedFlag: Boolean): Flow<PagingData<Pizza>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = MenuPagingSource.PAGE_SIZE_DEFAULT),
            pagingSourceFactory = { MenuPagingSource(apiService, sortedFlag) }
        ).flow
    }

    override fun getDiscounts(): Flow<List<Discount>> {
        return flow {
            emit(apiService.getDiscounts())
        }.catch { exception ->
            throw exception
        }
    }
}