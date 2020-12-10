package com.example.publicidentityprovider.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.publicidentityprovider.R

class WaitingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_list)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val userToken = originIntent.getStringExtra("USER_TOKEN")!!
        Log.d("USER TOKEN", userToken)
    }
}