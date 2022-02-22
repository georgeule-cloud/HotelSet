package com.hotelset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hotelset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        mAuth = Firebase.auth

        binding.btFormRegister.setOnClickListener{ register()}
        binding.btFormLogin.setOnClickListener{ login()}


    }
    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()

        //Se usa el metodo
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    update(user)
                }
                else{
                    Toast.makeText(baseContext,getString(R.string.msg_LoginErrorMessage), Toast.LENGTH_LONG).show()
                    update(null)

                }

            }

    }

    private fun update(user: FirebaseUser?) {
        if (user!= null){
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }

    private fun register() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()

        //Se usa el metodo
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    update(user)
                }
                else{
                    Toast.makeText(baseContext,getString(R.string.msg_RegisterErrorMessage), Toast.LENGTH_SHORT).show()
                    update(null)

                }

            }

    }

    public override fun onStart(){
        super.onStart()
        val user = mAuth.currentUser
        update(user)
    }
}