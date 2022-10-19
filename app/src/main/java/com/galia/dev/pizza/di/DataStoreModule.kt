package com.galia.dev.pizza.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.galia.dev.pizza.OrderProto
import com.galia.dev.pizza.data.OrderSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val DATA_STORE_FILE_NAME = "schema.pb"

@InstallIn(SingletonComponent::class)
@Module
interface DataStoreModule {

    companion object {

        @Singleton
        @Provides
        fun provideDataStoreOrder(@ApplicationContext appContext: Context): DataStore<OrderProto> {
            return DataStoreFactory.create(
                serializer = OrderSerializer,
                produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) }
            )
        }
    }
}