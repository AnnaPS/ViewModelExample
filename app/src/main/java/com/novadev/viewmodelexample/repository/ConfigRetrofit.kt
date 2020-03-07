package com.novadev.viewmodelexample.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigRetrofit {

    var BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): ApiService = getRetrofitInstance().create<ApiService>(ApiService::class.java)


}