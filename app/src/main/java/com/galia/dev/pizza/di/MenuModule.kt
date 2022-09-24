package com.galia.dev.pizza.di

import com.galia.dev.pizza.data.repositories.MenuRemoteRepository
import com.galia.dev.pizza.data.repositories.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface MenuModule {

    @Binds
    fun provideMenuRepository(repository: MenuRemoteRepository): MenuRepository
}