package com.example.ralfy_frias_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.ralfy_frias_ap2_p1.data.local.dao.CervezaDao
import com.example.ralfy_frias_ap2_p1.data.local.database.AppDatabase
import com.example.ralfy_frias_ap2_p1.data.repository.CervezaRepositoryImpl
import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "beertracker_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCervezaDao(
        database: AppDatabase
    ): CervezaDao {
        return database.cervezaDao()
    }

    // 🔹 REPOSITORY
    @Provides
    @Singleton
    fun provideCervezaRepository(
        dao: CervezaDao
    ): CervezaRepository {
        return CervezaRepositoryImpl(dao)
    }
}