package com.example.andy_javier_ap2_p1.domain.usecase

import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import com.example.andy_javier_ap2_p1.domain.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    operator fun invoke(nombre: String?, marca: String?): Flow<List<Cerveza>> {
        return repository.observeCervezas(nombre, marca)
    }

    suspend fun getTotal(nombre: String?, marca: String?): Int {
        return repository.getTotal(nombre, marca)
    }

    suspend fun getPromedio(nombre: String?, marca: String?): Double? {
        return repository.getPromedio(nombre, marca)
    }
}
