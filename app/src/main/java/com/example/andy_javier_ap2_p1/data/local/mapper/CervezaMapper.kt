package com.example.andy_javier_ap2_p1.data.local.mapper

import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity
import com.example.andy_javier_ap2_p1.domain.models.Cerveza

fun CervezaEntity.toDomain(): Cerveza {
    return Cerveza(
        idCerveza = this.idCerveza,
        nombre = this.nombre,
        marca = this.marca,
        puntuacion = this.puntuacion
    )
}

fun Cerveza.toEntity(): CervezaEntity {
    return CervezaEntity(
        idCerveza = this.idCerveza,
        nombre = this.nombre,
        marca = this.marca,
        puntuacion = this.puntuacion
    )
}
