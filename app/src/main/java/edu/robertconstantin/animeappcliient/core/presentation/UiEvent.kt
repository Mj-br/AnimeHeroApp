package edu.robertconstantin.animeappcliient.core.presentation

import edu.robertconstantin.animeappcliient.core.util.UiText

sealed class UiEvent {
    object NavigateTo: UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}
