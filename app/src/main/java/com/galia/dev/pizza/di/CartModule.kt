package com.galia.dev.pizza.di

import com.galia.dev.pizza.data.repositories.CartRemoteRepository
import com.galia.dev.pizza.data.repositories.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CartModule {

    @Binds
    fun provideCartRepository(repository: CartRemoteRepository): CartRepository
}