package com.example.ralfy_frias_ap2_p1.data.local.mapper

import com.example.ralfy_frias_ap2_p1.data.local.entities.CervezaEntity
import com.example.ralfy_frias_ap2_p1.domain.model.Cerveza

fun CervezaEntity.toDomain() = Cerveza(
    idCerveza = idCerveza,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)

fun Cerveza.toEntity() = CervezaEntity(
    idCerveza = idCerveza,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)
