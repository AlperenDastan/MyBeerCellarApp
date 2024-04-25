package com.example.mybeercellarapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mybeercellarapp.databinding.ActivityMainPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.mybeercellarapp.ui.theme.MyBeerCellarAppTheme


class MainPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is logged in
        if (FirebaseAuth.getInstance().currentUser == null) {
            // User is not logged in, redirect to SignInActivity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return // Prevent further execution
        }

        // Set up the log out button
        binding.logoutButton.setOnClickListener {
            signOut()
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut() // Sign out from Firebase
        startActivity(Intent(this, SignInActivity::class.java)) // Go back to sign-in screen
        finish()
    }
}