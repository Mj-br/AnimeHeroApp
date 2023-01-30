package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.callApi
import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import javax.inject.Inject


//Gestiona peticiones en cualquier tipo de servidor, tiene el endpoint
class HeroRemoteDataSourceImpl @Inject constructor(private val heroApi: HeroApi) : IHeroRemoteDataSource { //Inyeccion de dependencias

    override suspend fun getAllHeroes(): Resource<List<HeroDto>> { //Cuando se hace un getAllHeroes va al Get api/hero/all y revisa
        return callApi { heroApi.getAllHeroes() } // NOTA: Revisa como esta la conexion entre lo que piden y lo que se le manda, así
                                                  // así puedes entender que se llama a qué.
                                                  //
    }
}