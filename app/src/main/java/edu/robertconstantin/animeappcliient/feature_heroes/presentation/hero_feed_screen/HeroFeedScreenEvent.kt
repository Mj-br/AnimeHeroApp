package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen

import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO

sealed class HeroFeedScreenEvent {
    data class OnFavoriteClick(val hero: HeroVO): HeroFeedScreenEvent()
}
