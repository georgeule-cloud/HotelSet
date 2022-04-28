package com.hotelset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hotelset.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth

class MainActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        mAuth = Firebase.auth

        binding.btFormRegister.setOnClickListener{ val intent = Intent(this, Registro::class.java)
            startActivity(intent)}
        binding.btFormLogin.setOnClickListener{ login()}

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.id_web))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        binding.btGoogle.setOnClickListener { googleSignIn() }

    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle (idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    update(user)
                } else {
                    update(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(cuenta.idToken!!)
            } catch (e: ApiException) {

            }
        }
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
/*
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

    }*/

    public override fun onStart(){
        super.onStart()
        val user = mAuth.currentUser
        update(user)
    }
}