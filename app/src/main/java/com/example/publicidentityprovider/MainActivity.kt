package com.example.publicidentityprovider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSign_up.setOnClickListener {
            val explicitIntent = Intent(this@MainActivity, SignUpActivity::class.java)
            explicitIntent.putExtra("ID", "TODO")
            startActivity(explicitIntent)
        }
    }
}