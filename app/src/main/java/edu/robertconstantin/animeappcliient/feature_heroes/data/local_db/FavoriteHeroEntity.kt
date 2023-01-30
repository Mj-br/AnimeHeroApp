package edu.robertconstantin.animeappcliient.feature_heroes.data.local_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.robertconstantin.animeappcliient.feature_heroes.data.util.DataConst.FAVORITES_HERO_DB

@Entity(tableName = FAVORITES_HERO_DB)
data class FavoriteHeroEntity(
    @PrimaryKey(autoGenerate = true)
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
)