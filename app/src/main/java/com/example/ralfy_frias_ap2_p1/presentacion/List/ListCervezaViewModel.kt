package com.example.ralfy_frias_ap2_p1.presentacion.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCervezaViewModel @Inject constructor(
    private val repository: CervezaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListCervezaUiState())
    val uiState: StateFlow<ListCervezaUiState> = _uiState

    init {
        loadCervezas()
    }

    fun onEvent(event: ListCervezaUiEvent) {
        when (event) {

            is ListCervezaUiEvent.OnNombreFiltroChange -> {
                _uiState.update { it.copy(nombreFiltro = event.nombre) }
                loadCervezas()
            }

            is ListCervezaUiEvent.OnMarcaFiltroChange -> {
                _uiState.update { it.copy(marcaFiltro = event.marca) }
                loadCervezas()
            }

            is ListCervezaUiEvent.OnDelete -> {
                viewModelScope.launch {
                    val cerveza = _uiState.value.cervezas
                        .find { it.idCerveza == event.idCerveza }

                    cerveza?.let {
                        repository.delete(it)
                        loadCervezas()
                    }
                }
            }
        }
    }

    private fun loadCervezas() {
        viewModelScope.launch {
            repository.getCervezas(
                _uiState.value.nombreFiltro.ifBlank { null },
                _uiState.value.marcaFiltro.ifBlank { null }
            ).collect { list ->

                val total = list.size
                val promedio =
                    if (total > 0) list.map { it.puntuacion }.average()
                    else 0.0

                _uiState.update {
                    it.copy(
                        cervezas = list,
                        total = total,
                        promedio = promedio
                    )
                }
            }
        }
    }
}