package com.galia.dev.pizza.data.repositories

import androidx.datastore.core.DataStore
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.galia.dev.pizza.OrderProto
import com.galia.dev.pizza.api.ApiService
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Order
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.source.MenuPagingSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MenuRemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<OrderProto>
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
        }.catch { exception ->
            throw exception
        }
    }

    override fun getPizza(id: Int): Flow<Pizza> {
        return flow {
            emit(apiService.getPizza(id))
        }.catch { exception ->
            throw exception
        }
    }


    override suspend fun addPizzaToCart(pizzaId: Int) {
        var orderId = getOrderProtoId()
        if (orderId == 0) {
            val newOrder = getNewOrder().first()
            orderId = newOrder.id
            setOrderProtoId(orderId)
        }
        apiService.addPizza(orderId, pizzaId)
    }

    private suspend fun getNewOrder(): Flow<Order> {
        return flow {
            emit(apiService.createOrder())
        }.catch { ex ->
            throw ex
        }
    }

    private suspend fun getOrderProtoId() = dataStore.data.first().id

    private suspend fun setOrderProtoId(id: Int) {
        dataStore.updateData { order ->
            order.toBuilder().setId(id).build()
        }
    }


}