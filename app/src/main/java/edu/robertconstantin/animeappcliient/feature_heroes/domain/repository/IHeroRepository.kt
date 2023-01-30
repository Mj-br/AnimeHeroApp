package edu.robertconstantin.animeappcliient.feature_heroes.domain.repository

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import kotlinx.coroutines.flow.Flow

interface IHeroRepository {
    suspend fun getAllHeroes(): Resource<List<HeroDM>>
    //Suspend porque es una operacion asincrona (asincrono permite que las demas funciones se ejecuten mientras se espera la respuesta de esta)
    //Resource porque es un objeto que puede tener 3 estados: success, error, loading
    //List<HeroDM> porque es una lista de objetos de tipo HeroDM (Que es de este dominio y así no se mezclan los datos de dominio con los de la capa de datos)


    suspend fun insertFavoriteHero(heroDM: HeroDM)
    suspend fun deleteFavoriteHero(heroDM: HeroDM): Int

    fun getAllFavoritesHeroes(): Flow<List<HeroDM>>
}