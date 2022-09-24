package com.galia.dev.pizza.api.models

data class Pizza(
    val id: Long,
    val title: String,
    val description: String,
    val url: String,
    val composition: String,
    val price: Int
)