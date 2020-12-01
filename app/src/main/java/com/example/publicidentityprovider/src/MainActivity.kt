package com.example.publicidentityprovider.src

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.publicidentityprovider.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSign_up.setOnClickListener {
            val explicitIntent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(explicitIntent)
        }
    }
}