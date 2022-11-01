package com.galia.dev.pizza.api.models

import com.squareup.moshi.Json

data class OrderedPizza(
    val id: Int,
    @Json(name = "pizzaVO") val pizza: Pizza,
    val count: Int
)
