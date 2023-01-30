package edu.robertconstantin.animeappcliient.feature_heroes.presentation.util

import edu.robertconstantin.animeappcliient.core.presentation.UiEvent

sealed class FavoritesToggler {
    data class Favorite(val text: UiEvent): FavoritesToggler()
    data class UnFavorite(val text: UiEvent): FavoritesToggler()
}
