package com.example.publicidentityprovider.details

import com.google.gson.annotations.SerializedName

//---> App model for POST method
data class AppInfo (@SerializedName("appName") val appName : String,
                     @SerializedName("date") val date : String,
                     @SerializedName("scopes") val scopes : List<String>,
                     @SerializedName("userCode") val userCode : String)