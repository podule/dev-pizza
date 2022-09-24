package com.galia.dev.pizza.data.repositories

import com.galia.dev.pizza.api.models.Pizza
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getMenu(): Flow<List<Pizza>>
}