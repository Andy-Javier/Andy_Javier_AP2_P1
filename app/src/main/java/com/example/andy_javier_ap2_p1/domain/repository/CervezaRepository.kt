package com.example.andy_javier_ap2_p1.domain.repository

import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    fun observeCervezas(nombre: String?, marca: String?): Flow<List<Cerveza>>
    suspend fun getCerveza(id: Int): Cerveza?
    suspend fun upsert(cerveza: Cerveza): Int
    suspend fun delete(id: Int)
    suspend fun getTotal(nombre: String?, marca: String?): Int
    suspend fun getPromedio(nombre: String?, marca: String?): Double?
}
