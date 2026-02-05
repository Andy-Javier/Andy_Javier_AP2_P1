package com.example.andy_javier_ap2_p1.data.repository

import com.example.andy_javier_ap2_p1.data.local.dao.PrimerParcialDao
import com.example.andy_javier_ap2_p1.data.local.entities.BorrameEntity
import com.example.andy_javier_ap2_p1.domain.repository.PrimerParcialRepository
import javax.inject.Inject

class PrimerParcialRepositoryImpl @Inject constructor(
    private val primerParcialDao: PrimerParcialDao
) : PrimerParcialRepository {
    override suspend fun upsert(borrame: BorrameEntity) = primerParcialDao.upsert(borrame)
}
