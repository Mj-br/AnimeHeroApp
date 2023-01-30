package edu.robertconstantin.animeappcliient.feature_heroes.domain.model


//Creamos la clase HeroDM para que sea la que se use en el dominio para implementar esto en el IHeroRepository

data class HeroDM(
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
    val day: String
)
