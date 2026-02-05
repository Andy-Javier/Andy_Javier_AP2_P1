package com.example.andy_javier_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.andy_javier_ap2_p1.data.local.dao.PrimerParcialDao
import com.example.andy_javier_ap2_p1.data.local.database.PrimerParcialDb
import com.example.andy_javier_ap2_p1.data.repository.PrimerParcialRepositoryImpl
import com.example.andy_javier_ap2_p1.domain.repository.PrimerParcialRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePrimerParcialDb(@ApplicationContext appContext: Context): PrimerParcialDb {
        return Room.databaseBuilder(
            appContext,
            PrimerParcialDb::class.java,
            "PrimerParcial.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePrimerParcialDao(db: PrimerParcialDb): PrimerParcialDao {
        return db.primerParcialDao()
    }

    @Provides
    @Singleton
    fun providePrimerParcialRepository(primerParcialDao: PrimerParcialDao): PrimerParcialRepository {
        return PrimerParcialRepositoryImpl(primerParcialDao)
    }
}
