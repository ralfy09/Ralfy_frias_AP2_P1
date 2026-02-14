package com.example.ralfy_frias_ap2_p1.domain.repository

import com.example.ralfy_frias_ap2_p1.domain.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {

    fun getCervezas(nombre: String?, marca: String?): Flow<List<Cerveza>>

    suspend fun getById(id: Int): Cerveza?

    suspend fun insert(cerveza: Cerveza)

    suspend fun update(cerveza: Cerveza)

    suspend fun delete(cerveza: Cerveza)

}

