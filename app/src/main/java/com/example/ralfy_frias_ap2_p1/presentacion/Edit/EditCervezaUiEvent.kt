package com.example.ralfy_frias_ap2_p1.presentacion.Edit

sealed class EditCervezaUiEvent {

    data class OnNombreChange(val nombre: String) : EditCervezaUiEvent()
    data class OnMarcaChange(val marca: String) : EditCervezaUiEvent()
    data class OnPuntuacionChange(val puntuacion: String) : EditCervezaUiEvent()

    object OnSave : EditCervezaUiEvent()
}