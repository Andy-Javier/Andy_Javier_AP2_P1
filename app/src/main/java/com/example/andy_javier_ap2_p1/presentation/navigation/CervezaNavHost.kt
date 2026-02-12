package com.example.andy_javier_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.andy_javier_ap2_p1.presentation.edit.CervezaEditScreen
import com.example.andy_javier_ap2_p1.presentation.list.CervezaListScreen

@Composable
fun CervezaNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            CervezaListScreen(
                items = emptyList(),
                onAdd = { navController.navigate(Screen.Edit()) },
                onEdit = { id -> navController.navigate(Screen.Edit(id)) }
            )
        }
        composable<Screen.Edit> { backStackEntry ->
            val edit = backStackEntry.toRoute<Screen.Edit>()
            CervezaEditScreen(
                id = edit.id,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
