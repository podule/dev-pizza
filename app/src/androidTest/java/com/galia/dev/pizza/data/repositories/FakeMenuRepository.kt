package com.galia.dev.pizza.data.repositories

import androidx.paging.PagingData
import com.galia.dev.pizza.OrderProto
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Pizza
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeMenuRepository : MenuRepository {
    override fun getMenuResultStream(sortedFlag: Boolean): Flow<PagingData<Pizza>> {
        val menu = mutableListOf(
            Pizza(2, "Margo", "", "", "", 250),
            Pizza(0, "Pepperoni", "", "", "", 150),
            Pizza(3, "Chicago", "", "", "", 200),
        )
        return flow {
            if (sortedFlag) menu.sortBy { it.price }
            emit(PagingData.from(menu))
        }
    }

    override fun getDiscounts(): Flow<List<Discount>> {
        return flow {
            emit(listOf(Discount("")))
        }
    }

    override fun getPizza(id: Int): Flow<Pizza> {
        return flow {
            emit(Pizza(0, "Pepperoni", "", "", "", 150))
        }
    }

    override suspend fun addPizzaToCart(pizzaId: Int) {

    }

    override fun getOrderProto(): Flow<OrderProto> {
        return flow { emit( OrderProto.getDefaultInstance() ) }
    }

}