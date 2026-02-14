package com.example.ralfy_frias_ap2_p1.presentacion.Edit

data class EditCervezaUiState(
    val nombre: String = "",
    val marca: String = "",
    val puntuacion: String = "",

    val nombreError: String? = null,
    val marcaError: String? = null,
    val puntuacionError: String? = null,

    val isSaved: Boolean = false
)

