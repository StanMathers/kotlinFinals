package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChangeDetailsActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference()

    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_details)

        init()
        listeners()
    }

    private fun init(){
        firstName = findViewById(R.id.newFirstname)
        lastName = findViewById(R.id.newLastname)
        saveButton = findViewById(R.id.saveButton)


    }

    private fun listeners(){
        saveButton.setOnClickListener {
            val first = firstName.text.toString().trim()
            val last = lastName.text.toString().trim()

            val userInfo = User(first, last)

            db.child(auth.currentUser?.uid!!).setValue(userInfo)
            Toast.makeText(this, "Changes applied", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}