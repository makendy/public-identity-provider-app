package com.example.publicidentityprovider.controller

import android.util.Log
import com.example.publicidentityprovider.details.UserInfo
import com.example.publicidentityprovider.details.UserPostResponse
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

    fun getUserInfo(userToken: String?, onResult: (UserInfo?) -> Unit){
        Log.d("USER TOKEN REQUEST", userToken)
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getUserInfo("Bearer $userToken").enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.d("GET USER INFOS FAILURE", "Impossible to get user's infos")
                    Log.d("CAUSE", t.message)
                }
                override fun onResponse( call: Call<UserInfo>, response: Response<UserInfo>) {
                    Log.d("GET USER INFO SUCCEEDED", "Maybe user's infos are returned !")
                    if (response.code() == 200){
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