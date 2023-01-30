package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.robertconstantin.animeappcliient.R
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.HeroUseCases
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroFeedScreenState
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroVo
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//Mapea nuestros datos a una variable de estado para que nuestra vista principal sea reactiva (que cambie cuando cambie el estado)

@HiltViewModel
class HeroFeedScreenViewModel
@Inject constructor(
    private val useCases: HeroUseCases //Pasamos el caso de uso, los viewmodel siempre tendran casos de uso
) : ViewModel() {


    //Declaramos una variable de estado mutable, tiene by (Delegacion a la clase que tiene lo campos que vamos a utilizar)

    var state by mutableStateOf<HeroFeedScreenState>(HeroFeedScreenState())
        private set //Solo se puede modificar desde el viewmodel

    private var _singleUiEvent = Channel<UiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()


    init {
        getAllHeroes() //Obtenemos todos los heroes
    }

    fun onEvent(event: HeroFeedScreenEvent) {
        when (event) {
            is HeroFeedScreenEvent.OnFavoriteClick -> {
                toggleFavoriteHero(event.hero)
            }
        }
    }

    private fun getAllHeroes() {

        viewModelScope.launch(Dispatchers.IO) {//Corrutina que se ejecuta en el hilo IO, se lanza con los scopes que ya vienen creado por defecto en el viewmodel
            state = state.copy(loading = true) //Copia el estado actual y cambia el loading a true
            useCases.getAllHeroesUseCase.invoke().mapResourceData( //Ejecutamos el caso de uso, lo invoco con invoke y me devuelve los datos
                success = { //Si es exitoso
                    it?.let { heroes -> //Si no es nulo
                        state = state.copy( //Copia el estado actual
                            loading = false, //Cambia el loading a false
                            heroes = heroes.map { hero -> hero.toHeroVo() }) //Mapea los heroes a la vista
                    }
                },
                error = { msg -> //Si hay un error
                    state = state.copy(loading = false) //Copia el estado actual y cambia el loading a false
                    _singleUiEvent.send(UiEvent.ShowSnackBar(msg ?: UiText.unknownError())) //Envia un evento de snackbar con el mensaje de error
                }
            )
        }
    }

    private fun toggleFavoriteHero(hero: HeroVO) {
        viewModelScope.launch {
            useCases.toggleFavoriteHeroUseCase.invoke(hero.toHeroDM(), hero.isAddedToFavorites)

            state = state.copy(heroes = state.heroes.map {
                if (it.id == hero.id) it.copy(isAddedToFavorites = !it.isAddedToFavorites)
                else it
            })

            when (hero.isAddedToFavorites) {
                true -> _singleUiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_deleted_from_fav))
                )
                false -> _singleUiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_added_to_fav))
                )
            }
        }
    }
}