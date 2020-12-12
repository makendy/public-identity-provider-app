package com.example.publicidentityprovider.src

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.controller.RestApiService
import com.example.publicidentityprovider.details.AppAuthorization
import kotlinx.android.synthetic.main.activity_app_authorization.*

class AppAuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_authorization)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val userCode = originIntent.getStringExtra("AUTH_USER_CODE")!!
        val userToken = originIntent.getStringExtra("AUTH_USER_TOKEN")!!
        val appName = originIntent.getStringExtra("APP_NAME")!!
        val appDate = originIntent.getStringExtra("APP_DATE")!!
        val appScopes = originIntent.getStringExtra("APP_SCOPES")!!

        id_app_name.text = "App Name: $appName"
        id_app_date.text = "Date: $appDate"
        id_app_auth.text = "Authorizations: $appScopes"

        val appAuthorization = AppAuthorization(userCode = userCode,
                                                userToken = userToken)

        id_button_auth.setOnClickListener {
            giveAuthorization(appAuthorization)
            Toast.makeText(this@AppAuthorizationActivity, "Authorization is given",
                    Toast.LENGTH_LONG).show()
            //Thread.sleep(5000)
            //goToNextActivity(userToken)

        }
    }

    private fun giveAuthorization(appAuthorization: AppAuthorization) {
        val apiService = RestApiService()

        apiService.postAuthorization(appAuthorization) {
            
        }
    }

    //---> Start next activity
    private fun goToNextActivity(userToken : String) {
        val explicitIntent = Intent(this@AppAuthorizationActivity,
                                    UserHomeActivity::class.java)
        explicitIntent.putExtra("USER_TOKEN", userToken)
        startActivity(explicitIntent)
    }
}