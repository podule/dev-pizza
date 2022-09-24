package com.galia.dev.pizza.api

import com.galia.dev.pizza.api.models.MenuPizza
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("menu")
    suspend fun getMenu(): MenuPizza

    companion object {
        private const val BASE_URL = "https://29af7f1a-4f96-4ccb-9de2-b15ec07e0078.mock.pstmn.io/"

        fun create(): ApiService {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiService::class.java)
        }
    }
}