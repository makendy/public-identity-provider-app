package com.example.publicidentityprovider.controller

import com.example.publicidentityprovider.details.UserInfo
import com.example.publicidentityprovider.details.UserPostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("/users")
    fun addUser(@Body userData: UserInfo): Call<UserPostResponse>

    @GET("/users/info")
    fun getUserInfo()

}