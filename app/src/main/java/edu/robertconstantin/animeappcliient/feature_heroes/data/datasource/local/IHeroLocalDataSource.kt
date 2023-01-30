package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local

import edu.robertconstantin.animeappcliient.feature_heroes.data.local_db.FavoriteHeroEntity
import kotlinx.coroutines.flow.Flow

interface IHeroLocalDataSource {
    suspend fun insertFavoriteHero(favoriteHero: FavoriteHeroEntity)

    // TODO: HADNEL INTEGER
    suspend fun deleteFavoriteHero(favoriteHero: FavoriteHeroEntity): Int
    fun getAllFavoritesHeroes(): Flow<List<FavoriteHeroEntity>>

}