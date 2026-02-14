package com.example.ralfy_frias_ap2_p1.presentacion.Edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCervezaScreen(
    navController: NavController,
    idCerveza: Int,
    viewModel: EditCervezaViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(idCerveza) {
        viewModel.setId(idCerveza)
    }

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (idCerveza == 0)
                            "Agregar Cerveza"
                        else
                            "Editar Cerveza"
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = state.nombre,
                onValueChange = {
                    viewModel.onEvent(
                        EditCervezaUiEvent.OnNombreChange(it)
                    )
                },
                label = { Text("Nombre") },
                isError = state.nombreError != null,
                supportingText = {
                    state.nombreError?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.marca,
                onValueChange = {
                    viewModel.onEvent(
                        EditCervezaUiEvent.OnMarcaChange(it)
                    )
                },
                label = { Text("Marca") },
                isError = state.marcaError != null,
                supportingText = {
                    state.marcaError?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.puntuacion,
                onValueChange = {
                    viewModel.onEvent(
                        EditCervezaUiEvent.OnPuntuacionChange(it)
                    )
                },
                label = { Text("Puntuación (1-10)") },
                isError = state.puntuacionError != null,
                supportingText = {
                    state.puntuacionError?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.onEvent(EditCervezaUiEvent.OnSave)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}
