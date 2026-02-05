package com.example.andy_javier_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object List : Screen()
    @Serializable
    data class Edit(val id: Int? = null) : Screen()
}
