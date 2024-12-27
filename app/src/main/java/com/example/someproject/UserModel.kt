package com.example.someproject

import com.google.gson.annotations.SerializedName

data class UserModel(

    @SerializedName("name") val name:String,
    @SerializedName("id") val id:Int,
    @SerializedName("role_id") val role_id:Int
)