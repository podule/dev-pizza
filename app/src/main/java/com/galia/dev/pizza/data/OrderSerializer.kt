package com.galia.dev.pizza.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.galia.dev.pizza.OrderProto
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object OrderSerializer : Serializer<OrderProto> {
    override val defaultValue: OrderProto
        get() = OrderProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): OrderProto {
        try {
            return OrderProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: OrderProto, output: OutputStream) {
        t.writeTo(output)
    }

}