package com.example.someproject

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("role_id") val role_id:Int
)