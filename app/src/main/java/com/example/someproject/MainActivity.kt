package com.example.someproject

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceActivity.Header
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.someproject.ui.theme.SomeprojectTheme
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import java.lang.Error



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SomeprojectTheme {
                Greeting()
            }
        }
    }



    @Composable
    fun Greeting() {

        //val name by viewModel.name.observeAsState()
        var login = remember { mutableStateOf("")}
        var password = remember { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize().background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = login.value,
                    onValueChange = {login.value = it},
                    label = { Text("xyz@gmail.com")}
                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = {password.value = it},
                    label = { Text("*********")},
                    visualTransformation = PwdTransformation()
                )
                val viewModel: UserViewModel = viewModel()
                val user by viewModel.user.observeAsState()

                Button(
                    modifier = Modifier.height(60.dp).width(240.dp),
                    content = { Text("Войти")},
                    onClick = {

                        UserViewModel().getUsers(login.value)
                        if (user == null){
                            val intent = Intent(applicationContext, SecondScreen::class.java)
                            startActivity(intent)
                        }
                        else{

                            Toast.makeText(applicationContext, "User doesn't exist", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                )
            }
        }



    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SomeprojectTheme {
            Greeting()
        }
    }



}

