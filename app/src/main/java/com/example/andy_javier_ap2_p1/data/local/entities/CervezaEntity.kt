package com.example.andy_javier_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cerveza")
data class CervezaEntity(
    @PrimaryKey
    val IdCerveza: Int? = null,
    val Nombre: String = "",
    val Marca: String = "",
    val Puntuación: Int? = 1-5,
)