package com.galia.dev.pizza.data.repositories

import androidx.paging.PagingData
import com.galia.dev.pizza.api.models.Pizza
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getMenu(): Flow<List<Pizza>>
    fun getMenuResultStream(): Flow<PagingData<Pizza>>
}