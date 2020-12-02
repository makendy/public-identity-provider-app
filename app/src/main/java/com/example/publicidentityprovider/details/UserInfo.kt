package com.example.publicidentityprovider.details

import com.google.gson.annotations.SerializedName

//---> User model for POST method
class UserInfo (@SerializedName("user_phoneNumber") val msisdn : String,
                @SerializedName("user_email") val email : String,
                @SerializedName("user_lastName") val lastName : String,
                @SerializedName("user_firstName") val firstName : String,
                @SerializedName("user_birthDate") val birthDate : String)