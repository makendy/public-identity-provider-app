package com.example.publicidentityprovider.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.publicidentityprovider.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        buttonSendSignUp.setOnClickListener {
            if(verificationOfFields(textPhoneNumber.text.toString(), textEmailAddress.text.toString(),
                            textLastName.text.toString(), textFirstName.text.toString(),
                            textBirthDate.text.toString())) {
                Log.d("SIGN UP OK", "Every fields is correct")
            }
            Log.d("SIGN UP", "phone number : " + textPhoneNumber.text +
                                        " // email : " + textEmailAddress.text +
                                        " // last name : " + textLastName.text +
                                        " // first name : " + textFirstName.text +
                                        " // birth date : " + textBirthDate.text)
        }
    }

    private fun regexNumber (phoneNumber: String) : Boolean {
        return Regex("^[0-9]*$").matches(phoneNumber)
    }

    private fun  verificationOfFields(
            phoneNumber : String,
            email : String,
            lastName  : String,
            firstName : String,
            birthDate : String
    ) : Boolean {

        if(!regexNumber(phoneNumber)) {
            Toast.makeText(this@SignUpActivity, "Phone number can contains only " +
                    "digits", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}