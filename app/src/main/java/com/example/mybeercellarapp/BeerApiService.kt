package com.example.mybeercellarapp

import com.example.mybeercellarapp.Beer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApiService {
    @GET("/api/Beers")
    suspend fun getAllBeers(): List<Beer>
    @GET("/api/Beers/{user}")
    suspend fun getUserBeers(@Path("user") user: String, @Query("orderBy") orderBy: String): List<Beer>

    @POST("/api/Beers")
    suspend fun addBeer(@Body newBeer: Beer): Beer
}
