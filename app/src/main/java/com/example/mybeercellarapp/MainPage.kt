package com.example.mybeercellarapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mybeercellarapp.databinding.ActivityMainPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.mybeercellarapp.ui.theme.MyBeerCellarAppTheme


class MainPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding
    // Assume you have a view model for BeerViewModel
    private val viewModel: BeerViewModel by viewModels() // You'll need to set up the necessary factory for this ViewModel

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
            startActivity(Intent(this, BeerListScreen::class.java))
        }

        // Set up the View My Beers button
        binding.viewUserBeersButton.setOnClickListener {
            startActivity(Intent(this, SearchBeersActivity::class.java))
        }

        //binding.viewUserBeersButton.setOnClickListener {
          //  currentUser?.email?.let { email ->
                // You'll need to implement getUserBeers in your ViewModel
            //    viewModel.getUserBeers(email, "orderByParameterHere") // Adjust parameters as needed
                 //Observe the viewModel's beers LiveData to update the UI
            //}
        //}

        // Set up the Add Beer button
        binding.addBeerButton.setOnClickListener {
            // Navigate to an activity where you can add a new beer
            // You'll need to create this activity
            val intent = Intent(this, AddBeerActivity::class.java)
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

    // Remember to create your viewUserBeersButton and addBeerButton in your activity_main_page.xml
}
