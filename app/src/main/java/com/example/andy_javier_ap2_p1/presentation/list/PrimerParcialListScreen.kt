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
import com.example.andy_javier_ap2_p1.data.local.entities.BorrameEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimerParcialListScreen(
    items: List<BorrameEntity>,
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Primer Parcial List") })
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
                    headlineContent = { Text(item.descripcion) },
                    modifier = Modifier.clickable { item.id?.let { onEdit(it) } }
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimerParcialListPreview() {
    PrimerParcialListScreen(
        items = emptyList(),
        onAdd = {},
        onEdit = {}
    )
}
