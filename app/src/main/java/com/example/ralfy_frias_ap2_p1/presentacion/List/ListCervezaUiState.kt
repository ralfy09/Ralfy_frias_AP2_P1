package com.example.ralfy_frias_ap2_p1.presentacion.List

import com.example.ralfy_frias_ap2_p1.domain.model.Cerveza

data class ListCervezaUiState(
    val cervezas: List<Cerveza> = emptyList(),
    val nombreFiltro: String = "",
    val marcaFiltro: String = "",
    val total: Int = 0,
    val promedio: Double = 0.0
)