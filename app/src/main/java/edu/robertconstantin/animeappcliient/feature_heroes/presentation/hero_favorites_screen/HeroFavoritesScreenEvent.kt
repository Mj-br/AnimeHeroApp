package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_favorites_screen

import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO

sealed class HeroFavoritesScreenEvent {
    data class OnDeleteClick(val hero: HeroVO): HeroFavoritesScreenEvent()
}
