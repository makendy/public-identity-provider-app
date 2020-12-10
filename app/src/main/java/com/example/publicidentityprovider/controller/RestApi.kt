package com.example.publicidentityprovider.controller

import com.example.publicidentityprovider.details.AppInfo
import com.example.publicidentityprovider.details.UserInfo
import com.example.publicidentityprovider.details.UserPostResponse
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("/users")
    fun addUser(@Body userData: UserInfo): Call<UserPostResponse>

    @Headers("Content-Type: application/json")
    @GET("/users/info")
    fun getUserInfo(@Header("Authorization") userToken : String): Call<UserInfo>

    @Headers("Content-Type: application/json")
    @GET("/auth/waiting")
    fun getAppInfo(@Header("Authorization") userToken : String): Call<AppInfo>
}