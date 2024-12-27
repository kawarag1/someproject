package com.example.someproject

import android.content.Intent
import android.preference.PreferenceActivity.Header
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams

class UserViewModel : ViewModel() {
    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user
    //val name = MutableLiveData<String>()

    fun getUsers(login:String) {
        val url = "http://10.0.2.2:8000/users/select?login=$login"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d("UserViewModel", "Success: $result")
                val gson = Gson()
                try{
                    val User = gson.fromJson(result, UserModel::class.java)
                    _user.postValue(User)

                }
                catch (e: Exception){
                    Log.e("UserViewModel", "Error parsing JSON:${e.message}")
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.e("UserViewModel", "Error parsing JSON:${error.message}")
            }

            override fun onFinish() {
                super.onFinish()
                Log.d("UserViewModel", "Request finished")
            }
        })
    }
    override fun onCleared() {
        super.onCleared()
        Log.d("UserViewModel", "ViewModel cleared")
    }
}