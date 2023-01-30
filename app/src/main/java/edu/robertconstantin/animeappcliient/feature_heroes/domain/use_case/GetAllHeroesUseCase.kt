package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import javax.inject.Inject


//Caso de uso para obtener todos los heroes

//Invoke se puede llamar desde el viewmodel

class GetAllHeroesUseCase @Inject constructor(private val repository: IHeroRepository) {

    suspend operator fun invoke(): Resource<List<HeroDM>> {
        return repository.getAllHeroes()
    }
}