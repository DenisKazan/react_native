package com.react_native.counter.logic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post(
        @SerializedName("userId")
        @Expose
        var userId: Int? = 0,
        @SerializedName("id")
        @Expose
        var id: Int? = 0,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("body")
        @Expose
        var body: String? = null
        ) {

    override fun toString(): String {
        return "UserId = $userId\nId = $id\nTitle = $title\n Body = $body"
    }
}
