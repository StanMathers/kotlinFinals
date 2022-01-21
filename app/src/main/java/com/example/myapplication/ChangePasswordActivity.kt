package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    private val auth = FirebaseAuth.getInstance()

    lateinit var currentPassword: EditText
    lateinit var newPassword: EditText
    lateinit var confirmNewPassword: EditText
    lateinit var changePassword: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        init()
    }

    fun init(){
        // Find edit text
        currentPassword = findViewById(R.id.currentPassword)
        newPassword = findViewById(R.id.newPassword)
        confirmNewPassword = findViewById(R.id.confirmNewPassword)

        // Convert
        val currPass = currentPassword.text.toString()
        val newPass = newPassword.text.toString()
        val confNewPass = confirmNewPassword.text.toString()

        changePassword = findViewById(R.id.updatePassword)
        changePassword.setOnClickListener {
            Toast.makeText(this, "${currPass} ${newPass} ${confNewPass}", Toast.LENGTH_LONG).show()

        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

}