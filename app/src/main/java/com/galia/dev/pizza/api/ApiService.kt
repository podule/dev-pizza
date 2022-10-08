package com.galia.dev.pizza.api

import com.galia.dev.pizza.api.models.MenuPizza
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.api.models.Pizza
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discounts")
    suspend fun getDiscounts(): List<Discount>

    @GET("menu/{size}/{index}/{isSort}")
    suspend fun getPagingMenu(
        @Path("size") size: Int,
        @Path("index") index: Int,
        @Path("isSort") isSort: Int = 0
    ): MenuPizza

    @GET("{id}")
    suspend fun getPizza(@Path("id") id: Int): Pizza


    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/v1/pizza/"

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