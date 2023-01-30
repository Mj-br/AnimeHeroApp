package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_favorites_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroItem
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen.HeroFeedScreenEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HeroFavoritesScreen(
    imageLoader: ImageLoader,
    showUserInfo: (text: String) -> Unit = {},
    viewModel: HeroFavoritesScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.singleUiEvent.collectLatest { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    showUserInfo(uiEvent.message.asString(context))
                }
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        LazyColumn {
            items(state.heroes) {
                HeroItem(imageLoader = imageLoader, hero = it, isFromFavorites = true , onHeroDeleteClick = { hero ->
                    viewModel.onEvent(HeroFavoritesScreenEvent.OnDeleteClick(hero))
                })
            }
        }
    }
}