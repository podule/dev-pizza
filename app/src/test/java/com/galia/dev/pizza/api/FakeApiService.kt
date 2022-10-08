package com.galia.dev.pizza.api

import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.MenuPizza
import com.galia.dev.pizza.api.models.Pizza

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
}