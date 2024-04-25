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
        enableEdgeToEdge()
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            // User is not logged in, redirect to SignInActivity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        // Set up the View Beers button
        binding.viewBeersButton.setOnClickListener {
            val intent = Intent(this, BeerListScreen::class.java).apply {
                putExtra("user_id", currentUser.uid)  // Pass user ID to BeerListScreen
            }
            startActivity(intent)
        }

        // Set up the Log Out button
        binding.logoutButton.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}