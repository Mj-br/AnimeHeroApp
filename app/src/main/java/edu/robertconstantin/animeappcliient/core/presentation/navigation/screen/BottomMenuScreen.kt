package edu.robertconstantin.animeappcliient.core.presentation.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import edu.robertconstantin.animeappcliient.core.util.CoreConstants.HERO_CREATE_SCREEN
import edu.robertconstantin.animeappcliient.core.util.CoreConstants.HERO_CREATE_TITLE
import edu.robertconstantin.animeappcliient.core.util.CoreConstants.HERO_FAVORITES_SCREEN
import edu.robertconstantin.animeappcliient.core.util.CoreConstants.HERO_FAVORITES_TITLE
import edu.robertconstantin.animeappcliient.core.util.CoreConstants.HERO_FEED_SCREEN
import edu.robertconstantin.animeappcliient.core.util.CoreConstants.HERO_FEED_TITLE

//Tenemos que pensar que queremos mostrar cuadno vayamos a dirigirnos a una opcion de menu u otra.
sealed class BottomMenuScreen(val route: String, val icon: ImageVector? = null, val title: String) {  // Se usa para saber que opcion de menu se selecciono
    object HeroFeedScreen: BottomMenuScreen(
        route = HERO_FEED_SCREEN,
        icon = Icons.Default.List,
        title = HERO_FEED_TITLE
    )
    object HeroFavoritesScreen: BottomMenuScreen(
        route = HERO_FAVORITES_SCREEN,
        icon = Icons.Default.Favorite,
        title = HERO_FAVORITES_TITLE
    )
    object HeroCreateScreen: BottomMenuScreen(
        route = HERO_CREATE_SCREEN,
        icon = Icons.Default.Create,
        title = HERO_CREATE_TITLE
    )
}
