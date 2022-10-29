package com.galia.dev.pizza.cart

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.galia.dev.pizza.api.models.OrderedPizza
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    private val _orderedPizza = getStubOrderedPizza().toMutableStateList()
    val orderedPizza: List<OrderedPizza>
        get() = _orderedPizza
}

fun getStubOrderedPizza() =
    List(8) { i ->
        OrderedPizza(
            Pizza(
                i.toLong(),
                "Пепперони",
                "Средняя пицца на тонком тесте",
                "test",
                "test",
                140
            ), 1
        )
    }