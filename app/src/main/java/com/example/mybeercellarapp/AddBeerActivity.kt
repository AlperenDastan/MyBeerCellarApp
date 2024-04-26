package com.example.mybeercellarapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mybeercellarapp.databinding.ActivityAddBeerBinding
import com.google.firebase.auth.FirebaseAuth

class AddBeerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBeerBinding
    private val viewModel: BeerViewModel by viewModels {
        // Assuming your application class is named MyBeerCellarApp
        BeerViewModelFactory((application as BeerApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBeerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddBeer.setOnClickListener {
            // gather information from EditTexts and create a Beer object
            val brewery = binding.editTextBrewery.text.toString()
            val name = binding.editTextName.text.toString()
            val style = binding.editTextStyle.text.toString()
            val abv = binding.editTextAbv.text.toString().toDoubleOrNull()
            val volume = binding.editTextVolume.text.toString().toDoubleOrNull()
            val howMany = binding.editTextHowMany.text.toString().toIntOrNull() ?: 0

            if (brewery.isBlank() || name.isBlank() || style.isBlank() || abv == null) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val newBeer = Beer(
                    0,
                    FirebaseAuth.getInstance().currentUser?.email ?: "",
                    brewery,
                    name,
                    style,
                    abv,
                    volume,
                    null,
                    howMany
                )
                viewModel.addBeer(newBeer)
            }
        }

        viewModel.beers.observe(this, { beers ->
            Toast.makeText(this, "Beer added successfully!", Toast.LENGTH_SHORT).show()
            // After successfully adding a beer, finish the current activity to return to the previous one
            finish()
        })

        viewModel.error.observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        binding.buttonBackToMain.setOnClickListener {
            finish()  // Simply finish the activity to return to the previous one in the stack
        }
    }
}