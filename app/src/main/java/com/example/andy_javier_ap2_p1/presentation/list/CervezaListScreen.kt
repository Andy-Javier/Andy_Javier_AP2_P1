package com.example.andy_javier_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListScreen(
    items: List<CervezaEntity>,
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Cervezas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(items) { item ->
                ListItem(
                    headlineContent = { Text(item.Nombre) },
                    modifier = Modifier.clickable { item.IdCerveza?.let { onEdit(it) } }
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CervezaListPreview() {
    CervezaListScreen(
        items = emptyList(),
        onAdd = {},
        onEdit = {}
    )
}
