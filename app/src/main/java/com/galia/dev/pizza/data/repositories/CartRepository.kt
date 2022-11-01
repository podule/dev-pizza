package com.galia.dev.pizza.data.repositories

import com.galia.dev.pizza.api.models.OrderedPizza
import kotlinx.coroutines.flow.Flow


interface CartRepository {
    suspend fun getOrderedPizza(): Flow<List<OrderedPizza>>
    suspend fun isOrderCreated(): Boolean
}