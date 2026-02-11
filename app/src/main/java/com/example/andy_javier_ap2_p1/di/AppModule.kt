package com.example.andy_javier_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.andy_javier_ap2_p1.data.local.dao.CervezaDao
import com.example.andy_javier_ap2_p1.data.local.database.CervezaDb
import com.example.andy_javier_ap2_p1.data.repository.CervezaRepositoryImpl
import com.example.andy_javier_ap2_p1.domain.repository.CervezaRepository
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
    fun provideCervezaDb(@ApplicationContext appContext: Context): CervezaDb {
        return Room.databaseBuilder(
            appContext,
            CervezaDb::class.java,
            "Cerveza.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCervezaDao(db: CervezaDb): CervezaDao {
        return db.CervezaDao()
    }

    @Provides
    @Singleton
    fun provideCervezaRepository(cervezaDao: CervezaDao): CervezaRepository {
        return CervezaRepositoryImpl(cervezaDao)
    }
}
