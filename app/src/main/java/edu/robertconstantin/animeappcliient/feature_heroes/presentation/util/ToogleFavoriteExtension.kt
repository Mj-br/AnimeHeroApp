package edu.robertconstantin.animeappcliient.feature_heroes.presentation.util

import androidx.compose.runtime.MutableState
import edu.robertconstantin.animeappcliient.R
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroFeedScreenState
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO

fun <T> HeroFeedScreenState.toggleHeroFavorites(hero: HeroVO, state: MutableState<T>): FavoritesToggler {

    this.copy(heroes = this.heroes.map {
        if (it.id == hero.id) it.copy(isAddedToFavorites = !it.isAddedToFavorites)
        else it
    })

    return when (hero.isAddedToFavorites) {
        true -> FavoritesToggler.Favorite(
            UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_deleted_from_fav))
        )
        false -> FavoritesToggler.UnFavorite(
            UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_added_to_fav))
        )
    }
}