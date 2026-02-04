package com.example.andy_javier_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.andy_javier_ap2_p1.data.local.entities.BorrameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BorrameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(borrame: BorrameEntity)

    @Query("SELECT * FROM Borrame WHERE id = :id")
    suspend fun find(id: Int): BorrameEntity?

    @Delete
    suspend fun delete(borrame: BorrameEntity)

    @Query("SELECT * FROM Borrame")
    fun getAll(): Flow<List<BorrameEntity>>
}
