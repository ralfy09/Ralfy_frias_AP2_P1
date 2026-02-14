package com.example.ralfy_frias_ap2_p1.data.repository

import com.example.ralfy_frias_ap2_p1.data.local.dao.CervezaDao
import com.example.ralfy_frias_ap2_p1.data.local.mapper.toDomain
import com.example.ralfy_frias_ap2_p1.data.local.mapper.toEntity
import com.example.ralfy_frias_ap2_p1.domain.model.Cerveza
import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CervezaRepositoryImpl(
    private val dao: CervezaDao
) : CervezaRepository {

    override fun getCervezas(nombre: String?, marca: String?): Flow<List<Cerveza>> {
        return dao.getCervezas(nombre, marca).map { entityList ->
            entityList.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun getById(id: Int): Cerveza? {
        return dao.getById(id)?.toDomain()
    }

    override suspend fun insert(cerveza: Cerveza) {
        dao.insert(cerveza.toEntity())
    }

    override suspend fun update(cerveza: Cerveza) {
        dao.update(cerveza.toEntity())
    }

    override suspend fun delete(cerveza: Cerveza) {
        dao.delete(cerveza.toEntity())
    }
}





