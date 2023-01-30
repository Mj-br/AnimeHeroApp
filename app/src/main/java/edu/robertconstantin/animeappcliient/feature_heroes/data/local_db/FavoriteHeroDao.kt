package edu.robertconstantin.animeappcliient.feature_heroes.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao //Data Access Object
interface FavoriteHeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //if the hero already exists in the db, replace it
    suspend fun insertFavoriteHero(favoriteHero: FavoriteHeroEntity) //Se mapea a la tabla de la base de datos

    @Update
    suspend fun updateFavoriteHero(favoriteHero: FavoriteHeroEntity): Int

    @Delete
    suspend fun deleteFavoriteHero(favoriteHero: FavoriteHeroEntity): Int //

    @Query("SELECT * FROM favorites_hero_db WHERE id = :id") //Query para obtener un solo heroe favorito
    suspend fun getFavoriteHeroById(id: Int): FavoriteHeroEntity //Se mapea a la tabla de la base de datos


    @Query("SELECT * FROM favorites_hero_db") //Query para obtener todos los heroes favoritos
    fun getAllFavoritesHeroes(): Flow<List<FavoriteHeroEntity>> //Se mapea a la tabla de la base de datos, flow es para que se actualice en tiempo real
}