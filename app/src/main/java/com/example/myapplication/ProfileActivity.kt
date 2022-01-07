package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text

class ProfileActivity : AppCompatActivity() {



    lateinit var currentFirstname: TextView
    lateinit var currentSurname: TextView
    lateinit var changeDetailsBtn: Button
    lateinit var changePasswordBtn: Button

    lateinit var newFirstname: EditText
    lateinit var newLastname: EditText

    lateinit var saveButton: Button

    lateinit var currentMail: TextView

    lateinit var firebaseAuth: FirebaseAuth
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()
        listeners()

        db.child(auth.currentUser?.uid!!).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(User::class.java) ?: return

                currentFirstname.text = userInfo.firstname
                currentSurname.text = userInfo.lastname
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
    fun init(){



        currentMail = findViewById(R.id.userEmail)


        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            val email = firebaseUser.email

            currentMail.text = email
        }


        currentFirstname = findViewById(R.id.firstnameChange)
        currentSurname = findViewById(R.id.surnameChange)

//        newFirstname = findViewById(R.id.newFirstname)
//        newLastname = findViewById(R.id.newSurname)
//
//        saveButton = findViewById(R.id.saveChanges)
    }

    fun listeners(){
        changeDetailsBtn = findViewById(R.id.changeDetails)
        changePasswordBtn = findViewById(R.id.changePassword)

        changeDetailsBtn.setOnClickListener {
            startActivity(Intent(this, ChangeDetailsActivity::class.java))
            finish()
        }

        changePasswordBtn.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
            finish()
        }







//        saveButton.setOnClickListener {
//            val first = newFirstname.text.toString()
//            val last = newLastname.text.toString()
//
//            val userInfo = User(first, last)
//
//            db.child(auth.currentUser?.uid!!).setValue(userInfo)
//        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }

}