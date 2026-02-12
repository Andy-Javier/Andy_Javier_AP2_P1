package com.example.andy_javier_ap2_p1.data.repository

import com.example.andy_javier_ap2_p1.data.local.dao.CervezaDao
import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity
import com.example.andy_javier_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val cervezaDao: CervezaDao
) : CervezaRepository {
    override suspend fun upsert(cerveza: CervezaEntity) = cervezaDao.upsert(cerveza)
}
