package com.example.mybeercellarapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mybeercellarapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (email.isBlank() || password.isBlank()) {
                    showMessage("Fields cannot be empty")
                } else {
                    loginUser(email, password)
                }
            }

            registerButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (email.isBlank() || password.isBlank()) {
                    showMessage("Fields cannot be empty")
                } else {
                    registerUser(email, password)
                }
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showMessage("Login successful")
                navigateToMain()
            } else {
                showMessage("Login failed: ${task.exception?.message}")
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showMessage("Registration successful")
                navigateToMain()
            } else {
                showMessage("Registration failed: ${task.exception?.message}")
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showMessage(message: String) {
        // Update a TextView or use a Snackbar to show messages
        binding.messageTextView.text = message
    }
}