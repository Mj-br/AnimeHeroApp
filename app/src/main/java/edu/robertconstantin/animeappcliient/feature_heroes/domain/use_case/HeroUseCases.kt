package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case
//Creamos nuestro caso de uso

data class HeroUseCases(
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val toggleFavoriteHeroUseCase: ToogleFavoriteHeroUseCase
)
