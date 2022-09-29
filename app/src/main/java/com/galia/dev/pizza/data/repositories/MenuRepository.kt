package com.galia.dev.pizza.data.repositories

import androidx.paging.PagingData
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.api.models.Discount
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenuResultStream(): Flow<PagingData<Pizza>>
    fun getDiscounts(): Flow<List<Discount>>
}