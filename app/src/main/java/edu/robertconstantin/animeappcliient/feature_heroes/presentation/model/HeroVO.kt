package edu.robertconstantin.animeappcliient.feature_heroes.presentation.model

data class HeroVO(
    val id:Int,
    val name: String,
    //this will be a path of that image in the backend server
    val image: String,
    val about: String,
    //max value 5 min value 0
    val rating: Double,
    //random value from 0 to 100
    val power: Int,
    //month of born
    val month: String,
    val day: String,
    val isAddedToFavorites: Boolean = false
)
