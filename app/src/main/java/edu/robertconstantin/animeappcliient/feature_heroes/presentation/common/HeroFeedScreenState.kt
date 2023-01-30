package edu.robertconstantin.animeappcliient.feature_heroes.presentation.common

import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO

data class HeroFeedScreenState(
    val loading: Boolean = false,
    val heroes: List<HeroVO> = emptyList()
)
