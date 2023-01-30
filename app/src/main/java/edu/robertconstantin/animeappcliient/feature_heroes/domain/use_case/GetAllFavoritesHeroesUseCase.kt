package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case

import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesHeroesUseCase(private val repository: IHeroRepository) {
    operator fun invoke(): Flow<List<HeroDM>> = repository.getAllFavoritesHeroes()
}