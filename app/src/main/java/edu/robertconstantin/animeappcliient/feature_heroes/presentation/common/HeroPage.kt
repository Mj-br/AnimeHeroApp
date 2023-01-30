package edu.robertconstantin.animeappcliient.feature_heroes.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroPage.HeroFeedScreen.heroFavoritesScreenViewModel
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroPage.HeroFeedScreen.heroFeedScreenViewModel
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen.HeroFeedScreenViewModel
import javax.inject.Inject

sealed class HeroPage(val title: String, val screen: @Composable () -> Unit = {}) {

    @Inject
    lateinit var heroFeedScreenViewModel: HeroFeedScreenViewModel
    @Inject
    lateinit var heroFavoritesScreenViewModel: HeroFeedScreenViewModel

    object HeroFeedScreen: HeroPage(
        title = "Hero feed",
        screen = {
            val context = LocalContext.current
            HeroBaseScreen(imageLoader = ImageLoader(context), viewModel = heroFeedScreenViewModel)
        }
    )

    object HeroFavoritesScreen: HeroPage(
        title = "Hero Favorites",
        screen = {
            val context = LocalContext.current
            HeroBaseScreen(imageLoader = ImageLoader(context), viewModel = heroFavoritesScreenViewModel)
        }
    )

}