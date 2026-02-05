package com.example.andy_javier_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.andy_javier_ap2_p1.data.local.entities.BorrameEntity

@Dao
interface PrimerParcialDao {
    @Upsert
    suspend fun upsert(borrame: BorrameEntity)
}
