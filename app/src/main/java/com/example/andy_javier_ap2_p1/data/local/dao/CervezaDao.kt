package com.example.andy_javier_ap2_p1.data.local.dao

import androidx.room.*
import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao {

    @Query("""
        SELECT * FROM cervezas
        WHERE (:nombre IS NULL OR nombre LIKE '%' || :nombre || '%')
        AND (:marca IS NULL OR marca LIKE '%' || :marca || '%')
        ORDER BY idCerveza DESC
    """)
    fun observeFiltered(
        nombre: String?,
        marca: String?
    ): Flow<List<CervezaEntity>>

    @Query("SELECT * FROM cervezas WHERE idCerveza = :id")
    suspend fun getById(id: Int): CervezaEntity?

    @Upsert
    suspend fun upsert(entity: CervezaEntity)

    @Query("DELETE FROM cervezas WHERE idCerveza = :id")
    suspend fun deleteById(id: Int)

    @Query("""
        SELECT COUNT(*) FROM cervezas
        WHERE (:nombre IS NULL OR nombre LIKE '%' || :nombre || '%')
        AND (:marca IS NULL OR marca LIKE '%' || :marca || '%')
    """)
    suspend fun getTotal(nombre: String?, marca: String?): Int

    @Query("""
        SELECT AVG(puntuacion) FROM cervezas
        WHERE (:nombre IS NULL OR nombre LIKE '%' || :nombre || '%')
        AND (:marca IS NULL OR marca LIKE '%' || :marca || '%')
    """)
    suspend fun getPromedio(nombre: String?, marca: String?): Double?
}
