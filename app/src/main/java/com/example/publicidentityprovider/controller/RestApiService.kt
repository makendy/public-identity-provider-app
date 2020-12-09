package com.example.publicidentityprovider.controller

import android.util.Log
import com.example.publicidentityprovider.details.UserInfo
import com.example.publicidentityprovider.details.UserPostResponse
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun addUser(userData: UserInfo, onResult: (UserPostResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
                object : Callback<UserPostResponse> {
                    override fun onFailure(call: Call<UserPostResponse>, t: Throwable) {
                        Log.d("POST USER FAILURE", "Impossible to create user")
                        Log.d("CAUSE", t.message)
                    }
                    override fun onResponse( call: Call<UserPostResponse>, response: Response<UserPostResponse>) {
                        Log.d("POST USER SUCCEEDED", "Maybe user is created !")
                        if (response.code() == 201){
                            if(response.body() != null) {
                                val addedUser = response.body()
                                onResult(addedUser)
                            }
                        }
                        else
                            Log.d("RETURN CODE : ", response.code().toString()
                                    + " // body : "+  response.errorBody()?.string())
                    }
                }
        )
    }
}