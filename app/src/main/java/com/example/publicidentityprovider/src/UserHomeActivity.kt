package com.example.publicidentityprovider.src

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.controller.RestApiService
import com.example.publicidentityprovider.details.UserInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_home.*

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

    private fun displayUserInfo(userData : UserInfo, userToken: String) {
        id_last_name.text = "" + id_last_name.text + ": " + userData.lastName
        id_first_name.text = "" + id_first_name.text + ": "+ userData.firstName
        id_email.text = "" + id_email.text + ": "+ userData.email
        id_phone_number.text = "" + id_phone_number.text + ": "+ userData.msisdn
        id_birth_date.text =  "" + id_birth_date.text + ": "+ userData.birthDate

        id_button_waiting_auth.setOnClickListener {
            val explicitIntent = Intent(this@UserHomeActivity,
                                            WaitingListActivity::class.java)
            explicitIntent.putExtra("USER_TOKEN", userToken)
            startActivity(explicitIntent)
        }
    }
    //----> get user's info from DB
    private fun getUserInfoFromDatabase (userToken : String) {
        val apiService = RestApiService()

        apiService.getUserInfo(userToken) {
            if (it != null) {
                val itJsonString = Gson().toJson(it)
                Log.d("GET INFO SUCCEESSFUL", itJsonString.toString())
                var userData = Gson().fromJson(itJsonString, UserInfo::class.java)
                displayUserInfo(userData, userToken)
            } else {
                Log.d("GET USER INFO FAILURE", "An error has occurred !")
            }
        }
    }
}