package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var emailField: EditText
    lateinit var passwordField: EditText

    private val auth = FirebaseAuth.getInstance()

    lateinit var loginBtn: Button
    lateinit var regBtn: Button

    lateinit var forgotPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        listeners()


    }
    private fun init(){
        emailField = findViewById(R.id.loginEmail)
        passwordField = findViewById(R.id.loginPassword)

        forgotPassword = findViewById(R.id.forgotPassword)

        loginBtn = findViewById(R.id.loginButton)
        regBtn = findViewById(R.id.registerButton)
    }

    private fun listeners(){
        // HERE ADD LOGIN CLICK LISTENER

        loginBtn.setOnClickListener {
            var email = emailField.text.toString()
            var password = passwordField.text.toString()


            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim(), password.trim())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        if(auth.currentUser?.isEmailVerified == true){
                            val intent = Intent(this, HomePage::class.java)
                            intent.putExtra("user_email", email)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this, "Please, verify your email", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }
        }

        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finish()
        }
        //  Register
        regBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    fun profile(){
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure?")
        builder.setMessage("Do you want to exit?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }
}