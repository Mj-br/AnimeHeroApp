package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote

import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import retrofit2.Response
import retrofit2.http.GET

interface HeroApi {
    @GET("api/hero/all") //this is the endpoint, el get es del retrofit
    suspend fun getAllHeroes(): Response<List<HeroDto>> //Cuando se hace un getAllHeroes va al Get api/hero/all y revisa

    //Este es el localHost de Android Studio y mi puerto
    companion object {
        const val HERO_BASE_URL = "http://10.0.2.2:8001/"
    }
}