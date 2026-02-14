package com.example.ralfy_frias_ap2_p1.presentacion.List

sealed class ListCervezaUiEvent {

    data class OnNombreFiltroChange(val nombre: String) : ListCervezaUiEvent()

    data class OnMarcaFiltroChange(val marca: String) : ListCervezaUiEvent()

    data class OnDelete(val idCerveza: Int) : ListCervezaUiEvent()
}