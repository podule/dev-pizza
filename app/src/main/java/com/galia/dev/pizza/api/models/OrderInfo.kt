package com.galia.dev.pizza.api.models

data class OrderInfo(
    val id: Int,
    val orderedPizzaList: List<OrderedPizza>
)
