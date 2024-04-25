package com.example.mybeercellarapp

data class Beer(
    val id: Int,
    val user: String,
    val name: String,
    val brewery: String,
    val style: String,
    val abv: Double,
    val ibu: Int?,
    val pictureUrl: String?,
    val howMany: Int
)