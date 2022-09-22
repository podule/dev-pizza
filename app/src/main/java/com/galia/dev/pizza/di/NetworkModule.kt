package com.galia.dev.pizza.di

import com.galia.dev.pizza.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface NetworkModule {
    companion object {

        @Singleton
        @Provides
        fun provideApiService(): ApiService {
            return ApiService.create()
        }
    }
}