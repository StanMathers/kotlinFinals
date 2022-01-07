package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var emailPrompt: EditText
    lateinit var passwordPrompt: EditText
    lateinit var regPasswordConfirm: EditText
    lateinit var passwordNotMatch: TextView

    lateinit var firstname: EditText
    lateinit var lastname: EditText

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference()

    lateinit var regBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        listeners()
    }

    private fun init(){
        emailPrompt = findViewById(R.id.regEmail)
        passwordPrompt = findViewById(R.id.regPassword)
        regPasswordConfirm = findViewById(R.id.regPasswordConfirm)

        regBtn = findViewById(R.id.registerUserButton)
    }
    private fun listeners(){
        regBtn.setOnClickListener {
            var email = emailPrompt.text.toString()
            var password = passwordPrompt.text.toString()
            var confirmPassword = regPasswordConfirm.text.toString()

            passwordNotMatch = findViewById(R.id.passwordNotMatch)


            if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if (password != confirmPassword || password.isEmpty() && confirmPassword.isNotEmpty() || confirmPassword.isNotEmpty() && password.isEmpty()){
                passwordNotMatch.text = "Password doesn't match"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { task2 ->
                                if (task2.isSuccessful){
                                    userDetails()
                                    Toast.makeText(this, "Email Verification sent", Toast.LENGTH_LONG).show()
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                } else{
                                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                                }
                            }


                    }else{
                        Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }

                }

        }
    }

    // Parse into RealTime DataBase

    fun userDetails(){
        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)

        val first = firstname.text.toString().trim()
        val last = lastname.text.toString().trim()

        val userInfo = User(first, last)

        db.child(auth.currentUser?.uid!!).setValue(userInfo)



    }

}