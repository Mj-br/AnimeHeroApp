package edu.robertconstantin.animeappcliient.feature_heroes.data.di

//Este package contiene unos casos de usos que deberían  estar en otro package di (dependencies injection) en la capa de dominio  y no en  esta (la de datos)

import android.app.Application
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local.HeroLocalDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local.IHeroLocalDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroApi
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroApi.Companion.HERO_BASE_URL
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroRemoteDataSourceImpl
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.IHeroRemoteDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.local_db.FavoriteHeroDatabase
import edu.robertconstantin.animeappcliient.feature_heroes.data.repository.HeroRepositoryImpl
import edu.robertconstantin.animeappcliient.feature_heroes.data.util.DataConst.FAVORITES_HERO_DB
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Se instala en el componente singleton
object HeroModule { //Se crea el modulo

    //Provee el retrofit
    @Provides
    @Singleton //Se crea una sola instancia
    fun providesRetrofit(okHttpClient: OkHttpClient): HeroApi {
        return Retrofit.Builder() //Construye el objeto Retrofit
            .baseUrl(HERO_BASE_URL) // Base URL
            .addConverterFactory(GsonConverterFactory.create()) //Convertir JSON a objetos
            .client(okHttpClient) //Cliente HTTP
            .build() //Crear instancia
            .create(HeroApi::class.java) //Crear instancia de la interfaz
    }

    @Provides
    @Singleton
    fun providesHeroDatabase(app: Application): FavoriteHeroDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteHeroDatabase::class.java,
            FAVORITES_HERO_DB
        ).build()
    }


    //Provee el HeroRemoteDataSource
    @Provides //Busca en el appModule toodo lo que se instancia
    @Singleton
    fun provideHeroRemoteDataSource(api: HeroApi): IHeroRemoteDataSource { //Es de tipo IHeroRemoteDataSource porque es la interfaz
        return HeroRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideHeroLocalDataSource(local: FavoriteHeroDatabase): IHeroLocalDataSource {
        return HeroLocalDataSource(local.dao)
    }

    @Provides
    @Singleton
    fun provideRepository(remote: IHeroRemoteDataSource, local: IHeroLocalDataSource): IHeroRepository {
        return HeroRepositoryImpl(remote, local)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: IHeroRepository): HeroUseCases {
        return HeroUseCases(
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            toggleFavoriteHeroUseCase = ToogleFavoriteHeroUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDeleteFavHeroUseCase(repository: IHeroRepository): DeleteFavoriteHeroUseCase {
        return  DeleteFavoriteHeroUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavHeroUseCase(repository: IHeroRepository): GetAllFavoritesHeroesUseCase {
        return  GetAllFavoritesHeroesUseCase(repository)
    }



}