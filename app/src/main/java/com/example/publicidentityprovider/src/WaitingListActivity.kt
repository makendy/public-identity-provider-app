package com.example.publicidentityprovider.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.controller.RestApiService
import com.example.publicidentityprovider.details.AppInfo
import com.google.gson.Gson

class WaitingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_list)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val userToken = originIntent.getStringExtra("USER_TOKEN")!!
        Log.d("USER TOKEN", userToken)
        getWaitingAppList(userToken)
    }

    private fun displayAppListInfo(listOfApp : List<AppInfo>) {

    }

    private fun getWaitingAppList(userToken : String) {
        val apiService = RestApiService()

        apiService.getListAppInfo(userToken) {
            if (it != null) {
                val itJsonString = Gson().toJson(it)
                Log.d("GET APP INFO SUCCEED", itJsonString.toString())
                displayAppListInfo(it)
            } else {
                Log.d("GET APP INFO FAILURE", "An error has occurred !")
            }
        }
    }
}