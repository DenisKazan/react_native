package com.react_native.counter.logic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderApi {

    @GET("/posts/{id}")
    fun getPostWithID(@Path("id") id: Int): Call<Post>
}
