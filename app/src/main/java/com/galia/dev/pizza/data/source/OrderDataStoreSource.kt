package com.galia.dev.pizza.data.source

import androidx.datastore.core.DataStore
import com.galia.dev.pizza.OrderProto
import javax.inject.Inject

class OrderDataStoreSource @Inject constructor(
    private val orderDataStore: DataStore<OrderProto>
) {
    val orderDataStream = orderDataStore.data

    suspend fun setOrderProtoId(id: Int) {
        orderDataStore.updateData { order ->
            order.toBuilder().setId(id).build()
        }
    }
}