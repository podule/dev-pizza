package com.galia.dev.pizza.api

import com.galia.dev.pizza.api.models.*

class FakeApiService(): ApiService {
    var failureMsg: String? = null
    var discounts: List<Discount>? = null
    var menuPizza: MenuPizza? = null

    override suspend fun getDiscounts(): List<Discount> {
        failureMsg?.let {
            throw Exception(it)
        }
        return discounts ?: emptyList()
    }

    override suspend fun getPagingMenu(size: Int, index: Int, isSort: Int): MenuPizza {
        failureMsg?.let {
            throw Exception(it)
        }
        return menuPizza!!
    }

    override suspend fun getPizza(id: Int): Pizza {
        failureMsg?.let {
            throw Exception(it)
        }
        return menuPizza!!.menu[id]
    }

    override suspend fun addPizzaToCart(orderId: Int, pizzaId: Int): Int {
        return 1
    }

    override suspend fun createOrder(): Order {
        return Order(1)
    }

    override suspend fun getOrderData(id: Int): OrderInfo {
        return OrderInfo(1, emptyList())
    }
}