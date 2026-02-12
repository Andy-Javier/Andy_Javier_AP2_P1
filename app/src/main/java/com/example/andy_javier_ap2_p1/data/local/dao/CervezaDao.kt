package com.example.andy_javier_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity

@Dao
interface CervezaDao {
    @Upsert
    suspend fun upsert(Cerveza: CervezaEntity)
}
