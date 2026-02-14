package com.example.ralfy_frias_ap2_p1.presentacion.List

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ralfy_frias_ap2_p1.presentacion.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCervezaScreen(
    navController: NavController,
    viewModel: ListCervezaViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Lista de Cervezas",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.editCerveza(0))
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // 🔎 FILTROS
            OutlinedTextField(
                value = state.nombreFiltro,
                onValueChange = {
                    viewModel.onEvent(
                        ListCervezaUiEvent.OnNombreFiltroChange(it)
                    )
                },
                label = { Text("Filtrar por Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.marcaFiltro,
                onValueChange = {
                    viewModel.onEvent(
                        ListCervezaUiEvent.OnMarcaFiltroChange(it)
                    )
                },
                label = { Text("Filtrar por Marca") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (state.cervezas.isEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay cervezas registradas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(state.cervezas) { cerveza ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(vertical = 6.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = cerveza.nombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Text("Marca: ${cerveza.marca}")
                                Text("Puntuación: ${cerveza.puntuacion}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    TextButton(
                                        onClick = {
                                            navController.navigate(
                                                Routes.editCerveza(cerveza.idCerveza)
                                            )
                                        }
                                    ) {
                                        Text("Editar")
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    TextButton(
                                        onClick = {
                                            viewModel.onEvent(
                                                ListCervezaUiEvent.OnDelete(
                                                    cerveza.idCerveza
                                                )
                                            )
                                        }
                                    ) {
                                        Text("Eliminar")
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }

            Divider()

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total de cervezas: ${state.total}",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Promedio: ${"%.2f".format(state.promedio)}"
                )
            }
        }
    }
}




