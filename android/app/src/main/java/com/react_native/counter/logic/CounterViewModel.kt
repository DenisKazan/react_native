package com.react_native.counter.logic

import android.util.Log
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CounterViewModel: ReactContextBaseJavaModule() {

    private val service = NetworkService()

    @ReactMethod
    fun onCountChangeClick(count: Int) {
        service.getJSONApi().getPostWithID(count)
                .enqueue(object: Callback<Post> {
                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("CounterViewModel", t.localizedMessage)
                    }

                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        val post = response.body()
                        post?.let {
                            Log.d("CounterViewModel", post.toString())
                        }
                    }

                })
    }

    override fun getName(): String {
        return "CounterViewModel"
    }
}
