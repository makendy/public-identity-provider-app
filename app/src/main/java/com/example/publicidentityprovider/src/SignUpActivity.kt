package com.example.publicidentityprovider.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.publicidentityprovider.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val globalFrenchNumberLengthMin = 10
    private val globalFrenchNumberLengthMax = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonSendSignUp.setOnClickListener {
            if(verificationOfFields(textPhoneNumber.text.toString(), textEmailAddress.text.toString(),
                            textLastName.text.toString(), textFirstName.text.toString(),
                            textBirthDate.text.toString())) {
                Log.d("SIGN UP OK", "Every field is correct")
            }
            Log.d("SIGN UP", "phone number : " + textPhoneNumber.text +
                                        " // email : " + textEmailAddress.text +
                                        " // last name : " + textLastName.text +
                                        " // first name : " + textFirstName.text +
                                        " // birth date : " + textBirthDate.text)
        }
    }
// ---> Next 4 functions are about regex to test if fields are correctly filled
    //---> A phone number can only have digits
    private fun regexNumber (phoneNumber : String) : Boolean {
        return Regex("^[0-9]*$").matches(phoneNumber)
    }

    //---> An email address can only have the format -----@-------.----
    private fun regexEmail (email : String) : Boolean {
        return Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(email)
    }

    //---> A name can only have alphabetical characters
    private fun regexName (name : String) : Boolean {
        return Regex("^[A-Za-z]*$").matches(name)
    }

    //---> A date can only have the formats DD/MM/YYYY or DD-MM-YYYY
    private fun regexDate (birthDate : String) : Boolean {
        return Regex("^(0[1-9]|[12][0-9]|3[01])[- /](0[1-9]|1[012])[- /](19|20)\\d\\d\$")
                .matches(birthDate)
    }

    private fun verificationNumber (phoneNumber: String) : Boolean {
        if(!regexNumber(phoneNumber)) {
            Toast.makeText(this@SignUpActivity, "Phone number can contains only " +
                    "digits", Toast.LENGTH_SHORT).show()
            return false
        }

        //---> For France : 10 or 11 digits for a number
        if (phoneNumber.length != globalFrenchNumberLengthMax
                || phoneNumber.length != globalFrenchNumberLengthMin) {
            Toast.makeText(this@SignUpActivity, "Incorrect phone number",
                    Toast.LENGTH_SHORT).show()
            return false
        }

        //---> If the number is like 0- -- -- -- --
        if (phoneNumber[0] == '0' && phoneNumber.length != globalFrenchNumberLengthMin)
            return false
        //---> if the number is like 33 - -- -- -- --
        if (phoneNumber.length == globalFrenchNumberLengthMax) {
            if (phoneNumber[0] != '3' || phoneNumber[1] != '3')
                return false
        }

        return true
    }

    private fun verificationEmail (email: String) : Boolean {
        if (!regexEmail(email)) {
            Toast.makeText(this@SignUpActivity, "Incorrect email address",
                    Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun verificationName (name: String, test : Boolean = true) : Boolean {
        if (!regexName(name)) {
            val text = "name can only contains alphabetical characters"
            if (test)
                Toast.makeText(this@SignUpActivity, "Last $text",
                        Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this@SignUpActivity, "First $text",
                        Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun verificationBirthDate (birthDate: String) : Boolean {
        if (!regexDate(birthDate)) {
            Toast.makeText(this@SignUpActivity, "Incorrect birth date. " +
                    "Try with the format DD/MM/YYYY",
                    Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun  verificationOfFields(
            phoneNumber : String,
            email : String,
            lastName  : String,
            firstName : String,
            birthDate : String
    ) : Boolean {

        if (!verificationNumber(phoneNumber))
            return false
        if (!verificationEmail(email))
            return false
        if (!verificationName(lastName, true))
            return false
        if (!verificationName(firstName, true))
            return false
        if (!verificationBirthDate(birthDate))
            return false
        return true
    }
}