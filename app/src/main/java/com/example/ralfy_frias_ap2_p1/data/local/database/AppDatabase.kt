package com.example.ralfy_frias_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ralfy_frias_ap2_p1.data.local.dao.CervezaDao
import com.example.ralfy_frias_ap2_p1.data.local.entities.CervezaEntity

@Database(
    entities = [CervezaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cervezaDao(): CervezaDao
}




