package com.example.andy_javier_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                onAdd = { navController.navigate(Screen.Edit(-1)) },
                onEdit = { id -> navController.navigate(Screen.Edit(id)) }
            )
        }
        composable<Screen.Edit> {
            CervezaEditScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
