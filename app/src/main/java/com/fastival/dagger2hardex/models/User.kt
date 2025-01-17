package com.fastival.dagger2hardex.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")  var id: Int,
    @SerializedName("username")  var username: String,
    @SerializedName("email")  var email: String,
    @SerializedName("website")  var website: String
) {
    constructor(): this(0, "", "" , "")
}