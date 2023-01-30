package edu.robertconstantin.animeappcliient.feature_heroes.data.repository

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local.IHeroLocalDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.IHeroRemoteDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.mapper.toFavHeroEntity
import edu.robertconstantin.animeappcliient.feature_heroes.data.mapper.toHeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor( // El @Inject es para inyectar el datasource o lo que sea (en este caso los diferentes datasources que tengamos)
    private val remoteHeroDataSource: IHeroRemoteDataSource,
    private val localHeroDataSource: IHeroLocalDataSource
    ): IHeroRepository {

    override suspend fun getAllHeroes(): Resource<List<HeroDM>> {
        return remoteHeroDataSource.getAllHeroes().mapResourceData( // Hace el llamado a la api y revisa si hay error o no
            success = { heroes ->
                Resource.Success(heroes?.map { it.toHeroDM() }) // Si no hay error, mapea los datos de la api a los datos del dominio
            },
            error = { msg ->
                Resource.Error(msg ?: UiText.unknownError()) // Si hay error, manda un mensaje de error
            }
        )
    }

    override suspend fun insertFavoriteHero(heroDM: HeroDM) {
        localHeroDataSource.insertFavoriteHero(heroDM.toFavHeroEntity())
    }

    override suspend fun deleteFavoriteHero(heroDM: HeroDM): Int {
        return localHeroDataSource.deleteFavoriteHero(heroDM.toFavHeroEntity())
    }

    override fun getAllFavoritesHeroes(): Flow<List<HeroDM>> {
        return localHeroDataSource.getAllFavoritesHeroes().map { heroes ->
            heroes.map { hero -> hero.toHeroDM() }
        }
    }
}