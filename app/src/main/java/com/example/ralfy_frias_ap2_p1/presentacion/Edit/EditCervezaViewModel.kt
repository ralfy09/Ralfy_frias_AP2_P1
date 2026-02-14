package com.example.ralfy_frias_ap2_p1.presentacion.Edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ralfy_frias_ap2_p1.domain.model.Cerveza
import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCervezaViewModel @Inject constructor(
    private val repository: CervezaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditCervezaUiState())
    val uiState: StateFlow<EditCervezaUiState> = _uiState

    private var currentId = 0

    fun setId(id: Int) {
        currentId = id
        if (id != 0) {
            loadCerveza(id)
        }
    }

    private fun loadCerveza(id: Int) {
        viewModelScope.launch {
            repository.getById(id)?.let { cerveza ->
                _uiState.update {
                    it.copy(
                        nombre = cerveza.nombre,
                        marca = cerveza.marca,
                        puntuacion = cerveza.puntuacion.toString()
                    )
                }
            }
        }
    }

    fun onEvent(event: EditCervezaUiEvent) {
        when (event) {

            is EditCervezaUiEvent.OnNombreChange -> {
                _uiState.update {
                    it.copy(nombre = event.nombre, nombreError = null)
                }
            }

            is EditCervezaUiEvent.OnMarcaChange -> {
                _uiState.update {
                    it.copy(marca = event.marca, marcaError = null)
                }
            }

            is EditCervezaUiEvent.OnPuntuacionChange -> {
                _uiState.update {
                    it.copy(puntuacion = event.puntuacion, puntuacionError = null)
                }
            }

            EditCervezaUiEvent.OnSave -> {
                validateAndSave()
            }
        }
    }

    private fun validateAndSave() {

        val nombre = _uiState.value.nombre.trim()
        val marca = _uiState.value.marca.trim()
        val puntuacionText = _uiState.value.puntuacion.trim()

        var hasError = false

        var nombreError: String? = null
        var marcaError: String? = null
        var puntuacionError: String? = null

        if (nombre.isEmpty()) {
            nombreError = "El nombre es obligatorio"
            hasError = true
        }

        if (marca.isEmpty()) {
            marcaError = "La marca es obligatoria"
            hasError = true
        }

        val puntuacion = puntuacionText.toIntOrNull()

        if (puntuacion == null) {
            puntuacionError = "Debe ser un número válido"
            hasError = true
        } else if (puntuacion !in 1..10) {
            puntuacionError = "Debe estar entre 1 y 10"
            hasError = true
        }

        _uiState.update {
            it.copy(
                nombreError = nombreError,
                marcaError = marcaError,
                puntuacionError = puntuacionError
            )
        }

        if (hasError) return

        viewModelScope.launch {

            val cerveza = Cerveza(
                idCerveza = currentId,
                nombre = nombre,
                marca = marca,
                puntuacion = puntuacion!!
            )

            if (currentId == 0) {
                repository.insert(cerveza)
            } else {
                repository.update(cerveza)
            }

            _uiState.update {
                it.copy(isSaved = true)
            }
        }
    }
}
