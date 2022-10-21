package com.galia.dev.pizza.data.repositories

import androidx.paging.PagingData
import com.galia.dev.pizza.OrderProto
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Pizza
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMenuRepository: MenuRepository {
    var menuPizza: List<Pizza>? = null
    var discounts: List<Discount>? = null

    override fun getMenuResultStream(sortedFlag: Boolean): Flow<PagingData<Pizza>> {
        return flow {
            emit(PagingData.from(menuPizza!!))
        }
    }

    override fun getDiscounts(): Flow<List<Discount>> {
        return flow {
            emit(discounts!!)
        }
    }

    override fun getPizza(id: Int): Flow<Pizza> {
        return flow {
            emit(menuPizza!![id])
        }
    }

    override suspend fun addPizzaToCart(pizzaId: Int) {

    }

    override fun getOrderProto(): Flow<OrderProto> {
        return flow { emit( OrderProto.getDefaultInstance() ) }
    }
}