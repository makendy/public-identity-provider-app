package com.example.publicidentityprovider.src

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.controller.RestApiService
import com.example.publicidentityprovider.details.UserInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up.*

class UserHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val userToken = originIntent.getStringExtra("USER_TOKEN")!!
        Log.d("USER TOKEN", userToken)
        getUserInfoFromDatabase(userToken)
    }

    //---> Prevent activity from going back to previous activities and quit app
    override fun onBackPressed() {
        finishAffinity()
    }

    private fun getUserInfoFromDatabase (userToken : String) {
        val apiService = RestApiService()

        apiService.getUserInfo(userToken) {
            if (it != null) {
                val itJsonString = Gson().toJson(it)
                Log.d("GET INFO SUCCEESSFUL", itJsonString.toString())
                var userData = Gson().fromJson(itJsonString, UserInfo::class.java)
            } else {
                Log.d("GET USER INFO FAILURE", "An error has occurred !")
            }
        }
    }
}