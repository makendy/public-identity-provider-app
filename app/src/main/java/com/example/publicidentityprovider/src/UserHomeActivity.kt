package com.example.publicidentityprovider.src

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.details.UserPostResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up.*

class UserHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val jsonStringUserdata = originIntent.getStringExtra("USER_DATA")!!
        Log.d("HOME USER ACTVT DATA : ", jsonStringUserdata)

        var userData = Gson().fromJson(jsonStringUserdata, UserPostResponse::class.java)
        Log.d("USER TOKEN", userData.userToken)
    }

    //---> Prevent activity from going back to previous activities and quit app
    override fun onBackPressed() {
        finishAffinity()
    }
}