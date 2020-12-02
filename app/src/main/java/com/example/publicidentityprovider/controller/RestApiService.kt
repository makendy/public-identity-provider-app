package com.example.publicidentityprovider.controller

import android.util.Log
import com.example.publicidentityprovider.details.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun addUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
                object : Callback<UserInfo> {
                    override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                        Log.d("POST USER FAILURE", "Impossible to create user")
                    }
                    override fun onResponse( call: Call<UserInfo>, response: Response<UserInfo>) {
                        if (response.code() == 200){
                            if(response.body() != null) {
                                val addedUser = response.body()
                                onResult(addedUser)
                            }
                        }
                    }
                }
        )
    }
}