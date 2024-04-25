package com.example.mybeercellarapp

class BeerRepository(private val beerApiService: BeerApiService) {
    suspend fun getAllBeers(): List<Beer> = beerApiService.getAllBeers()
    suspend fun getUserBeers(user: String, orderBy: String): List<Beer> = beerApiService.getUserBeers(user, orderBy)
    suspend fun addBeer(beer: Beer): Beer = beerApiService.addBeer(beer)
}