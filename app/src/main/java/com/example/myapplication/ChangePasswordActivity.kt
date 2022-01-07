package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseDatabase.getInstance().getReference()

    private lateinit var currentPassword: EditText
    private lateinit var newPassword: EditText
    private lateinit var confirmNewPassword: EditText

    private lateinit var updatePassword: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        listeners()
    }

    private fun init() {


    }

    private fun listeners() {
        currentPassword = findViewById(R.id.currentPassword)
        newPassword = findViewById(R.id.newPassword)
        confirmNewPassword = findViewById(R.id.confirmNewPassword)

        val getCurrentPassword = currentPassword.text.toString()
        val getNewPassword = newPassword.text.toString()
        val getConfirmPassword = confirmNewPassword.text.toString()

        updatePassword = findViewById(R.id.updatePassword)

        updatePassword.setOnClickListener {
            if (getCurrentPassword.isEmpty() || getNewPassword.isEmpty() || getConfirmPassword.isEmpty()){
                Toast.makeText(this, "Nruh", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Yes", Toast.LENGTH_LONG).show()
        }

    }
}