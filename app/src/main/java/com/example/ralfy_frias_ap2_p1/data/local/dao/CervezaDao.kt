package com.example.ralfy_frias_ap2_p1.data.local.dao

import androidx.room.*
import com.example.ralfy_frias_ap2_p1.data.local.entities.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao {

    @Query("""
        SELECT * FROM cervezas
        WHERE (:nombre IS NULL OR nombre LIKE '%' || :nombre || '%')
        AND (:marca IS NULL OR marca LIKE '%' || :marca || '%')
    """)
    fun getCervezas(
        nombre: String?,
        marca: String?
    ): Flow<List<CervezaEntity>>

    @Query("SELECT * FROM cervezas WHERE idCerveza = :id")
    suspend fun getById(id: Int): CervezaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cerveza: CervezaEntity)

    @Update
    suspend fun update(cerveza: CervezaEntity)

    @Delete
    suspend fun delete(cerveza: CervezaEntity)
}

