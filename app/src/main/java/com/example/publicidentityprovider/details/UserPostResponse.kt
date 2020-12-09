package com.example.publicidentityprovider.details

import com.google.gson.annotations.SerializedName

class UserPostResponse (@SerializedName("msisdn") val msisdn : String,
                        @SerializedName("email") val email : String,
                        @SerializedName("lastname") val lastName : String,
                        @SerializedName("firstname") val firstName : String,
                        @SerializedName("birthDate") val birthDate : String,
                        @SerializedName("userToken") val userToken : String)