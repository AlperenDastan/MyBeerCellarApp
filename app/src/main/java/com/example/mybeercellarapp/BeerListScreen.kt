package com.example.mybeercellarapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybeercellarapp.databinding.ActivityBeerListScreenBinding

class BeerListScreen : AppCompatActivity() {
    private lateinit var binding: ActivityBeerListScreenBinding
    private lateinit var viewModel: BeerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beerApiService = RetrofitInstance.api // Obtain the API service from Retrofit instance
        val repository =
            BeerRepository(beerApiService) // Initialize repository with the API service
        val viewModelFactory = BeerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BeerViewModel::class.java)

        val beerAdapter = BeerAdapter(emptyList())
        binding.beerRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.beerRecyclerView.adapter = beerAdapter

        viewModel.beers.observe(this, Observer { beers ->
            beerAdapter.updateBeers(beers)
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        })

        viewModel.getAllBeers()


        binding.buttonBackToMain.setOnClickListener {
            finish()  // Finish this activity to return to the previous one
        }
    }
}