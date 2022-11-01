package com.galia.dev.pizza.data.repositories

import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.OrderedPizza
import com.galia.dev.pizza.data.source.OrderDataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreSource: OrderDataStoreSource
) : CartRepository {

    override suspend fun getOrderedPizza(): Flow<List<OrderedPizza>> {
        return flow {
            val orderId = dataStoreSource.orderDataStream.first().id
            val list = if (orderId != 0) {
                val orderData = apiService.getOrderData(orderId)
                orderData.orderedPizzaList
            } else {
                emptyList()
            }
            emit(list)
        }
    }

    override suspend fun isOrderCreated() = dataStoreSource.orderDataStream.first().id != 0


}