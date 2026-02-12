package com.example.andy_javier_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.andy_javier_ap2_p1.domain.models.Cerveza

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListScreen(
    viewModel: CervezaListViewModel = hiltViewModel(),
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Cervezas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Cerveza")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Filtros
            OutlinedTextField(
                value = state.nombreFilter,
                onValueChange = { viewModel.onNombreFilterChange(it) },
                label = { Text("Filtrar por Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.marcaFilter,
                onValueChange = { viewModel.onMarcaFilterChange(it) },
                label = { Text("Filtrar por Marca") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Cervezas
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(state.cervezas) { cerveza ->
                    CervezaItem(
                        cerveza = cerveza,
                        onClick = { cerveza.idCerveza?.let { onEdit(it) } },
                        onDelete = { cerveza.idCerveza?.let { viewModel.deleteCerveza(it) } }
                    )
                    HorizontalDivider()
                }
            }

            // Resumen (Totales)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total cervezas probadas: ${state.totalCervezas}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Promedio de puntuación: ${"%.2f".format(state.promedioPuntuacion)}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CervezaItem(
    cerveza: Cerveza,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineContent = { Text(cerveza.nombre) },
        supportingContent = {
            Column {
                Text("Marca: ${cerveza.marca}")
                Text("Puntuación: ${cerveza.puntuacion} ⭐")
            }
        },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
            }
        }
    )
}
