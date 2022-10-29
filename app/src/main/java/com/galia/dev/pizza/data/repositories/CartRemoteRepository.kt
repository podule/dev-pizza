package com.galia.dev.pizza.data.repositories

import androidx.datastore.core.DataStore
import com.galia.dev.pizza.OrderProto
import com.galia.dev.pizza.api.ApiService
import javax.inject.Inject

class CartRemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<OrderProto>
) : CartRepository {


}