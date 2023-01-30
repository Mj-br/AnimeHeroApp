package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_favorites_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.robertconstantin.animeappcliient.R
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.data.mapper.toFavHeroEntity
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.DeleteFavoriteHeroUseCase
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.GetAllFavoritesHeroesUseCase
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.HeroUseCases
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroFeedScreenState
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen.HeroFeedScreenEvent
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroVo
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Inject all use cases separately.
@HiltViewModel
class HeroFavoritesScreenViewModel @Inject constructor(
    private val deleteHeroUseCase: DeleteFavoriteHeroUseCase,
    private val getAllFavHeroes: GetAllFavoritesHeroesUseCase
    ): ViewModel() {

    var state by mutableStateOf<HeroFeedScreenState>(HeroFeedScreenState())
        private set

    private var _singleUiEvent = Channel<UiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    init {
        getAllFavoritesHero()
    }

    private var favoritesHeroesJob: Job? = null

    fun onEvent(event: HeroFavoritesScreenEvent) {
        when (event) {
            is HeroFavoritesScreenEvent.OnDeleteClick -> {
                deleteFavoriteHero(event.hero)
            }
        }
    }

    private fun deleteFavoriteHero(hero: HeroVO) {
        viewModelScope.launch {
            deleteHeroUseCase.invoke(hero.toHeroDM())

            _singleUiEvent.send(
                UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_deleted_from_fav))
            )
        }
    }

    private fun getAllFavoritesHero() {
        favoritesHeroesJob?.cancel()
        favoritesHeroesJob = viewModelScope.launch {
            state = state.copy(loading = true)
            getAllFavHeroes.invoke().collectLatest { heroes ->
                state = state.copy(loading = false, heroes =  heroes.map { it.toHeroVo() })
            }
        }
    }
}