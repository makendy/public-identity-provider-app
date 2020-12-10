package com.example.publicidentityprovider.details

import com.google.gson.annotations.SerializedName

//---> User model for POST method
data class AppInfo (@SerializedName("appName") val appName : String,
                     @SerializedName("date") val date : String,
                     @SerializedName("scopes") val scope : List<String>,
                     @SerializedName("userCode") val userCode : String)