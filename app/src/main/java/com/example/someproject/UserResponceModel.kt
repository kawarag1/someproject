package com.example.someproject

import com.google.gson.annotations.SerializedName

data class UserResponceModel (
    @SerializedName("users") val users: List<UserModel>
)