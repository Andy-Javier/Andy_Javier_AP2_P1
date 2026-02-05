package com.example.andy_javier_ap2_p1.domain.repository

import com.example.andy_javier_ap2_p1.data.local.entities.BorrameEntity

interface PrimerParcialRepository {
    suspend fun upsert(borrame: BorrameEntity)
}
