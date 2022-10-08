package com.galia.dev.pizza.data

import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Pizza

val mockMenu = listOf(
    Pizza(0, "test1", "test", "test", "test", 10),
    Pizza(0, "test2", "test", "test", "test", 10),
)

val mockDiscounts = listOf(
    Discount("test1"),
    Discount("test2"),
    Discount("test3"),
    Discount("test4"),
)