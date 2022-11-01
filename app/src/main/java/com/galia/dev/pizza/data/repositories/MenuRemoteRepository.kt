package com.galia.dev.pizza.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Order
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.source.MenuPagingSource
import com.galia.dev.pizza.data.source.OrderDataStoreSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MenuRemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreSource: OrderDataStoreSource
) : MenuRepository {

    override fun getMenuResultStream(sortedFlag: Boolean): Flow<PagingData<Pizza>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = MenuPagingSource.PAGE_SIZE_DEFAULT
            ),
            pagingSourceFactory = { MenuPagingSource(apiService, sortedFlag) }
        ).flow
    }

    override fun getDiscounts(): Flow<List<Discount>> {
        return flow {
            emit(apiService.getDiscounts())
        }
    }

    override fun getPizza(id: Int): Flow<Pizza> {
        return flow {
            emit(apiService.getPizza(id))
        }
    }


    override suspend fun addPizzaToCart(pizzaId: Int) {
        var orderId = getOrderProto().first().id
        if (orderId == 0) {
            val newOrder = getNewOrder().first()
            orderId = newOrder.id
            dataStoreSource.setOrderProtoId(orderId)
        }
        apiService.addPizzaToCart(orderId, pizzaId)
    }

    private suspend fun getNewOrder(): Flow<Order> {
        return flow {
            emit(apiService.createOrder())
        }
    }

    override fun getOrderProto() = dataStoreSource.orderDataStream
}