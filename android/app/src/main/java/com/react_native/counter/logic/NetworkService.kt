package com.react_native.counter.logic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getJSONApi(): JSONPlaceHolderApi = retrofit.create(JSONPlaceHolderApi::class.java)

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}
