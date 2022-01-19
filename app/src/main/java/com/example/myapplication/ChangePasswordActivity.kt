package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ChangePasswordActivity : AppCompatActivity() {
    lateinit var changePassword: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        init()
    }

    fun init(){
        changePassword = findViewById(R.id.updatePassword)
        changePassword.setOnClickListener {
            Toast.makeText(this, "test", Toast.LENGTH_LONG).show()
        }
    }

}