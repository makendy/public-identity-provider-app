package com.example.publicidentityprovider.details

import com.google.gson.annotations.SerializedName

//---> Authorization model for post method
class AppAuthorization (@SerializedName("userCode") val userCode : String,
                        @SerializedName("userToken") val userToken : String)