package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
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


        changePassword = findViewById(R.id.updatePassword)
        changePassword.setOnClickListener {
            val currPass = currentPassword.text.toString()
            val newPass = newPassword.text.toString()
            val confNewPass = confirmNewPassword.text.toString()

            if((currPass.isEmpty() || newPass.isEmpty() || confNewPass.isEmpty())){
                Toast.makeText(this, "Please fill all the graphs", Toast.LENGTH_LONG).show()
            }
            else if ((currPass.isNotEmpty() && newPass.isNotEmpty() && confNewPass.isNotEmpty())){
                if(newPass != confNewPass){
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show()
                }
                else if(newPass.length < 8 && confNewPass.length < 8){
                    Toast.makeText(this, "Password lenght must be greater than 8", Toast.LENGTH_LONG).show()
                }
                // Else
                val firebaseUser = auth.currentUser
//                Toast.makeText(this, firebaseUser!!.email, Toast.LENGTH_LONG).show()

                val credential = EmailAuthProvider
                    .getCredential(firebaseUser?.email!!, currPass)

                firebaseUser.reauthenticate(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            firebaseUser.updatePassword(confNewPass)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful){
                                        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_LONG).show()
                                        startActivity(Intent(this, MainActivity::class.java))
                                        finish()

                                    }
                                }

                        }
                        else{
                            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                        }
                    }
            }

        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

}