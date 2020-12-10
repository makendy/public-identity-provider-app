package com.example.publicidentityprovider.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.controller.RestApiService
import com.google.gson.Gson

class AllowedListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allowed_list)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val userToken = originIntent.getStringExtra("USER_TOKEN")!!
        Log.d("USER TOKEN", userToken)
        getAllowedAppList(userToken)
    }

    private fun getAllowedAppList(userToken : String) {
        val apiService = RestApiService()

        apiService.getListAllowedAppInfo(userToken) {
            if (it != null) {
                val itJsonString = Gson().toJson(it)
                Log.d("GET APP ALLWD SUCCEED", itJsonString.toString())
                //displayAppListInfo(it)
            } else {
                Log.d("GET APP ALLWD FAILURE", "An error has occurred !")
            }
        }
    }
}