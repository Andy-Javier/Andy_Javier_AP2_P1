package com.example.andy_javier_ap2_p1.data.repository

import com.example.andy_javier_ap2_p1.data.local.dao.CervezaDao
import com.example.andy_javier_ap2_p1.data.local.mapper.toDomain
import com.example.andy_javier_ap2_p1.data.local.mapper.toEntity
import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import com.example.andy_javier_ap2_p1.domain.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val cervezaDao: CervezaDao
) : CervezaRepository {
    override fun observeCervezas(nombre: String?, marca: String?): Flow<List<Cerveza>> {
        return cervezaDao.observeFiltered(nombre, marca).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getCerveza(id: Int): Cerveza? {
        return cervezaDao.getById(id)?.toDomain()
    }

    override suspend fun upsert(cerveza: Cerveza): Int {
        cervezaDao.upsert(cerveza.toEntity())
        return 1
    }

    override suspend fun delete(id: Int) {
        cervezaDao.deleteById(id)
    }

    override suspend fun getTotal(nombre: String?, marca: String?): Int {
        return cervezaDao.getTotal(nombre, marca)
    }

    override suspend fun getPromedio(nombre: String?, marca: String?): Double? {
        return cervezaDao.getPromedio(nombre, marca)
    }
}
