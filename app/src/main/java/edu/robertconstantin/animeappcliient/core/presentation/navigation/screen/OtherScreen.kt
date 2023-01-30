package edu.robertconstantin.animeappcliient.core.presentation.navigation.screen

import edu.robertconstantin.animeappcliient.core.util.CoreConstants.EDIT_FAVORITE_HERO_SCREEN

sealed class OtherScreen(val route: String) {
    object EditScreen: OtherScreen(EDIT_FAVORITE_HERO_SCREEN)
}
