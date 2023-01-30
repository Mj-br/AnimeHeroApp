package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto


//Principal interface for the remote data source
interface IHeroRemoteDataSource {
    suspend fun getAllHeroes(): Resource<List<HeroDto>>
}