package com.example.andy_javier_ap2_p1.domain.repository

import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity

interface CervezaRepository {
    suspend fun upsert(cerveza: CervezaEntity)
}
