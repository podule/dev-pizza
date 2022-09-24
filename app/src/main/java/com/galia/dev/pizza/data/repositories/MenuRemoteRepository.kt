package com.galia.dev.pizza.data.repositories

import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.Pizza
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
}