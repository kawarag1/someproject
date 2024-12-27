package com.example.someproject

import android.preference.PreferenceActivity.Header
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = _users

    fun getUsers(login:String) {
        val url = "http://10.0.2.2:8000/users/select?login=$login"
        //val url = "https://api.speedsolver.ru/v1/access/register"
        val client = AsyncHttpClient()
        val params = RequestParams()
        params.put("param1", login)
        //params.put("param2", password)
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                val gson = Gson()
                try{
                    val User = gson.fromJson(result, UserResponceModel::class.java)
                    _users.postValue(User.users)
                }
                catch (e: Exception){
                    Log.e("FilmViewModel", "Error parsing JSON:${e.message}")
                    _users.postValue(emptyList())
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.e("UserViewModel", "Error parsing JSON:${error.message}")
                _users.postValue(emptyList())
            }
        })
    }
}